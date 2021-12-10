import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class StudentGUI extends JComponent implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());

    }
    @Override
    public void run() {
        menu();
    }

    void menu() {

        //student menu
        JFrame frame = new JFrame("Student Menu");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();

        JButton quizButton = new JButton("Take Quiz");
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                courseList();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });


        JButton editButton = new JButton("Edit Information");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editInformation();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                logoutScreen();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(quizButton);
        panel.add(editButton);
        panel.add(logoutButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    void courseList() {
        JFrame frame = new JFrame("Course List");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course do you want to select?");
        JTextField courseName = new JTextField(10);
        JButton enter = new JButton("Enter");

        //if the course is found
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                takeQuiz();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });


        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(courseName);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


    void editInformation() {
        JFrame frame = new JFrame("Edit Information");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Choose which one to change");
        JButton userName = new JButton("Username");
        userName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                newUsername();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JButton password = new JButton("Password");
        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                newPassword();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteAccount();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(userName);
        panel.add(password);
        panel.add(deleteAccount);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void logoutScreen() {
        JFrame frame = new JFrame("Logout");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Thank you for using the Quiz App");
        JPanel panel = new JPanel();
        panel.add(message);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


    //second slides
    void courseNotFound() {
        JFrame frame = new JFrame("Error!");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Error! \n Enter Another Course Name");
        JTextField courseName = new JTextField(10);
        JButton continueButton = new JButton("Continue");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(courseName);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void takeQuiz() {
        JFrame frame = new JFrame("Take Quiz");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which quiz do you want to take?");
        JTextField quizNumber = new JTextField(10);
        JButton enter = new JButton("Enter");
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(quizNumber);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    //third slides

    void quizPage() {
        JFrame frame = new JFrame("Quiz Page");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Do you want write the answers through text or upload a file?");
        JButton text = new JButton("Text");
        //text option
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                writeByText();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JButton file = new JButton("File");
        //file
        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                uploadFile();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(text);
        panel.add(file);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void newUsername() {
        JFrame frame = new JFrame("Username Field");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Enter new username: ");
        JTextField courseName = new JTextField(12);
        JButton continueButton = new JButton("Enter");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                confirmation();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(courseName);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void newPassword() {
        JFrame frame = new JFrame("Password Field");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Enter new password: ");
        JTextField courseName = new JTextField(12);
        JButton continueButton = new JButton("Enter");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                confirmation();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(courseName);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    void deleteAccount() {
        JFrame frame = new JFrame("Delete Account");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Delete account");
        JButton b = new JButton("Continue");
        JButton c = new JButton("No");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(b);
        panel.add(c);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


    //fourth slides

    void quizNotFound() {
        JFrame frame = new JFrame("Error!");
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Error! \n Enter Another Course Name");
        JTextField courseName = new JTextField(10);
        JButton continueButton = new JButton("Continue");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(courseName);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void writeByText() {
        JFrame frame = new JFrame("File Upload");
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Enter Quiz");
        JTextField quiz = new JTextField(10);
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(quiz);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void uploadFile() {
        JFrame frame = new JFrame("File Upload");
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Enter name of course");
        JTextField courseName = new JTextField(10);
        JLabel message2 = new JLabel("Enter file name");
        JTextField filename = new JTextField(10);
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(courseName);
        panel.add(message2);
        panel.add(filename);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    void confirmation() {
        JFrame f = new JFrame("Operation Complete");
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container c = f.getContentPane();
        JTextField a = new JTextField(
                "Update Complete"
        );
        JButton finish = new JButton("Finish");
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(a);
        panel.add(finish);
        c.add(a, panel);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

    }
}
