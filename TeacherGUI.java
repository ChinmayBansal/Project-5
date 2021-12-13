import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TeacherGUI extends JComponent implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TeacherGUI());
    }
    @Override
    public void run() {
        //main page
        menu();
    }

    void menu() {
        //teacher menu
        JFrame frame = new JFrame("Teacher Menu");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
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
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editInformation();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option5 = new JButton("View Submissions");
        option5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                logoutScreen();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton option6 = new JButton("Logout");
        option5.addActionListener(new ActionListener() {
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
        panel.add(option6);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    void createCourse() {
        JFrame frame_cc = new JFrame("Create Course");
        //closes program if user closes gui
        frame_cc.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
            }
        });
        Container content = frame_cc.getContentPane();
        JLabel question = new JLabel("What do you want to name the course?");
        JLabel question2 = new JLabel("Two letters and 3 numbers Ex. CS180");
        JTextField courseName = new JTextField(10);
        JButton enter = new JButton("Enter");
        //**if the course is found
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_cc.dispose();
                addedConformation();
                frame_cc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //**if the course is not found
//        JOptionPane.showMessageDialog(frame_cc, "Error, course not found");
//        frame_cc.setVisible(false);
        JPanel panel = new JPanel();
        panel.add(question);
        panel.add(question2);
        panel.add(courseName);
        panel.add(enter);
        content.add(panel, BorderLayout.CENTER);
        frame_cc.setSize(600, 600);
        frame_cc.setLocationRelativeTo(null);
        frame_cc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_cc.setVisible(true);
    }

    void editCourse() {
        JFrame frame = new JFrame("Edit Course");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to edit?");
        JTextField courseEdit = new JTextField(10);
        JButton enter = new JButton("Enter");
        //if the course is found
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editQuestion();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //**if the course is not found
//        JOptionPane.showMessageDialog(frame, "Error, course not found");
//        frame.setVisible(false);

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
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel question = new JLabel("Which course would you like to view submissions for?");
        JTextField courseEdit = new JTextField(10);
        JButton enter = new JButton("Enter");

        //add how to check course
         boolean fileChecker = Student.checkFile(courseEdit.getText());

        if (fileChecker) {
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    printCourses();
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            });
        } else {
         JOptionPane.showMessageDialog(frame, "Error, course is not found");
         frame.setVisible(false);
        }

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


    //second set of frames

    void addedConformation() {
        JFrame frame = new JFrame("Course Added!");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Course Added! \n Click button to continue");
        JButton continueButton = new JButton("Continue");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    static void printCourses() {
        JFrame frame = new JFrame("Submissions");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Input the file to view");
        JTextField inputFile = new JTextField(10);
        JButton confirm = new JButton("confirm");

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    File readFile = new File( inputFile.getText()+ ".txt");
                    FileReader fileReader = new FileReader(readFile);
                    BufferedReader br = new BufferedReader(fileReader);
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception i) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid File",
                            "Teacher",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(confirm);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void editQuestion() {
        JFrame frame = new JFrame("Edit Course");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Would you like to add or remove a quiz?");
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                inputOrFile();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JButton remove = new JButton("Remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteQuiz();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(add);
        panel.add(remove);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    //third slides
    void inputOrFile() {
        JFrame frame = new JFrame("Add Quiz");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Add through text or file?");
        JButton text = new JButton("Text");
        //text option
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                inputQuiz();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        JButton file = new JButton("File");
        //file
        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                fileUpload();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(text);
        panel.add(file);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    void deleteQuiz() {
        JFrame frame = new JFrame("Delete Quiz");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("What is the name of the quiz?");
        JTextField courseName = new JTextField(10);
        JButton continueButton = new JButton("Continue");

        //if course is found
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addedConformation2();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

//        //if course is not found
//        continueButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                courseNotFound2();
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            }
//        });


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
                updateConfirmation();
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
                updateConfirmation();
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
    void addedConformation2() {
        JFrame frame = new JFrame("Course Added!");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Course Added! \n Click button to continue");
        JButton continueButton = new JButton("Continue");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(continueButton);
        content.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    void inputQuiz() {
        JFrame frame = new JFrame("File Upload");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Enter Quiz");
        JTextField quiz = new JTextField(10);
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                randomization();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    void fileUpload() {
        JFrame frame = new JFrame("File Upload");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
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

    void updateConfirmation() {
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

    void randomization() {
        JFrame frame = new JFrame("Randomization");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Client force closed");
                //messageToServer("closing");
            }
        });
        Container content = frame.getContentPane();
        JLabel message = new JLabel("Would you like to randomize this quiz?");
        JButton yes = new JButton("Yes");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Quiz Uploaded");
                frame.setVisible(false);
            }
        });
        JButton no = new JButton("No");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Quiz Uploaded");
                frame.setVisible(false);
            }
        });
        JPanel p = new JPanel();
        p.add(message);
        p.add(yes);
        p.add(no);

        content.add(p, BorderLayout.CENTER);

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
