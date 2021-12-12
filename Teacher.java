import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {

    //scanner for the new quiz from the terminal
    Scanner scan = new Scanner(System.in);

    public Teacher() {

    }

    public void createCourse(String courseName, String username) {
        try {
            File course = new File(username + courseName + ".txt");
            if (course.createNewFile()) {
                System.out.println("Course file successfully created!");
            }
        } catch (IOException e) {
            System.out.println("Error! File not created.");
            e.printStackTrace();
        }
    }

    //create quiz through terminal
    public ArrayList<String> addQuizTerminal(String courseName, String username, Scanner scan) {
        ArrayList<String> quiz_input = new ArrayList<String>();

        boolean loop = true;
        int index;
        //quiz loop
        while (loop) {
            System.out.println("What's the quiz number?");
            index = scan.nextInt();
            scan.nextLine();
            System.out.println("Randomize this quiz? y/n");
            String randomizeQuestion = scan.nextLine();
            String quizNumber = null;
            if (randomizeQuestion.equals("y") || randomizeQuestion.equals("Y")) {
                quizNumber = "Quiz " + Integer.toString(index) + "t";
            } else {
                quizNumber = "Quiz " + Integer.toString(index) + "f";
            }
            quiz_input.add(quizNumber);

            System.out.println("How many questions would you like to add?");
            int questions = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < questions; i++) {

                //get the quiz from the terminal
                System.out.println("Add Question:");
                for (int j = 0; j < 5; j++) {
                    String line1 = scan.nextLine();
                    quiz_input.add(line1);
                }
            }

            System.out.println("Add Another Quiz? Y or N");
            String loop_question = scan.nextLine();
            if (loop_question.equals("Y") || loop_question.equals("y")) {
                loop = true;
                index++;
            } else {
                loop = false;
            }

        }
        try {
            FileOutputStream fos = new FileOutputStream(username + courseName + ".txt", true);
            PrintWriter pw = new PrintWriter(fos);

            //print to the new txt file
            for (int i = 0; i < quiz_input.size(); i++) {
                pw.println(quiz_input.get(i));
            }
            pw.close();

        } catch (IOException p) {
            System.out.println("Error, file has not been found");
            p.printStackTrace();
        }
        //print case
        System.out.println("Quiz Added");
        return quiz_input;
    }


    public ArrayList<String> uploadQuiz(String filename) throws IOException {
        ArrayList<String> uploadQuiz = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fileReader);
            String line = bfr.readLine();
            while (line != null) {
                uploadQuiz.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (IOException e) {
            System.out.println("The file doesn't exist!");
            e.printStackTrace();
        }
        boolean checkFormat1 = uploadQuiz.toString().contains("?");
        boolean checkFormat2 = uploadQuiz.toString().contains("a.");
        boolean checkFormat3 = uploadQuiz.toString().contains("b.");
        boolean checkFormat4 = uploadQuiz.toString().contains("c.");
        boolean checkFormat5 = uploadQuiz.toString().contains("d.");
        if (checkFormat1 && checkFormat2 && checkFormat3 && checkFormat4 && checkFormat5) {
            System.out.println("Correct");
            return uploadQuiz;
        } else {
            System.out.println("Incorrect Format.");
            return null;
        }

    }

    public void writeUploadQuiz(String filename /*Name of the course file*/,
                                ArrayList<String> arrUploadedQuiz, String username) throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(username + filename + ".txt", true));
            for (int i = 0; i < arrUploadedQuiz.size(); i++) {
                pw.println(arrUploadedQuiz.get(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //delete the quiz
    public void deleteQuiz(String filename/* the name of the course file*/,
                           ArrayList<String> deleteQuiz,
                           String input) throws FileNotFoundException {
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file + ".txt");
            BufferedReader bfr = new BufferedReader(fileReader);
            String line = bfr.readLine();
            while (line != null) {
                deleteQuiz.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (IOException e) {
            System.out.println("The file doesn't exist!");
            e.printStackTrace();
        }
        int num = Integer.parseInt(input.substring(5));
        String str = String.valueOf(num++);
        String line;
        int i = deleteQuiz.indexOf(input);
        do {
            deleteQuiz.remove(i);
            line = deleteQuiz.get(i + 1);
            i++;
        } while (!line.equalsIgnoreCase("Quiz " + str));

        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(filename, true));
            for (int j = 0; j < deleteQuiz.size(); j++) {
                pw.println(deleteQuiz.get(j));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> courseList() {

        try {
            File listOfCourses = new File("CourseList.txt");
            listOfCourses.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> courseList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "CourseList.txt"))) {

            String names;
            while ((names = br.readLine()) != null) {
                courseList.add(names);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseList;
    }


    public String addCourse(ArrayList<String> courses, String courseName, String username) throws IOException {
        int i = 0;
        while (i < courses.size()) {
            if (courses.get(i).contains(courseName)) {
                return "courseExists";
            }
            i++;
        }

        String finalCourse = username + courseName;
        courses.add(finalCourse);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("CourseList.txt", true)))) {
            pw.write(finalCourse + "\n");
        } catch (IOException e) {
            throw e;
        }
        return "courseAdded";
    }

    public boolean goToCourse(ArrayList<String> courses, String courseName, String username) {
        boolean found = false;
        int i = 0;
        while (i < courses.size()) {
            if (courses.get(i).equals(username + courseName)) {
                found = true;
                break;
            }
            i++;
        }
        return found;
    }

    public static void printCourse(String course, String username) {
        try {
            File readFile = new File(username + course + ".txt");
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

    public static void viewSubmissions(String username) {
        try {
            File readFile = new File(username + "Submissions.txt");
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

}
