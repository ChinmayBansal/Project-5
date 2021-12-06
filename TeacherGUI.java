import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class TeacherGUI extends JComponent implements Runnable {
    private  JButton login;
    private  JButton createUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TeacherGUI());

    }
    @Override
    public void run() {
        menu();
    }

    void menu() {
        JFrame frame = new JFrame("Teacher Menu");
        Container content = frame.getContentPane();

        JButton optionsMenu = new JButton("Create Course");
        optionsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createCourse();
            }
        });
        JButton edit_course = new JButton("Edit Course");
        edit_course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editCourse();
            }
        });

        JButton view_submissions = new JButton("View Submissions");
        view_submissions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewSubmissions();
            }
        });

        JButton edit_information = new JButton("Edit Information");
        edit_information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editInformation();
            }
        });

        JButton logout = new JButton("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                logout();
            }
        });

        createUser = new JButton("Create User");
//        createUser.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(optionsMenu);
        panel.add(edit_course);
        panel.add(view_submissions);
        panel.add(edit_information);
        panel.add(logout);
        content.add(panel, BorderLayout.NORTH);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    void createCourse() {
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

    void editCourse() {
        JFrame frame = new JFrame("Edit Course");
        Container content = frame.getContentPane();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

    }

    void viewSubmissions() {
        JFrame frame = new JFrame("View Submissions");
        Container content = frame.getContentPane();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

    }

    void editInformation() {
        JFrame frame = new JFrame("Edit Information");
        Container content = frame.getContentPane();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

    }

    void logout() {
        JFrame frame = new JFrame("Logout");
        Container content = frame.getContentPane();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

    }
}
