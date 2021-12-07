import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

// Client class
public class ClientTest extends JComponent implements Runnable
{
    private static Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    public static void main(String[] args) throws IOException
    {
        try
        {
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            s = new Socket(ip, 4242);

            SwingUtilities.invokeLater(new ClientTest());

//           dis.close();
//           dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized void messageToServer(String message) throws IOException {
        // obtaining input and out streams
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (message != null) {
            try {
                dos.writeUTF(message);
                message = null;
            } catch (Exception e) {
                System.out.println("Message to server could not be sent");
            }
        }
    }

    public static synchronized String messageFromServer() throws IOException {
        // obtaining input and out streams
        while (true) {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            try {
                return dis.readUTF();
            } catch (Exception e) {
                System.out.println("Message to server could not be sent");
            }
        }
    }

    @Override
    public void run() {

        // First Gui
        JFrame frame = new JFrame("Quiz App");
        Container content = frame.getContentPane();
        JButton login = new JButton("Login");
        JButton createUser = new JButton("Create User");
        JPanel panel = new JPanel();
        panel.add(login);
        panel.add(createUser);
        content.add(panel, BorderLayout.NORTH);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                JFrame frame = new JFrame("Create User");
                Container content = frame.getContentPane();
                JButton teacherButton = new JButton("Teacher");
                JButton studentButton = new JButton("Student");
                JPanel panel = new JPanel();
                panel.add(teacherButton);
                panel.add(studentButton);
                content.add(panel, BorderLayout.CENTER);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

                teacherButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);

                        JFrame frame = new JFrame("Create User");
                        Container content = frame.getContentPane();
                        JLabel teacherUsername = new JLabel("Enter Username");
                        JTextField enterTeacherUsername = new JTextField(10);
                        JLabel teacherPassword = new JLabel("Enter Password");
                        JTextField enterTeacherPassword = new JTextField(10);
                        JButton enter = new JButton("Enter");
                        JPanel panel = new JPanel();
                        panel.add(teacherUsername);
                        panel.add(enterTeacherUsername);
                        panel.add(teacherPassword);
                        panel.add(enterTeacherPassword);
                        panel.add(enter);
                        content.add(panel, BorderLayout.CENTER);
                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);
                        enter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    messageToServer(enterTeacherUsername.getText()+"00");
                                    messageToServer(enterTeacherPassword.getText()+"01");
                                    String checkUsername = messageFromServer();
                                    if (checkUsername.equals("name taken")) {
                                        JOptionPane.showMessageDialog(null,
                                                "Username taken", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });

    }

}
