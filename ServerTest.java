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
                        if (line.endsWith("00")) {
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            user.setUsername(line);
                        }
                        else if (line.endsWith("01")) {
                            System.out.println("This is a teacher password");
                            line = line.substring(0, line.length() - 2);
                            String nameTaken = user.createName(loginInfo, user.getUsername(),false);
                            if (nameTaken.equals("taken")) {
                                dos.writeUTF("name taken");
                            } else {
                                user.teacherPass(loginInfo,line,user.getUsername());
                                dos.writeUTF("name available");
                                break;
                            }
                        }
                        else if (line.endsWith("02")) {
                            System.out.println("This is a student username");
                            line = line.substring(0, line.length() - 2);
                             user.setUsername(line);
                            break;
                        }
                        else if (line.endsWith("03")) {
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
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            String nameMatch = user.checkUsername(loginInfo,line);
                            if(nameMatch.equals("username match")) {
                                dos.writeUTF("username match");
                            }
                            else {
                                dos.writeUTF("username wrong");
                                break;
                            }
                        }

                        else if (line.endsWith("05")) {
                            System.out.println("This is a teacher password");
                            line = line.substring(0, line.length() - 2);
                            String passMatch = user.checkTeacher(loginInfo,line);
                            if(passMatch.equals("pass match")) {
                                dos.writeUTF("pass match");
                            }
                            else {
                                dos.writeUTF("pass wrong");
                                break;
                            }
                        }

                        else if (line.endsWith("06")) {
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

                        else if (line.equals("Exit")) {
                            System.out.println("Client " + this.s + " sends exit...");
                            System.out.println("Closing this connection.");
                            this.s.close();
                            System.out.println("Connection closed");
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (line.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    this.dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    this.dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
