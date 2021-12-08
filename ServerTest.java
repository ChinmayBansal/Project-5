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
                        if (line.substring(line.length() - 2).equals("00")) {
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            user.setUsername(line);
                        }
                        else if (line.substring(line.length() - 2).equals("01")) {
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
                        else if (line.substring(line.length() - 2).equals("02")) {
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            break;
                        }
                        else if (line.substring(line.length() - 2).equals("03")) {
                            System.out.println("This is a teacher username");
                            line = line.substring(0, line.length() - 2);
                            break;
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
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
