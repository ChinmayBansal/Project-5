import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

// Client class
public class ClientTest extends JComponent implements Runnable {
    private static Socket s;

    public static void main(String[] args) {
        try {
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            s = new Socket(ip, 4242);


            SwingUtilities.invokeLater(new ClientTest());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void messageToServer(String message) throws IOException {
        // obtaining out stream
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (message != null) {
            try {
                dos.writeUTF(message);
                message = null;
            } catch (Exception e) {
                int i = 0;
                while (i < 2) {
                    System.out.println("Message to server could not be sent");
                    i++;
                }
            }
        }
    }

    public static String messageFromServer() throws IOException {
        // obtaining input stream
        while (true) {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            try {
                return dis.readUTF();
            } catch (Exception e) {
                int i = 0;
                while (i < 2) {
                    System.out.println("Message from server could not be received");
                    i++;
                }
            }
        }
    }

    @Override
    public void run() {

        // First Gui
        JFrame frame1 = new JFrame("Quiz App");
        Container content = frame1.getContentPane();
        JButton login = new JButton("Login");
        JButton createUser = new JButton("Create User");
        JPanel panel = new JPanel();
        panel.add(login);
        panel.add(createUser);
        content.add(panel, BorderLayout.NORTH);
        frame1.setSize(600, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setVisible(true);

        //Once user clicks the create user button//
        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();

                JFrame frame2 = new JFrame("Create User");
                Container content = frame2.getContentPane();
                JButton teacherButton = new JButton("Teacher");
                JButton studentButton = new JButton("Student");
                JPanel panel = new JPanel();
                panel.add(teacherButton);
                panel.add(studentButton);
                content.add(panel, BorderLayout.CENTER);
                frame2.setSize(600, 400);
                frame2.setLocationRelativeTo(null);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setVisible(true);

                //User creating a teacher account//
                teacherButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame2.setVisible(false);

                        JFrame frame3 = new JFrame("Create User");
                        Container content = frame3.getContentPane();
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
                        frame3.setSize(600, 400);
                        frame3.setLocationRelativeTo(null);
                        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame3.setVisible(true);

                        //user submitting the username and password
                        enter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    messageToServer(enterTeacherUsername.getText() + "00");
                                    messageToServer(enterTeacherPassword.getText() + "01");
                                    String checkUsername = messageFromServer();
                                    if (checkUsername.equals("name taken")) {
                                        JOptionPane.showMessageDialog(null,
                                                "Username taken", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "Profile Created", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame3.setVisible(false);
                                        frame1.setVisible(true);
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                });

                //user creating a student account
                studentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame2.setVisible(false);

                        JFrame frame4 = new JFrame("Create User");
                        Container content = frame4.getContentPane();
                        JLabel studentUsername = new JLabel("Enter Username");
                        JTextField enterStudentUsername = new JTextField(10);
                        JLabel studentPassword = new JLabel("Enter Password");
                        JTextField enterStudentPassword = new JTextField(10);
                        JButton enter = new JButton("Enter");
                        JPanel panel = new JPanel();
                        panel.add(studentUsername);
                        panel.add(enterStudentUsername);
                        panel.add(studentPassword);
                        panel.add(enterStudentPassword);
                        panel.add(enter);
                        content.add(panel, BorderLayout.CENTER);
                        frame4.setSize(600, 400);
                        frame4.setLocationRelativeTo(null);
                        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame4.setVisible(true);


                        // user entering username and password
                        enter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    messageToServer(enterStudentUsername.getText() + "00");
                                    messageToServer(enterStudentPassword.getText() + "03");
                                    String checkUsername = messageFromServer();
                                    if (checkUsername.equals("name taken")) {
                                        JOptionPane.showMessageDialog(null,
                                                "Username taken", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "Profile Created", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame4.setVisible(false);
                                        messageToServer("continue000");
                                        String cont = messageFromServer();
                                        if (cont.equals("continue")) {
                                            frame1.setVisible(true);
                                        }
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
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
                System.out.println("Program reaches this far");

                JFrame frame5 = new JFrame("Account Type");
                Container content = frame5.getContentPane();

                JButton teacherLogin = new JButton("Teacher");
                JButton studentLogin = new JButton("Student");
                JPanel panel = new JPanel();
                panel.add(teacherLogin);
                panel.add(studentLogin);
                content.add(panel, BorderLayout.CENTER);
                frame5.setSize(600, 400);
                frame5.setLocationRelativeTo(null);
                frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame5.setVisible(true);


                teacherLogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame5.setVisible(false);

                        JFrame frame6 = new JFrame("Login");
                        Container content = frame6.getContentPane();
                        JLabel teacherUsername = new JLabel("Enter Username");
                        JTextField loginTeacherUsername = new JTextField(10);
                        JLabel teacherPassword = new JLabel("Enter Password");
                        JTextField loginTeacherPassword = new JTextField(10);
                        JButton enterLogin = new JButton("Enter");
                        JPanel panel = new JPanel();
                        panel.add(teacherUsername);
                        panel.add(loginTeacherUsername);
                        panel.add(teacherPassword);
                        panel.add(loginTeacherPassword);
                        panel.add(enterLogin);
                        content.add(panel, BorderLayout.CENTER);
                        frame6.setSize(600, 400);
                        frame6.setLocationRelativeTo(null);
                        frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame6.setVisible(true);


                        enterLogin.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    int checker = 0;
                                    messageToServer(loginTeacherUsername.getText() + "04");
                                    String checkUsername = messageFromServer();
                                    if (checkUsername.equals("username match")) {
                                        checker++;
                                    }
                                    messageToServer(loginTeacherPassword.getText() + "05");
                                    String checkTeacherPass = messageFromServer();
                                    if (checkTeacherPass.equals("pass match")) {
                                        checker++;
                                    }

                                    if (checker == 2) {
                                        System.out.println("User logged in");
                                        JOptionPane.showMessageDialog(null,
                                                "Successful Login", "Teacher",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame1.setVisible(false);
                                        frame6.setVisible(false);

                                        //teacher menu
                                        JFrame teacherMenuFrame = new JFrame("Teacher Menu");

                                        Container content = teacherMenuFrame.getContentPane();

                                        JButton createCourse = new JButton("Create Course");
                                        createCourse.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenuFrame.dispose();
                                                teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                JFrame frame = new JFrame("Create Course");
                                                Container content = frame.getContentPane();
                                                JLabel question = new JLabel("What do you want to name" +
                                                        " the course?");
                                                JLabel question2 = new JLabel("Two letters and 3 numbers" +
                                                        " Ex. CS180");
                                                JTextField courseName = new JTextField(10);
                                                JButton enter = new JButton("Enter");

                                                //if the course is found
                                                enter.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        try {
                                                            messageToServer(courseName.getText() + "createCourse");
                                                            String courseOrNot = ClientTest.messageFromServer();
                                                            System.out.println(courseOrNot);
                                                            if (courseOrNot.equals("courseAdded")) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Course has been added",
                                                                        "Adding a Course",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                frame.setVisible(false);
                                                                teacherMenuFrame.setVisible(true);

                                                            } else {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Course cannot be added",
                                                                        "Adding a Course",
                                                                        JOptionPane.ERROR_MESSAGE);
                                                                courseName.setText("");
                                                            }
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                    }
                                                });
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
                                        });

                                        JButton editCourse = new JButton("Edit Course");
                                        editCourse.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenuFrame.dispose();
                                                teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                JFrame frame = new JFrame("Edit Course");

                                                Container content = frame.getContentPane();
                                                JLabel question = new JLabel(
                                                        "Which course would you like to edit?");
                                                JTextField courseEdit = new JTextField(10);
                                                JButton enter = new JButton("Enter");
                                                enter.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        try {
                                                            messageToServer(courseEdit.getText() + "checkCourse");
                                                            String courseFound = messageFromServer();
                                                            System.out.println(courseFound);
                                                            if (courseFound.equals("courseFound")) {


                                                                frame.dispose();
                                                                frame.setDefaultCloseOperation(
                                                                        JFrame.DISPOSE_ON_CLOSE);
                                                                JFrame frame = new JFrame("Add Quiz");
                                                                Container content = frame.getContentPane();
                                                                JLabel message = new JLabel(
                                                                        "Add through text or file?");
                                                                JButton text = new JButton("Text");
                                                                //text option
                                                                text.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        frame.dispose();
                                                                        frame.setDefaultCloseOperation(
                                                                                JFrame.DISPOSE_ON_CLOSE);
                                                                        JFrame QuizText = new JFrame("Enter Quiz" +
                                                                                " Through Text");
                                                                        Container content = QuizText.getContentPane();
                                                                        JLabel courseNameLabel = new JLabel(
                                                                                "Course Name");
                                                                        JTextField courseNameText = new JTextField(
                                                                                6);
                                                                        JLabel message = new JLabel("Enter " +
                                                                                "Quiz Number");
                                                                        JTextField quizNum = new JTextField(1);
                                                                        JLabel random = new JLabel("Random? Enter" +
                                                                                " 't' or 'f'");
                                                                        JTextField randomText = new JTextField(
                                                                                1);
                                                                        JLabel questionLabel = new JLabel(
                                                                                "Question:");
                                                                        JTextField questionText = new JTextField(
                                                                                20);
                                                                        JTextField aChoice = new JTextField("a. ",
                                                                                15);
                                                                        JTextField bChoice = new JTextField("b. "
                                                                                , 15);
                                                                        JTextField cChoice = new JTextField("c. ",
                                                                                15);
                                                                        JTextField dChoice = new JTextField("d. ",
                                                                                15);

                                                                        JButton continueButton = new JButton(
                                                                                "Enter");
                                                                        JButton addAnother = new JButton("Add" +
                                                                                " Another Question");
                                                                        JButton newEnter = new JButton(
                                                                                "Multiple Question Enter");
                                                                        continueButton.addActionListener(
                                                                                new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e)
                                                                                    {
                                                                                        try {
                                                                                            messageToServer(courseNameText.
                                                                                                    getText() + ",Quiz " +
                                                                                                    quizNum.getText() +
                                                                                                    randomText.getText() +
                                                                                                    "quizName");
                                                                                            messageToServer(
                                                                                                    courseNameText.getText()
                                                                                                            + "," +
                                                                                                            questionText
                                                                                                                    .getText()
                                                                                                            + ","+aChoice.
                                                                                                            getText() + "," +
                                                                                                            bChoice.getText() +
                                                                                                            "," + cChoice.
                                                                                                            getText() + "," +
                                                                                                            dChoice.getText() +
                                                                                                            "quizTextUpload");

                                                                                        } catch (IOException ex) {
                                                                                            ex.printStackTrace();
                                                                                        }
                                                                                        QuizText.dispose();
                                                                                        teacherMenuFrame.setVisible(true);
                                                                                    }
                                                                                });
                                                                        addAnother.addActionListener(
                                                                                new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        try {
                                                                                            messageToServer(courseNameText.
                                                                                                    getText() + ",Quiz " +
                                                                                                    quizNum.getText() +
                                                                                                    randomText.getText() +
                                                                                                    "quizName");
                                                                                            messageToServer(courseNameText.
                                                                                                    getText() + "," +
                                                                                                    questionText.getText() +
                                                                                                    ","+aChoice.getText() + ","
                                                                                                    + bChoice.getText() + "," +
                                                                                                    cChoice.getText() + "," +
                                                                                                    dChoice.getText() +
                                                                                                    "quizTextUpload");
                                                                                            continueButton.setVisible(false);
                                                                                            newEnter.addActionListener(
                                                                                                    new ActionListener() {
                                                                                                        @Override
                                                                                                        public void actionPerformed(
                                                                                                                ActionEvent e) {
                                                                                                            try {
                                                                                                                messageToServer(
                                                                                                                        courseNameText.getText()
                                                                                                                                + "," + questionText.
                                                                                                                                getText()
                                                                                                                                + "," + aChoice.
                                                                                                                                getText() +
                                                                                                                                "," +
                                                                                                                                bChoice.getText() +
                                                                                                                                "," + cChoice.getText()
                                                                                                                                + "," + dChoice.
                                                                                                                                getText() +
                                                                                                                                "quizText" +
                                                                                                                                "Upload");

                                                                                                                QuizText.dispose();
                                                                                                                teacherMenuFrame.
                                                                                                                        setVisible(true);
                                                                                                            } catch (IOException ioe) {
                                                                                                                ioe.printStackTrace();
                                                                                                            }
                                                                                                        }
                                                                                                    });


                                                                                        } catch (IOException ex) {
                                                                                            ex.printStackTrace();
                                                                                        }
                                                                                        questionText.setText("");
                                                                                        aChoice.setText("a.");
                                                                                        bChoice.setText("b.");
                                                                                        cChoice.setText("c.");
                                                                                        dChoice.setText("d.");
                                                                                        try {
                                                                                            messageToServer(
                                                                                                    courseNameText.getText() + "," +
                                                                                                            questionText.getText() +
                                                                                                            "," +aChoice.getText() +
                                                                                                            "," + bChoice.getText() +
                                                                                                            "," + cChoice.getText() +
                                                                                                            "," + dChoice.getText() +
                                                                                                            "anotherQ");
                                                                                        } catch (IOException ex) {
                                                                                            ex.printStackTrace();
                                                                                        }
                                                                                    }
                                                                                });
                                                                        JPanel panelText = new JPanel();
                                                                        panelText.add(courseNameLabel);
                                                                        panelText.add(courseNameText);
                                                                        panelText.add(message);
                                                                        panelText.add(quizNum);
                                                                        panelText.add(random);
                                                                        panelText.add(randomText);
                                                                        panelText.add(questionLabel);
                                                                        panelText.add(questionText);
                                                                        panelText.add(aChoice);
                                                                        panelText.add(bChoice);
                                                                        panelText.add(cChoice);
                                                                        panelText.add(dChoice);

                                                                        JPanel panelTextButton = new JPanel();
                                                                        panelTextButton.add(addAnother);
                                                                        panelTextButton.add(continueButton);
                                                                        panelTextButton.add(newEnter);

                                                                        content.add(panelTextButton, BorderLayout.
                                                                                SOUTH);
                                                                        content.add(panelText, BorderLayout.CENTER);
                                                                        QuizText.setSize(600, 600);
                                                                        QuizText.setLocationRelativeTo(null);
                                                                        QuizText.setDefaultCloseOperation(
                                                                                JFrame.DISPOSE_ON_CLOSE);
                                                                        QuizText.setVisible(true);



                                                                    }
                                                                });

                                                                JButton file = new JButton("File");
                                                                //file
                                                                file.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        frame.dispose();
                                                                        frame.setDefaultCloseOperation(
                                                                                JFrame.DISPOSE_ON_CLOSE);

                                                                        JFrame frame = new JFrame("File Upload");
                                                                        Container content = frame.getContentPane();
                                                                        JLabel message = new JLabel(
                                                                                "Enter name of course");
                                                                        JTextField courseName = new JTextField(
                                                                                10);
                                                                        JLabel message2 = new JLabel(
                                                                                "Enter file name");
                                                                        JTextField filename = new JTextField(
                                                                                10);
                                                                        JButton continueButton = new JButton(
                                                                                "Continue");
                                                                        continueButton.addActionListener(
                                                                                new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(
                                                                                            ActionEvent e) {
                                                                                        frame.dispose();
                                                                                        try {
                                                                                            messageToServer(courseName.
                                                                                                    getText() + "," +
                                                                                                    filename.getText()
                                                                                                    + ",courseQuizFile"
                                                                                            );
                                                                                            String added =
                                                                                                    messageFromServer();
                                                                                            if (added.equals("" +
                                                                                                    "quizAdded")
                                                                                            ) {
                                                                                                JOptionPane.
                                                                                                        showMessageDialog(
                                                                                                                null,
                                                                                                                "Quiz added",
                                                                                                                "Adding a quiz",
                                                                                                                JOptionPane.
                                                                                                                        INFORMATION_MESSAGE);
                                                                                                frame.setVisible(false);
                                                                                                teacherMenuFrame.
                                                                                                        setVisible
                                                                                                                (true);
                                                                                            } else {
                                                                                                JOptionPane.
                                                                                                        showMessageDialog(
                                                                                                                null,
                                                                                                                "Quiz could not" +
                                                                                                                        " be added",
                                                                                                                "Adding a quiz",
                                                                                                                JOptionPane.
                                                                                                                        INFORMATION_MESSAGE);
                                                                                                frame.setVisible(false);
                                                                                                teacherMenuFrame.
                                                                                                        setVisible(
                                                                                                                true);
                                                                                            }
                                                                                        } catch (IOException ex) {
                                                                                            ex.printStackTrace();
                                                                                        }
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
                                                                        frame.setDefaultCloseOperation(
                                                                                JFrame.DISPOSE_ON_CLOSE);
                                                                        frame.setVisible(true);
                                                                    }
                                                                });
                                                                JPanel panel = new JPanel();
                                                                panel.add(message);
                                                                panel.add(text);
                                                                panel.add(file);
                                                                content.add(panel, BorderLayout.CENTER);
                                                                frame.setSize(600, 600);
                                                                frame.setLocationRelativeTo(null);
                                                                frame.setDefaultCloseOperation(
                                                                        JFrame.DISPOSE_ON_CLOSE);
                                                                frame.setVisible(true);


                                                            } else {
                                                                frame.dispose();
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Course Not found",
                                                                        "Course Error", JOptionPane.ERROR_MESSAGE);
                                                                teacherMenuFrame.setVisible(true);
                                                            }
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                        frame.dispose();
                                                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                                    }

                                                });

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
                                        });

                                        JButton viewSubmissions = new JButton("View Submissions");
                                        viewSubmissions.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenuFrame.dispose();
                                                TeacherGUI.printCourses();
                                                teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            }
                                        });

                                        JButton editInformation = new JButton("Edit Information");
                                        editInformation.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenuFrame.dispose();
                                                JFrame editInformation1 = new JFrame("Edit Information");
                                                Container content = editInformation1.getContentPane();
                                                JLabel question = new JLabel("Choose which one to change");
                                                JButton userName = new JButton("Username");
                                                userName.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        editInformation1.dispose();
                                                        JFrame frameTeacherName = new JFrame("Username Field");

                                                        Container content = frameTeacherName.getContentPane();
                                                        JLabel message = new JLabel("Enter new username: ");
                                                        JTextField userName = new JTextField(12);
                                                        JButton enterUsername = new JButton("Enter");
                                                        enterUsername.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                frameTeacherName.dispose();
                                                                try {
                                                                    messageToServer(userName.getText() +
                                                                            "changeUsername");
                                                                    String newName = messageFromServer();

                                                                    if (newName.equals("usernameChanged")) {
                                                                        JOptionPane.showMessageDialog(
                                                                                null,
                                                                                "Username Changed"
                                                                                , "Teacher",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        teacherMenuFrame.setVisible(true);
                                                                        frameTeacherName.setVisible(false);

                                                                    } else {
                                                                        JOptionPane.showMessageDialog(
                                                                                null,
                                                                                "Username could not be" +
                                                                                        " changed,",
                                                                                "Teacher",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        teacherMenuFrame.setVisible(true);
                                                                    }
                                                                } catch (IOException ex) {
                                                                    ex.printStackTrace();
                                                                }
                                                                frameTeacherName.setDefaultCloseOperation
                                                                        (JFrame.DISPOSE_ON_CLOSE);
                                                            }
                                                        });
                                                        JPanel panel = new JPanel();
                                                        panel.add(message);
                                                        panel.add(userName);
                                                        panel.add(enterUsername);
                                                        content.add(panel, BorderLayout.CENTER);
                                                        frameTeacherName.setSize(600, 600);
                                                        frameTeacherName.setLocationRelativeTo(null);
                                                        frameTeacherName.setDefaultCloseOperation
                                                                (JFrame.DISPOSE_ON_CLOSE);
                                                        frameTeacherName.setVisible(true);
                                                        editInformation1.setDefaultCloseOperation
                                                                (JFrame.DISPOSE_ON_CLOSE);
                                                    }
                                                });
                                                JButton password = new JButton("Password");
                                                password.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        editInformation1.dispose();
                                                        JFrame frame = new JFrame("Password Field");

                                                        Container content = frame.getContentPane();
                                                        JLabel message = new JLabel("Enter new password: ");
                                                        JTextField changePassword = new JTextField(12);
                                                        JButton enterPassword = new JButton("Enter");
                                                        enterPassword.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                frame.dispose();
                                                                try {
                                                                    messageToServer(changePassword.getText()
                                                                            + "changePass");
                                                                    String newPass = messageFromServer();
                                                                    if (newPass.equals("passChanged")) {
                                                                        JOptionPane.showMessageDialog(
                                                                                null,
                                                                                "Password changed,",
                                                                                "Teacher",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        teacherMenuFrame.setVisible(true);
                                                                    }

                                                                } catch (Exception ex) {
                                                                    ex.printStackTrace();
                                                                }
                                                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                            }
                                                        });
                                                        JPanel panel = new JPanel();
                                                        panel.add(message);
                                                        panel.add(changePassword);
                                                        panel.add(enterPassword);
                                                        content.add(panel, BorderLayout.CENTER);
                                                        frame.setSize(600, 600);
                                                        frame.setLocationRelativeTo(null);
                                                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                        frame.setVisible(true);
                                                        editInformation1.setDefaultCloseOperation
                                                                (JFrame.DISPOSE_ON_CLOSE);
                                                    }
                                                });
                                                JButton deleteAccount = new JButton("Delete Account");
                                                deleteAccount.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        editInformation1.dispose();
                                                        JFrame frame = new JFrame("Delete Account");
                                                        Container content = frame.getContentPane();
                                                        JLabel message = new JLabel("Delete account");
                                                        JButton continueDelete = new JButton("Continue");
                                                        JButton exitDelete = new JButton("No");
                                                        JPanel panel = new JPanel();
                                                        panel.add(message);
                                                        panel.add(continueDelete);
                                                        panel.add(exitDelete);
                                                        content.add(panel, BorderLayout.CENTER);
                                                        frame.setSize(600, 600);
                                                        frame.setLocationRelativeTo(null);
                                                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                        frame.setVisible(true);
                                                        editInformation1.setDefaultCloseOperation
                                                                (JFrame.DISPOSE_ON_CLOSE);
                                                        continueDelete.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                try {
                                                                    messageToServer("delete");
                                                                    String deleteOrNot = messageFromServer();
                                                                    if (deleteOrNot.equals("AccountDelete")) {
                                                                        JOptionPane.showMessageDialog(
                                                                                null,
                                                                                "Account Deleted",
                                                                                "Delete Account",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        frame.setVisible(false);
                                                                        frame1.setVisible(true);
                                                                    }
                                                                } catch (IOException ex) {
                                                                    ex.printStackTrace();
                                                                }
                                                            }
                                                        });

                                                    }
                                                });
                                                JPanel panel = new JPanel();
                                                panel.add(question);
                                                panel.add(userName);
                                                panel.add(password);
                                                panel.add(deleteAccount);
                                                content.add(panel, BorderLayout.CENTER);
                                                editInformation1.setSize(600, 600);
                                                editInformation1.setLocationRelativeTo(null);
                                                editInformation1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                editInformation1.setVisible(true);
                                                teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            }
                                        });

                                        JButton logoutButton = new JButton("Logout");
                                        logoutButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenuFrame.dispose();
                                                JOptionPane.showMessageDialog(null,
                                                        "Thank you using Quiz App", "Logout",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                try {
                                                    messageToServer("Exit");
                                                } catch (IOException ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                        });
                                        JPanel panel = new JPanel();
                                        panel.add(createCourse);
                                        panel.add(editCourse);
                                        panel.add(viewSubmissions);
                                        panel.add(editInformation);
                                        panel.add(logoutButton);
                                        content.add(panel, BorderLayout.CENTER);
                                        teacherMenuFrame.setSize(600, 600);
                                        teacherMenuFrame.setLocationRelativeTo(null);
                                        teacherMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        teacherMenuFrame.setVisible(true);
                                        System.out.println("Code reaches this far");

                                    } else {
                                        System.out.println("Invalid login");
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid Login", "Error",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame6.setVisible(false);
                                        frame1.setVisible(true);
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                });

                studentLogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame5.setVisible(false);

                        JFrame frame7 = new JFrame("Login");
                        Container content = frame7.getContentPane();
                        JLabel studentUsername = new JLabel("Enter Username");
                        JTextField loginStudentUsername = new JTextField(10);
                        JLabel studentPassword = new JLabel("Enter Password");
                        JTextField loginStudentPassword = new JTextField(10);
                        JButton enterLogin = new JButton("Enter");
                        JPanel panel = new JPanel();
                        panel.add(studentUsername);
                        panel.add(loginStudentUsername);
                        panel.add(studentPassword);
                        panel.add(loginStudentPassword);
                        panel.add(enterLogin);
                        content.add(panel, BorderLayout.CENTER);
                        frame7.setSize(600, 400);
                        frame7.setLocationRelativeTo(null);
                        frame7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame7.setVisible(true);

                        enterLogin.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    int checker = 0;
                                    messageToServer(loginStudentUsername.getText() + "04");
                                    String checkUsername = messageFromServer();
                                    if (checkUsername.equals("username match")) {
                                        checker++;
                                    }
                                    messageToServer(loginStudentPassword.getText() + "06");
                                    String checkTeacherPass = messageFromServer();
                                    if (checkTeacherPass.equals("pass match")) {
                                        checker++;
                                    }
                                    if (checker == 2) {
                                        System.out.println("Client logged in");
                                        JOptionPane.showMessageDialog(null,
                                                "Successful Login", "Student",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame7.setVisible(false);
                                        StudentGUI.studentMenu();
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid Login", "Error",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        frame7.setVisible(false);
                                        frame1.setVisible(true);

                                    }
                                } catch (IOException ex) {
                                    int i = 0;
                                    while (i < 2) {
                                        ex.printStackTrace();
                                        i++;
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
