import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project 4 - User
 * <p>
 * This class contains methods in which the user can edit their information, create accounts, and delete accounts.
 *
 * @author Chinmay Bansal
 * @version November 15, 2021
 */
public class User {
    private String username;
    private String password;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> createFile() {
        try {
            File file = new File("LoginInfo.txt");
            if (file.createNewFile()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> loginInfo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "LoginInfo.txt"))) {

            String login;
            while ((login = br.readLine()) != null) {
                loginInfo.add(login);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    public String createName(ArrayList<String> loginInfo, String username, boolean taken) {
        do {
            for (int i = 0; i < loginInfo.size(); i += 2) {
                if (username.equals(loginInfo.get(i))) {
                    return "taken";
                }
            }
            return "not taken";
        } while (true);
    }

    public void studentPass(ArrayList<String> loginInfo, String password, String username) throws IOException {
        String student = "S";
        this.username = username;
        String finalPassStudent = password + student;

        loginInfo.add(this.username);
        loginInfo.add(finalPassStudent);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", true)))) {
            pw.write(username + "\n");
            pw.write(finalPassStudent + "\n");
        } catch (IOException e) {
            throw e;
        }
    }

    public String teacherPass(ArrayList<String> loginInfo, String password, String username) throws IOException {
        String teacher = "T";
        String finalPassTeacher = password + teacher;

        loginInfo.add(username);
        loginInfo.add(finalPassTeacher);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", true)))) {
            pw.write(username + "\n");
            pw.write(finalPassTeacher + "\n");
        } catch (IOException e) {
            throw e;
        }
        return "passChanged";
    }

    public String checkUsername(ArrayList<String> loginInfo, String username) {
        for (int i = 0; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(username)) {
                return "username match";
            }
        }
        return "username wrong";
    }

    public String checkStudent(ArrayList<String> loginInfo, String password) {
        for (int i = 1; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(password + "S")) {
                return "pass match";
            }
        }
        return "pass wrong";
    }

    public String checkTeacher(ArrayList<String> loginInfo, String password) {
        for (int i = 1; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(password + "T")) {
                return "pass match";
            }
        }
        return "pass wrong";
    }

    public String deleteAccount(ArrayList<String> loginInfo, String username, String password) throws IOException {
        for (int i = 0; i < loginInfo.size(); i++) {
            if (loginInfo.get(i).equals(username) && (loginInfo.get(i + 1).equals(password + "S") ||
                    loginInfo.get(i + 1).equals(password + "T"))) {
                loginInfo.remove(loginInfo.get(i));
                loginInfo.remove(loginInfo.get(i));
                break;
            }
        }
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", false)))) {
            for (int i = 0; i < loginInfo.size(); i++) {
                pw.write(loginInfo.get(i) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }

        return "AccountDelete";
    }

    public String changeUsername(ArrayList<String> loginInfo, String username, String changeName) throws IOException {
        String changeOrNot = "";
        for (int i = 0; i < loginInfo.size(); i += 2) {
            if(loginInfo.get(i).equals(changeName)) {
                changeOrNot = "usernameNoChange";
                return changeOrNot;
            }
        }

        for(int i = 0; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(username)) {
                int index = loginInfo.indexOf(username);
                loginInfo.set(index, changeName);
                break;
            }
        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", false)))) {
            for (int i = 0; i < loginInfo.size(); i++) {
                pw.write(loginInfo.get(i) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }

        changeOrNot = "UsernameChanged";

        return changeOrNot;
    }

    public String changeTeacherPass(ArrayList<String> loginInfo, String password, String changePass)
            throws IOException {
        for (int i = 1; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(password + "T")) {
                int index = loginInfo.indexOf(password + "T");
                String newPass = changePass + "T";
                loginInfo.set(index, newPass);
                break;
            }
        }
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", false)))) {
            for (int i = 0; i < loginInfo.size(); i++) {
                pw.write(loginInfo.get(i) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }

        return "Password changed.";
    }

    public String changeStudentPass(ArrayList<String> loginInfo, String password, String changePass)
            throws IOException {
        for (int i = 1; i < loginInfo.size(); i += 2) {
            if (loginInfo.get(i).equals(password + "S")) {
                int index = loginInfo.indexOf(password + "S");
                String newPass = changePass + "S";
                loginInfo.set(index, newPass);
                break;
            }
        }
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter
                ("LoginInfo.txt", false)))) {
            for (int i = 0; i < loginInfo.size(); i++) {
                pw.write(loginInfo.get(i) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }

        return "Password changed.";
    }

}

