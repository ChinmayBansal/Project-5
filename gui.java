import javax.swing.*;

public class gui {
   JFrame f = new JFrame();
  
  //welcome message
   public void welcomeMessage(String message) {
        JOptionPane.showMessageDialog(f, message);
   }

  //incorrect message
   public void incorrectMessage(String message) {
       JOptionPane.showMessageDialog(f, message);
   }

  //any input that takes numbers
   public int inputNumber(String message) {
       String input =  JOptionPane.showInputDialog(f, message);
       int i = Integer.parseInt(input);
       System.out.println(i);
       return i;
   }

  //any input that takes a string
   public String inputString(String message) {
       String input =  JOptionPane.showInputDialog(f, message);
       return input;
   }
   
   //dropdown for 2 options 
   public int enterDropdown2(String message) {
        Object[] choices = {
                1, 2
        };
        int input = (int) JOptionPane.showInputDialog(f, message, null, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        if (input == 1) {
            return 1;
        } else if (input == 2) {
            return 2;
        }
        return 0;
   }

  //dropdown for 3 options 
   public int enterDropdown3(String message) {
       Object[] choices = {
               1, 2, 3
       };
       int input = (int) JOptionPane.showInputDialog(f, message, null, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
       if (input == 1) {
           return 1;
       } else if (input == 2) {
           return 2;
       } else if (input == 3) {
           return 3;
       }
       return 0;
   }

  //dropdown for 5 options 
   public int enterDropdown5(String message) {
       Object[] choices = {
               1, 2, 3, 4, 5
       };
       int input = (int) JOptionPane.showInputDialog(f, message, null, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
       if (input == 1) {
           return 1;
       } else if (input == 2) {
           return 2;
       } else if (input == 3) {
           return 3;
       } else if (input == 4) {
           return 4;
       } else if (input == 5) {
           return 5;
       }
       return 0;
   }
   public void exitMessage(String message) {
       JOptionPane.showMessageDialog(f, message);
   }


   //main method to test
   public static void main(String[] args) {
        gui g = new gui();
        g.welcomeMessage("Welcome to the teacher app");
        g.enterDropdown2("1. Existing User\n 2. Create User");
        g.enterDropdown3("1. Take quiz.\n2. Edit information.\n3. Logout.");
        g.incorrectMessage("Incorrect login. Please try again!");
        g.inputNumber("Enter number");
   }
}
