package project.oop;
import java.util.Scanner;

public class User {
    protected Scanner input;
    protected String username;
    protected String password;
    protected int userId;
    protected String firstName;
    protected String lastName;
    protected String userCNIC;
    protected String userPhoneNo;
    protected String userEMail;
    protected String userDepartment;

    public User() {
        input = new Scanner(System.in);
        username = null;
        password = null;
        userId = (int) (Math.random() * 999);
        firstName = null;
        lastName = null;
        userCNIC = null;
        userPhoneNo = null;
        userEMail = null;
        userDepartment = null;
    }
    
    public void UserMenu()
    {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n\t\t\t** Menu **");
            System.out.println("1. Student Account");
            System.out.println("2. Faculty Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.StudentMenu();
                    break;
                case 2:
                    Faculty faculty = new Faculty();
                    faculty.FacultyMenu();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}