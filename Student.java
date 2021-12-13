import java.io.*;
import java.util.*;
/**
 * Project 4 - Student
 * <p>
 * This class contains the methods relating purely to student users.
 *
 * @author Adam Arthur
 * @version November 15, 2021
 */
public class Student {

    private String courseSelection;

    public String getCourseSelection() {
        return courseSelection;
    }

    public void setCourseSelection(String courseSelection) {
        this.courseSelection = courseSelection;
    }

    public static void takeQuiz(String fileName, int quizNumber, boolean randomized) {
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
                        while ((line = br.readLine()) != null && !(line.contains("Quiz"))) {
                            currentQuiz.add(line);
                        }
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            for (int j = 0; j < 5; j++) {
                                System.out.println(currentQuiz.get(j + (i * 5)));
                            }
                        }
                        return;
                    }
                }
            } else {
                while ((line = br.readLine()) != null) {
                    if ((Objects.equals(line, "Quiz " + quizNumber + "t"))) {
                        System.out.println("Quiz " + quizNumber);
                        while ((line = br.readLine()) != null) {
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
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            tester = 0;
                            while (true) {
                                if (answersArray[i][0].equals(trackingArray[tester])) { // Prints the question with the
                                    System.out.println((i + 1) + ". " +                 // correct format
                                            currentQuiz.get((tester * 5)).substring(2));
                                    System.out.println("a. " + answersArray[i][0].substring(2));
                                    System.out.println("b. " + answersArray[i][1].substring(2));
                                    System.out.println("c. " + answersArray[i][2].substring(2));
                                    System.out.println("d. " + answersArray[i][3].substring(2));
                                    break;
                                } else if (answersArray[i][1].equals(trackingArray[tester])) {
                                    System.out.println((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2));
                                    System.out.println("a. " + answersArray[i][0].substring(2));
                                    System.out.println("b. " + answersArray[i][1].substring(2));
                                    System.out.println("c. " + answersArray[i][2].substring(2));
                                    System.out.println("d. " + answersArray[i][3].substring(2));
                                    break;
                                } else if (answersArray[i][2].equals(trackingArray[tester])) {
                                    System.out.println((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2));
                                    System.out.println("a. " + answersArray[i][0].substring(2));
                                    System.out.println("b. " + answersArray[i][1].substring(2));
                                    System.out.println("c. " + answersArray[i][2].substring(2));
                                    System.out.println("d. " + answersArray[i][3].substring(2));
                                    break;
                                } else if (answersArray[i][3].equals(trackingArray[tester])) {
                                    System.out.println((i + 1) + ". " +
                                            currentQuiz.get((tester * 5)).substring(2));
                                    System.out.println("a. " + answersArray[i][0].substring(2));
                                    System.out.println("b. " + answersArray[i][1].substring(2));
                                    System.out.println("c. " + answersArray[i][2].substring(2));
                                    System.out.println("d. " + answersArray[i][3].substring(2));
                                    break;
                                } else {
                                    tester += 1;
                                }
                            }
                        }
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Bad File");
        }
    }

    public static void answerMethod(String fileName, int quizNumber, boolean file, String studentFile, String username,
                                    String teacherName, Scanner scan) {
        Date date = new Date();
        try {
            ArrayList<String> currentQuiz = new ArrayList<>();
            String line;
            File readFile = new File(fileName + ".txt");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fileReader);
            File writeFile = new File(teacherName + "Submissions.txt");
            FileWriter filewriter = new FileWriter(writeFile, true);
            BufferedWriter bw = new BufferedWriter(filewriter);
            while ((line = br.readLine()) != null) {
                if (line.equals("Quiz " + quizNumber + "f") || line.equals("Quiz " + quizNumber + "t")) {
                    while ((line = br.readLine()) != null) {
                        currentQuiz.add(line);
                    }
                    if (!file) {
                        bw.write(fileName + ",quiz" + quizNumber + ',' + username + ",");
                        for (int i = 0; i < (currentQuiz.size() / 5); i++) {
                            System.out.println("Write answer number " + (i + 1));
                            String answer = scan.nextLine();
                            bw.write(answer);
                        }
                    } else {
                        File readStudentFile = new File(studentFile + ".txt");
                        FileReader studentFileReader = new FileReader(readStudentFile);
                        BufferedReader brStudent = new BufferedReader(studentFileReader);
                        bw.write(fileName + ",quiz" + quizNumber + ',' + username + ",");
                        while ((line = brStudent.readLine()) != null) {
                            bw.write(line);
                        }
                    }
                    bw.write(",");
                    bw.write((int) (date.getMonth() + 1) + "/" + (int) date.getDate() + "/" + (int) // All of this
                            (date.getYear() + 1900) + "," + (int) date.getHours() + ":"                 // test
                            + (int) date.getMinutes() + ":" + (int) date.getSeconds() + "\n");
                    bw.close();
                    System.out.println("Answers Accepted");
                }
            }
        } catch (Exception e) {
            System.out.println("Bad File");
        }
    }

    public static void printCourses(String courseList) {
        try {
            File readFile = new File(courseList + ".txt");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Bad File");
        }
    }

    public static boolean determineRandom(String file, int quizNumber) {
        try {
            File readFile = new File(file + ".txt");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                if ((Objects.equals(line, "Quiz " + quizNumber + "t"))) {
                    return true;
                } else if ((Objects.equals(line, "Quiz " + quizNumber + "f"))) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Bad File");
        }
        return false;
    }

    public static boolean checkFile(String file) {
        try {
            File readFile = new File(file + ".txt");
            FileReader fileReader = new FileReader(readFile);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }


}