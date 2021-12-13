import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;


public class StudentGUI extends JComponent implements Runnable {

    public static String savedUsername = null;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());

    }

    @Override
    public void run() {
        studentMenu();
    }

    static void studentMenu() {

        //teacher menu
        JFrame frame1 = new JFrame("Student Menu");
        Container content = frame1.getContentPane();
        JPanel panel = new JPanel();
        JButton option1 = new JButton("Take Quiz");
        JButton option2 = new JButton("Edit Information");


        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                try {
                    takeQuiz();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Information", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                JFrame frame6 = new JFrame("Student Menu");
                Container content = frame6.getContentPane();
                JPanel panel = new JPanel();
                JButton option1 = new JButton("Logout");
                JButton option2 = new JButton("Edit Username");
                JButton option3 = new JButton("Edit Password");
                panel.add(option1);
                panel.add(option2);
                panel.add(option3);
                content.add(panel, BorderLayout.CENTER);
                frame6.setSize(600, 600);
                frame6.setLocationRelativeTo(null);
                frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame6.setVisible(true);
                frame6.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        try {
                            System.out.println("Client force closed");
                            ClientTest.messageToServer("closing");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    System.out.println("Client force closed");
                    ClientTest.messageToServer("closing");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(option1);
        panel.add(option2);
        content.add(panel, BorderLayout.CENTER);
        frame1.setSize(600, 600);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setVisible(true);
    }

    static void takeQuiz() throws IOException {
        JFrame frame2 = new JFrame("Student Menu");
        Container content = frame2.getContentPane();
        JPanel panel = new JPanel();
        JLabel message1 = new JLabel("Input Course");
        JTextField courseSelection = new JTextField(10);
        JLabel message2 = new JLabel("Input Quiz Number");
        JTextField quizSelection = new JTextField(10);
        JButton enterCourseAndQuiz = new JButton("Enter");
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    System.out.println("Client force closed");
                    ClientTest.messageToServer("closing");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        enterCourseAndQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {

                    ClientTest.messageToServer(courseSelection.getText() + "studentCourseChoice");
                    ClientTest.messageToServer(quizSelection.getText() + "studentQuizChoice");
                    String random = ClientTest.messageFromServer();
                    savedUsername = ClientTest.messageFromServer();
                    String courseSelected = courseSelection.getText();
                    int quizNumber = Integer.parseInt(quizSelection.getText());
                    printQuiz(courseSelection.getText(), quizSelection.getText(), Boolean.parseBoolean(random));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Course Or Quiz Selection", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panel.add(message1);
        panel.add(courseSelection);
        panel.add(message2);
        panel.add(quizSelection);
        panel.add(enterCourseAndQuiz);
        content.add(panel, BorderLayout.CENTER);
        frame2.setSize(600, 600);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setVisible(true);
    }

    public static void printQuiz(String fileName, String quizNumber, boolean randomized) {
        try {
            ArrayList<String> currentQuiz = new ArrayList<>();
            File readFile = new File(fileName + ".txt");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fileReader);
            int questionNumber = 0;
            String line;
            if (!randomized) {
                while ((line = br.readLine()) != null) {
                    if ((Objects.equals(line, "Quiz " + quizNumber + "f"))) {
                        while ((line = br.readLine()) != null && !(line.equals("Quiz " + Integer.parseInt(quizNumber) + 1
                                + "f"))) {
                            currentQuiz.add(line);
                        }
                        JFrame frame3 = new JFrame("Printed Quiz");
                        Container content = frame3.getContentPane();
                        JPanel panel = new JPanel();
                        JButton noFile = new JButton("Answer Without File");
                        JButton file = new JButton("Answer With File");
                        panel.add(noFile);
                        panel.add(file);
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            for (int j = 0; j < 5; j++) {
                                JTextField questionText = new JTextField(currentQuiz.get(j + (i * 5)), 10);
                                panel.add(questionText);
                            }
                        }
                        content.add(panel);
                        frame3.setSize(600, 600);
                        frame3.setLocationRelativeTo(null);
                        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame3.setVisible(true);
                        frame3.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                super.windowClosing(e);
                                try {
                                    System.out.println("Client force closed");
                                    ClientTest.messageToServer("closing");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                }
            } else {
                while ((line = br.readLine()) != null) {
                    if ((Objects.equals(line, "Quiz " + quizNumber + "t"))) {
                        while ((line = br.readLine()) != null && !(line.equals("Quiz " + Integer.parseInt(quizNumber) + 1
                                + "f"))) {
                            currentQuiz.add(line);
                        }
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            questionNumber++;
                        }
                        String[][] answersArray = new String[questionNumber][4];
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            for (int j = 0; j < 4; j++) {
                                answersArray[i][j] = currentQuiz.get(j + (i * 5) + 1);
                            }
                        }
                        Random random = new Random(); // This randomizes the sets of 4 answers
                        for (int i = answersArray.length - 1; i >= 0; i--) {
                            for (int j = answersArray[i].length - 1; j > 0; j--) {
                                int change = random.nextInt(j + 1);
                                String temp = answersArray[i][j];
                                answersArray[i][j] = answersArray[i][change];
                                answersArray[i][change] = temp;
                            }
                        }
                        String[] trackingArray = new String[(currentQuiz.size() / 5)];
                        int tracking = 0;
                        for (int i = 1; i < (currentQuiz.size()); i += 5) { // This allows for tracking which question
                            trackingArray[tracking] = currentQuiz.get(i);   // and answers go together
                            tracking++;
                        }
                        Collections.shuffle(Arrays.asList(answersArray)); // shuffles the questions order.
                        int tester;
                        JFrame frame3 = new JFrame("Printed Quiz");
                        Container content = frame3.getContentPane();
                        JPanel panel = new JPanel();
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            tester = 0;
                            while (true) {
                                if (answersArray[i][0].equals(trackingArray[tester])) {
                                    JTextField questionText1 = new JTextField((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2), 10);
                                    panel.add(questionText1);
                                    JTextField questionText2 = new JTextField("a. " +
                                            answersArray[i][0].substring(2), 10);
                                    panel.add(questionText2);
                                    JTextField questionText3 = new JTextField("b. " +
                                            answersArray[i][1].substring(2), 10);
                                    panel.add(questionText3);
                                    JTextField questionText4 = new JTextField("c. " +
                                            answersArray[i][2].substring(2), 10);
                                    panel.add(questionText4);
                                    JTextField questionText5 = new JTextField("d. " +
                                            answersArray[i][3].substring(2), 10);
                                    panel.add(questionText5);
                                    break;
                                } else if (answersArray[i][1].equals(trackingArray[tester])) {
                                    JTextField questionText1 = new JTextField((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2), 10);
                                    panel.add(questionText1);
                                    JTextField questionText2 = new JTextField("a. " +
                                            answersArray[i][0].substring(2), 10);
                                    panel.add(questionText2);
                                    JTextField questionText3 = new JTextField("b. " +
                                            answersArray[i][1].substring(2), 10);
                                    panel.add(questionText3);
                                    JTextField questionText4 = new JTextField("c. " +
                                            answersArray[i][2].substring(2), 10);
                                    panel.add(questionText4);
                                    JTextField questionText5 = new JTextField("d. " +
                                            answersArray[i][3].substring(2), 10);
                                    panel.add(questionText5);
                                    break;
                                } else if (answersArray[i][2].equals(trackingArray[tester])) {
                                    JTextField questionText1 = new JTextField((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2), 10);
                                    panel.add(questionText1);
                                    JTextField questionText2 = new JTextField("a. " +
                                            answersArray[i][0].substring(2), 10);
                                    panel.add(questionText2);
                                    JTextField questionText3 = new JTextField("b. " +
                                            answersArray[i][1].substring(2), 10);
                                    panel.add(questionText3);
                                    JTextField questionText4 = new JTextField("c. " +
                                            answersArray[i][2].substring(2), 10);
                                    panel.add(questionText4);
                                    JTextField questionText5 = new JTextField("d. " +
                                            answersArray[i][3].substring(2), 10);
                                    panel.add(questionText5);
                                    break;
                                } else if (answersArray[i][3].equals(trackingArray[tester])) {
                                    JTextField questionText1 = new JTextField((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2), 10);
                                    panel.add(questionText1);
                                    JTextField questionText2 = new JTextField("a. " +
                                            answersArray[i][0].substring(2), 10);
                                    panel.add(questionText2);
                                    JTextField questionText3 = new JTextField("b. " +
                                            answersArray[i][1].substring(2), 10);
                                    panel.add(questionText3);
                                    JTextField questionText4 = new JTextField("c. " +
                                            answersArray[i][2].substring(2), 10);
                                    panel.add(questionText4);
                                    JTextField questionText5 = new JTextField("d. " +
                                            answersArray[i][3].substring(2), 10);
                                    panel.add(questionText5);
                                    break;
                                } else {
                                    tester += 1;
                                }
                            }
                        }
                        JButton noFile = new JButton("Answer Without File");
                        JButton file = new JButton("Answer With File");
                        panel.add(noFile);
                        panel.add(file);
                        content.add(panel);
                        frame3.setSize(600, 600);
                        frame3.setLocationRelativeTo(null);
                        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame3.setVisible(true);
                        frame3.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                super.windowClosing(e);
                                try {
                                    System.out.println("Client force closed");
                                    ClientTest.messageToServer("closing");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        noFile.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame3.dispose();
                                answerMethod(fileName, quizNumber, false, null, savedUsername);
                                frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            }
                        });
                        file.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame3.dispose();
                                JFrame frame5 = new JFrame("File?");
                                Container content = frame5.getContentPane();
                                JPanel panel = new JPanel();
                                JLabel enterFile = new JLabel("Enter File Name Without .txt");
                                JButton confirmButton = new JButton("Confirm");
                                JTextField studentFileName = new JTextField("");
                                panel.add(enterFile);
                                panel.add(confirmButton);
                                panel.add(studentFileName);
                                content.add(panel);
                                frame5.setSize(600, 600);
                                frame5.setLocationRelativeTo(null);
                                frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frame5.setVisible(true);
                                confirmButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame5.dispose();
                                        answerMethod(fileName, quizNumber, true, studentFileName.getText()
                                                , savedUsername);
                                        frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    }
                                });
                                frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            }
                        });
                        return;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid File or Format", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void answerMethod(String fileName, String quizNumber, boolean file, String studentFile,
                                    String username) {
        Date date = new Date();
        try {
            ArrayList<String> currentQuiz = new ArrayList<>();
            String line;
            File readFile = new File(fileName + ".txt");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fileReader);
            File writeFile = new File(fileName.substring(0, fileName.length() - 5) + "Submissions.txt");
            FileWriter filewriter = new FileWriter(writeFile, true);
            BufferedWriter bw = new BufferedWriter(filewriter);
            while ((line = br.readLine()) != null) {
                if (line.equals("Quiz " + quizNumber + "f") || line.equals("Quiz " + quizNumber + "t")) {
                    while ((line = br.readLine()) != null) {
                        currentQuiz.add(line);
                    }
                    if (!file) {
                        JFrame frame4 = new JFrame("Student Menu");
                        Container content = frame4.getContentPane();
                        JPanel panel = new JPanel();
                        JLabel answerFormat = new JLabel("Write your answers numbers with no spaces");
                        panel.add(answerFormat);
                        JTextField answerPlace = new JTextField(10);
                        panel.add(answerPlace);
                        JButton confirm = new JButton("confirm answers");
                        panel.add(confirm);
                        bw.write(fileName + ",quiz" + quizNumber + ',' + username + ",");
                        content.add(panel);
                        frame4.setSize(600, 600);
                        frame4.setLocationRelativeTo(null);
                        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame4.setVisible(true);
                        frame4.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                super.windowClosing(e);
                                try {
                                    System.out.println("Client force closed");
                                    ClientTest.messageToServer("closing");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        confirm.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame4.dispose();
                                for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                                    try {
                                        bw.write((answerPlace.getText()).charAt(i));
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid Format", "Error",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                try {
                                    bw.write(",");
                                    bw.write((int) (date.getMonth() + 1) + "/" + (int) date.getDate() + "/" + (int) // All of this
                                            (date.getYear() + 1900) + "," + (int) date.getHours() + ":"                 // test
                                            + (int) date.getMinutes() + ":" + (int) date.getSeconds() + "\n");
                                    bw.close();
                                } catch (IOException ioException) {
                                    JOptionPane.showMessageDialog(null,
                                            "Not Possible", "Error",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                System.out.println("Answers Accepted");
                                frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            }
                        });

                    } else {
                        File readStudentFile = new File(studentFile + ".txt");
                        FileReader studentFileReader = new FileReader(readStudentFile);
                        BufferedReader brStudent = new BufferedReader(studentFileReader);
                        bw.write(fileName + ",quiz" + quizNumber + ',' + username + ",");
                        while ((line = brStudent.readLine()) != null) {
                            bw.write(line);
                        }
                        bw.write(",");
                        bw.write((int) (date.getMonth() + 1) + "/" + (int) date.getDate() + "/" + (int) // All of this
                                (date.getYear() + 1900) + "," + (int) date.getHours() + ":"                 // test
                                + (int) date.getMinutes() + ":" + (int) date.getSeconds() + "\n");
                        bw.close();
                        System.out.println("Answers Accepted");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid File or Format", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
