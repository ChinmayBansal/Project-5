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

        JButton option1 = new JButton("Create Course");
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createCourse();
            }
        });

        JButton option2 = new JButton("Edit Course");
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editCourse();
            }
        });

        JButton option3 = new JButton("View Submissions");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewSubmissions();
            }
        });

        JButton option4 = new JButton("Edit Information");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editInformation();
            }
        });

        JButton option5 = new JButton("Logout");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Logout();
            }
        });


        createUser = new JButton("Create User");
//        createUser.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(option5);
        content.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    void createCourse() {
        JFrame frame = new JFrame("Create Course");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("What do you want to name the course?");
        JLabel question2 = new JLabel("Two letters and 3 numbers Ex. CS180");
        JTextField courseName = new JTextField(10);
        JButton enter = new JButton("Enter");

//        username.addActionListener(actionListener);
//        password.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(question2);
        panel.add(courseName);

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
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void viewSubmissions() {
        JFrame frame = new JFrame("View Submissions");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void editInformation() {
        JFrame frame = new JFrame("Edit Information");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void Logout() {
        JFrame frame = new JFrame("Logout");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password");
        JTextField password = new JTextField(10);
        JButton enter = new JButton("Enter");

        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
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
