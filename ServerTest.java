import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class ServerTest {
    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(4242);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();
                System.out.println("A client has connected " + s);



                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());


                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();


            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        Student student = new Student();
        User user = new User();
        Teacher teacher = new Teacher();

        ArrayList<String> loginInfo = user.createFile();
        ArrayList<String> courseList = teacher.courseList();
        while (true) {
            try {
                String line;
                while (!(line = dis.readUTF()).equals("stop")) {
                    try {

                        if(line.endsWith("000")) {
                            dos.writeUTF("continue");
                            break;
                        }
                        if (line.endsWith("00")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a  username");
                            line = line.substring(0, line.length() - 2);
                            user.setUsername(line);
                        }
                        else if (line.endsWith("01")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a teacher password");
                            line = line.substring(0, line.length() - 2);
                            String nameTaken = user.createName(loginInfo, user.getUsername(),false);
                            if (nameTaken.equals("taken")) {
                                dos.writeUTF("name taken");
                            } else {
                                user.setPassword(line);
                                user.teacherPass(loginInfo,line,user.getUsername());
                                dos.writeUTF("name available");
                                break;
                            }
                        }
                        else if (line.endsWith("03")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a student password");
                            line = line.substring(0, line.length() - 2);
                            String nameTaken = user.createName(loginInfo, user.getUsername(), false);
                            if(nameTaken.equals("taken")) {
                                dos.writeUTF("name taken");
                            }
                            else {
                                user.studentPass(loginInfo, line, user.getUsername());
                                dos.writeUTF("name available");
                                break;
                            }
                        }

                        else if (line.endsWith("04")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            String nameMatch = user.checkUsername(loginInfo,line);
                            if(nameMatch.equals("username match")) {
                                user.setUsername(line);
                                dos.writeUTF("username match");
                            }
                            else {
                                dos.writeUTF("username wrong");
                                break;
                            }
                        }

                        else if (line.endsWith("05")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a teacher password for logging in");
                            line = line.substring(0, line.length() - 2);
                            String passMatch = user.checkTeacher(loginInfo,line);
                            if(passMatch.equals("pass match")) {
                                user.setPassword(line);
                                dos.writeUTF("pass match");

                            }
                            else {
                                dos.writeUTF("pass wrong");
                                break;
                            }
                        }

                        else if (line.endsWith("06")) {
                            loginInfo = user.createFile();
                            System.out.println("This is a student password");
                            line = line.substring(0, line.length() - 2);
                            String passMatch = user.checkStudent(loginInfo,line);
                            if(passMatch.equals("pass match")) {
                                dos.writeUTF("pass match");
                            }
                            else {
                                dos.writeUTF("pass wrong");
                                break;
                            }
                        }
                        else if (line.endsWith("changeUsername")) {
                            loginInfo = user.createFile();
                            System.out.println("User is trying to change username");
                            line = line.substring(0, line.length() - 14);
                            System.out.println(line);
                            System.out.println(user.getUsername());
                            String changeUsername = user.changeUsername(loginInfo, user.getUsername(), line);
                            System.out.println(changeUsername);
                            if(changeUsername.equals("UsernameChanged")) {
                                dos.writeUTF("usernameChanged");
                            }
                            else {
                                dos.writeUTF("usernameNoChange");
                                break;
                            }
                        }
                        else if(line.endsWith("changePass")) {
                            loginInfo = user.createFile();
                            System.out.println("The user is trying to change a teacher password");
                            line = line.substring(0, line.length() -10);
                            System.out.println(user.getPassword());
                            user.changeTeacherPass(loginInfo, user.getPassword(), line);
                            dos.writeUTF("passChanged");
                        }

                        else if(line.endsWith("delete")) {
                            loginInfo = user.createFile();
                            System.out.println("User is trying to delete account");
                            dos.writeUTF(user.deleteAccount(loginInfo, user.getUsername(), user.getPassword()));
                        }
                        else if(line.endsWith("createCourse")) {
                            courseList = teacher.courseList();
                            System.out.println(courseList);
                            line = line.substring(0,line.length() -12);
                            if(teacher.addCourse(courseList, line, user.getUsername()).equals("courseAdded")) {
                                teacher.createCourse(line, user.getUsername());
                                System.out.println("course added");
                                dos.writeUTF("courseAdded");
                            }
                            else {
                                System.out.println("Course exists");
                                dos.writeUTF("courseExists");
                                break;
                            }

                        }
                        else if(line.endsWith("checkCourse")) {
                            courseList = teacher.courseList();
                            line = line.substring(0, line.length() - 11);
                            int checker = 0;
                            for(int i = 0; i < courseList.size(); i++) {
                                if(courseList.get(i).contains(line)) {
                                    dos.writeUTF("courseFound");
                                }
                                else {
                                    checker++;
                                }
                            }
                            if(checker == courseList.size()) {
                                dos.writeUTF("courseNotFound");
                            }
                            break;

                        }

                        else if (line.endsWith("courseQuizFile")) {
                            line = line.substring(0, line.length() - 15);
                            String[] fromClient = line.split(",");
                            System.out.println(Arrays.toString(fromClient));
                            System.out.println(user.getUsername());
                            ArrayList<String> quizFile = teacher.uploadQuiz(fromClient[1]);
                            teacher.writeUploadQuiz(fromClient[0], quizFile, user.getUsername());
                            dos.writeUTF("quizAdded");
                            break;
                        }
                        else if (line.endsWith("quizName")) {
                            line = line.substring(0, line.length() - 8);
                            String[] quizNum = line.split(",");
                            ArrayList<String> quiz = new ArrayList<>();
                            quiz.add(quizNum[0]);

                            ArrayList<String> input = new ArrayList<>();
                            input.add(quizNum[1]);
                            teacher.addQuizTerminal(quizNum[0], user.getUsername(),input);
                        }
                        else if(line.endsWith("quizTextUpload")) {
                            line = line.substring(0, line.length() - 14);
                            String[] fromClient = line.split(",");
                            System.out.println(Arrays.toString(fromClient));

                            ArrayList<String> quiz = new ArrayList<String>();

                            // adding elements of array to arrayList.
                            quiz.add(fromClient[1]);
                            quiz.add(fromClient[2]);
                            quiz.add(fromClient[3]);
                            quiz.add(fromClient[4]);
                            quiz.add(fromClient[5]);

                           teacher.addQuizTerminal(fromClient[0], user.getUsername(), quiz);
                           break;

                        }

                        else if(line.endsWith("anotherQ")) {
                            line = line.substring(0, line.length() - 8);
                            String[] fromClient = line.split(",");
                            System.out.println(Arrays.toString(fromClient));
                            ArrayList<String> quiz = new ArrayList<String>();
                            System.out.println(quiz);
//                            quiz.add(fromClient);
                        }
                        else if (line.equals("Exit")) {
                            System.out.println("Client " + this.s + " sends exit...");
                            System.out.println("Closing this connection.");
                            this.s.close();
                            System.out.println("Connection closed");
                            break;
                        }
                    } catch (SocketException se) {
                        s.close();
                        dos.close();
                        dis.close();
                    }
                }
            } catch (IOException e) {
                try {
                    s.close();
                    dis.close();
                    dos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
