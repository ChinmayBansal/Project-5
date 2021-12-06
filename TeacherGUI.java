import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherGUI extends JComponent implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TeacherGUI());

    }
    @Override
    public void run() {
        menu();
    }

    void menu() {

        //teacher menu
        JFrame frame = new JFrame("Teacher Menu");
        Container content = frame.getContentPane();

        JButton option1 = new JButton("Create Course");
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createCourse();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option2 = new JButton("Edit Course");
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editCourse();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option3 = new JButton("View Submissions");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewSubmissions();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option4 = new JButton("Edit Information");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editInformation();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option5 = new JButton("Logout");
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                logoutScreen();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(option5);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
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

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void editCourse() {
        JFrame frame = new JFrame("Edit Course");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JButton enter = new JButton("Enter");
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void viewSubmissions() {
        JFrame frame = new JFrame("View Submissions");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to view submissions for?");
        JTextField courseEdit = new JTextField(10);
        JButton enter = new JButton("Enter");
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseEdit);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void editInformation() {
        JFrame frame = new JFrame("Edit Information");
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Choose which one to change");
        JButton userName = new JButton("Username");
        JButton password = new JButton("Password");
        JButton deleteAccount = new JButton("Delete Account");
        JButton enter = new JButton("Enter");
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(userName);
        panel.add(password);
        panel.add(deleteAccount);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void logoutScreen() {
        JFrame frame = new JFrame("Logout");
        JLabel message = new JLabel("Thank you for using the Quiz App");
        JPanel panel = new JPanel();
        panel.add(frame);
        panel.add(message);
        panel.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


}
