# Project-5

# Running the Project
1. Run the ServerTest class first.
2. Run the ClientTest class second. 

# Submissions
1. Report submitted by Chinmay Bansal
2. Code submitted by Chinmay Bansal
3. Presentation submitted by Chinmay Bansal


# Class Descriptions

# User Class
The User class contains methods which allow the user to create an account dependiing on if they are a teacher or if they are student. It also checks if a username is taken, which will then prompt the uer to write a new username until it does not exist. The way to differentiate between a student and a teacher is that the methods add an "S" or a "T" if the user is a student or a teacher. The class also contains methods to delete an account or change the user information like the username or password. This class was tested with the UserTest class and it included JUnit. It tests each method with some values assigned in each method. This clas relates to the other methods as it decideds whether the user is a teacher or not.

# Teacher Class
The teacher class contains methods which allows the teacher to create a quiz through the terminal and through a file upload, a course, delete a quiz, and creates a course list. The upload quiz through a file checks a format to see if it right. The upload quiz through a file does not because we are assuming the teacher knows how to create a question which includes a question and four answer choices(a. b. c. d.). It also asks how many questions does a teacher want to create and it asks if they want to create another question. The delete quiz method removes the quiz from the course file. The course list and print course allow the student view courses. There is also a method which allows the teacher to view submissons of the students. The testing was done using JUnit to see if each method could perform correctly.

# Student Class
The student class allows a student to take a quiz whether or not it is randomized. The way that this is done is that there is a boolean passed in which allows the quiz to be randomized or not and teacher will decide if it is randomized or not and then depending on which choice, the quiz will be randomized or not. The quiz is also timestamped once the student finishes. The teacher name is extracted when recording the submission so once the student takes a quiz, the submisson will show the teacher name, course name, and the quiz with their answers and time stamp. The class is tested with a main method instead of JUnit. 

# ClientTest Class
The client class creates the connection to the server and runs the GUIs. It contains the control flow for the project. It is much like the QuizApp class from Project 4 as it transfomrs that class into GUIs. It also contains two methods called: messageToServer() and messageFromServer(). These two methods are what allow our program to progress as it able to tell the server what to do and user can progress through the application. This class was tested through with ServerTest class so we can go through the GUIs and get the expected results. Print line debugging was also used to see how the GUIs were used when something happened.

# ServerTest Class
The server test clas creates the conneciton to the client and creates threads. Inside the class is also the ClientHandler class which reads in lines from the client class which would call methods from the Student, User, and Teacher class. The testing done for this was with the ClientTest class where we sent lines to the server and did actions. Print line debeugging was also used to see if the server called the methods correctly and sent lines properly.

# StudentGUI Class
The student GUI class includes all the GUIs for the student user such as taking a quiz. In this class, the message is sent to the server and is recevied. This class is implemented in the ClientTest class as it is used in the control flow and the methods of the class are called to display GUIs. It also woks with the server class as it writes messages to the server class. The testing for this class was done by calling the GUIs and ensuring they worked in the way we wanted them to.

# TeacherGUI Class
The teacher gui was never directly implemented in the client class but instead the GUIs were taken and edited in the client class for the contorl flow. The testing for this class was done by calling the GUIs and making sure the GUIs displayed the right frames and messages.

# Notes:

When creating a course, just coursename is fine. When uploading a quiz through a file for a teacher, include .txt and add a f or t next to the quiz so the program knows whether to randomize the quiz or not. When adding a quiz through text, add a number to each question. When a student is uploading a file, do not put .txt.
