import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class UserGui extends JComponent implements Runnable {
    private  JButton login;
    private  JButton createUser;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserGui());

    }

    @Override
    public void run() {
      login();
    }


    void login() {
        JFrame frame = new JFrame("Quiz App");
        Container content = frame.getContentPane();

        login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                information();
            }
        });
        createUser = new JButton("Create User");
//        createUser.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(login);
        panel.add(createUser);
        content.add(panel, BorderLayout.NORTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    void information() {
        JFrame frame = new JFrame("Login");
        Container content = frame.getContentPane();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

//        username.addActionListener(actionListener);
//        password.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);



    }

}
