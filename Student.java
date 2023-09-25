package project.oop;
import java.io.*;
import java.util.*;

public class Student extends User implements Serializable {
    private ArrayList<Student> students;
    private String regNo;
    private String sClass;

    Student() {
        super();
        students = new ArrayList<>();
        regNo = null;
        sClass = null;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setStudentClass(String sClass) {
        this.sClass = sClass;
    }
    
    public void StudentMenu()
    {
    BookingEvent newEvent = new BookingEvent();
    Scanner input = new Scanner(System.in);
        while (true) {
                    System.out.println("\n\t** Student Account **");
                    System.out.println("1. Add Student");
                    System.out.println("2. View Student");
                    System.out.println("3. Update Student");
                    System.out.println("4. Delete Student");
                    System.out.println("5. Manage Event Booking");
                    System.out.println("6. Exit");
                    
                    System.out.print("Enter your choice: ");
                    String  studentChoice = input.next();
                    
                    try
                    {
                    int choice = Integer.parseInt(studentChoice);
                    switch (choice) {
                        case 1:
                            addStudent();
                            break;
                        case 2:
                            viewStudent();
                            break;
                        case 3:
                            updateStudent();
                            break;
                        case 4:
                            deleteStudent();
                            break;
                        case 5:
                            newEvent.EventBookingMenu();
                            break;
                        case 6:
                            System.out.println("Exiting program...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
        }
    }
    
    public void addStudent() {
        
        System.out.println("\t!!..Enter Student Details..!!");
        
        int studentId = (int) (Math.random() * 999);
        System.out.println("Your student Id is: " + studentId);
       
        System.out.print("Enter your Registration Number (without hyphen): ");
        String regno;
        regno = input.next();
        while (regno.isEmpty() || !regno.matches("[A-Z]{2}\\d{2}[A-Z]{3}\\d{3}")) {
            System.out.println("Invalid input... Enter registration number: ");
            regno = input.next();
            input.nextLine();
        }
        input.nextLine();
    
        System.out.print("Enter your Class/Section e.g BSE3A: ");
        String classSection = input.next();
        while(classSection.isEmpty() || !classSection.matches("[A-Z]{3}\\d{1}[A-Z]{1}")) 
        {
            System.out.println("This data is invalid...enter class/section:");
            classSection = input.next();
        }
        input.nextLine();
        
        System.out.print("Enter Your First Name: ");
        String fName = input.nextLine();
        while (fName.isEmpty() || !fName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. First name should contain only alphabetic characters. Enter your first name:");
            fName = input.next();
        }
        input.nextLine();
        
        System.out.print("Enter Your Last Name: ");
        String lName = input.next().trim();
        while (lName.isEmpty() || !lName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. First name should contain only alphabetic characters. Enter your last name:");
            lName = input.next();
        }
        input.nextLine();
        
        System.out.print("Enter your CNIC in (00000-0000000-0) format: ");
        String cnic = input.next();
        while (cnic.isEmpty() || !cnic.matches("\\d{5}-\\d{7}-\\d")) {
            System.out.println("Invalid input. CNIC should be in the format 00000-0000000-0. Enter your CNIC:");
            cnic = input.next();
        }
        input.nextLine();
        
        System.out.print("Enter your phone no (without dashes or spaces): ");
        String phoneNo = input.next();
        while (phoneNo.isEmpty() || !phoneNo.matches("\\d{11}")) {
            System.out.println("Invalid input. phone no should be in the format 00000000000. Enter your Phone no:");
            phoneNo = input.next();
        }
        
        System.out.print("Enter your E-mail Address: ");
        String email = input.next();
        
        while (email.isEmpty() || !email.contains("@")) {
            System.out.println("Invalid input. Email should be in the format abc@gmail.com. Enter your email:");
            email = input.next();
        }
        
        System.out.print("Enter your Department: ");
        String department = input.next();
        while(department.isEmpty() || !department.matches("[a-zA-Z]+"))
        {
            System.out.println("Invalid input. deaprtment should contain only alphabetic characters. Enter your department name:");
            department = input.next();
            
        }

        Student newStudent = new Student();
        newStudent.userId = studentId;
        newStudent.setRegNo(regno);
        newStudent.setStudentClass(classSection);
        newStudent.firstName = fName;
        newStudent.lastName = lName;
        newStudent.userCNIC = cnic;
        newStudent.userPhoneNo = phoneNo;
        newStudent.userEMail = email;
        newStudent.userDepartment = department;
        students.add(newStudent);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Studentrecord.txt", true));
             for (Student student : students) {
                objectOutputStream.writeObject(student);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("\n\t\t!!..Your Information Saved Successfully..!!");
    }

    public void deleteStudent() {
        
        System.out.print("Enter Student ID Number to Delete: ");
        String studentId = input.next();
        
        while(studentId.isBlank() || !studentId.matches("[0-9]+"))
        {
            System.out.println("Invalid input. Student id should contain only digits. Enter your Id again:");
            studentId = input.next();
            input.nextLine();
        }

            boolean found = false;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).userId == Integer.parseInt(studentId)) {
                    students.remove(i);
                    found = true;
                    break;
                }
            }
            if (found) {
            // Remove the booking from the file
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Bookingrecord.txt"))) {
                for (Student student : students) {
                    objectOutputStream.writeObject(student);
                }
                System.out.println("Student Data Deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Student Data not found. Cancellation failed.");
        }
    }

    public void updateStudent() {
        System.out.print("\n\t!!..Update Student Details..!!");
        System.out.print("\nEnter Student ID Number: ");
        String studentId = input.next();
        
        while(studentId.isBlank() || !studentId.matches("[0-9]+"))
        {
            System.out.println("Invalid input. Student id should contain only digits. Enter your Id again:");
            studentId = input.next();
            input.nextLine();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Studentrecord.txt"));
            try {
                while (true) {
                    Student student = (Student) objectInputStream.readObject();
                    students.add(student);
                }
            } catch (EOFException e) {
                // Reached end of file
            }

            objectInputStream.close();

            boolean found = false;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).userId == Integer.parseInt(studentId)) {
                    Student student = students.get(i);
                    found = true;

                    System.out.print("Enter your Registration Number: ");
                    String regno;
                    regno = input.next();
                    while(regno.isBlank())
                    {
                        System.out.println("This field cannot be blank..enter reg no:");
                        regno = input.next();
                        input.nextLine();
                    }
            
                    System.out.print("Enter your Class/Section: ");
                    String classSection = input.next();
                    while(classSection.isBlank())
                    {
                        System.out.println("This field cannot be blank..enter class/section:");
                        classSection = input.next();
                        input.nextLine();
                    }
                    System.out.print("Enter Your First Name: ");
                    String fName = input.nextLine();
                    while (fName.isBlank() || !fName.matches("[a-zA-Z]+")) {
                        System.out.println("Invalid input. First name should contain only alphabetic characters. Enter your first name:");
                        fName = input.next();
                    }
        
                    System.out.print("Enter Your Last Name: ");
                    String lName = input.next();
                    while (lName.isBlank() || !lName.matches("[a-zA-Z]+")) {
                        System.out.println("Invalid input. First name should contain only alphabetic characters. Enter your last name:");
                        lName = input.next();
                    }
        
                    System.out.print("Enter your CNIC in (00000-0000000-0) format: ");
                    String cnic = input.next();
                    while (cnic.isBlank() || !cnic.matches("\\d{5}-\\d{7}-\\d")) {
                        System.out.println("Invalid input. CNIC should be in the format 00000-0000000-0. Enter your CNIC:");
                            cnic = input.next();
                    }
        
                    System.out.print("Enter your phone no (without dashes or spaces): ");
                    String phoneNo = input.next();
                    while (phoneNo.isBlank() || !phoneNo.matches("\\d{11}")) {
                        System.out.println("Invalid input. phone no should be in the format 00000000000. Enter your Phone no:");
                        phoneNo = input.next();
                    }
        
                    System.out.print("Enter your E-mail Address: ");
                    String email = input.nextLine();
        
                    while (email.isBlank() || !email.contains("@")) {
                        System.out.println("Invalid input. Email should be in the format abc@gmail.com. Enter your email:");
                        email = input.nextLine();
                    }
        
                    System.out.print("Enter your Department: ");
                    String department = input.next();
                    while(department.isBlank() || !department.matches("[a-zA-Z]+"))
                    {
                        System.out.println("Invalid input. deaprtment should contain only alphabetic characters. Enter your department name:");
                        department = input.next();
                    }

                    student.setRegNo(regNo);
                    student.setStudentClass(classSection);
                    student.firstName = fName;
                    student.lastName = lName;
                    student.userCNIC = cnic;
                    student.userPhoneNo = phoneNo;
                    student.userEMail = email;
                    student.userDepartment = department;

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Studentrecord.txt"));
                    for (Student s : students) {
                        objectOutputStream.writeObject(s);
                    }
                    objectOutputStream.close();
                    System.out.println("\n\t\t!!..Student Information Updated Successfully..!!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No student record found with ID " + studentId + ".");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }
    }

    public void viewStudent() {
        System.out.println("\n\t!!..View Student Details..!!");
        System.out.print("Enter Student ID Number: ");
        String studentId = input.next();
        
        while(studentId.isBlank() || !studentId.matches("[0-9]+"))
        {
            System.out.println("Invalid input. Student id should contain only digits. Enter your Id again:");
            studentId = input.next();
            input.nextLine();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Studentrecord.txt"));
            
            try {
                while (true) {
                    Student student = (Student) objectInputStream.readObject();
                    students.add(student);
                }
            } catch (EOFException e) {
                // Reached end of file
            }

            objectInputStream.close();

            boolean found = false;
            for (Student student : students) {
                if (student.userId == Integer.parseInt(studentId))  {
                     System.out.println("Student Details:");
                    System.out.println("-----------------------");
                    System.out.println("\nStudent ID: " + student.userId);
                    System.out.println("Registration Number: " + student.regNo);
                    System.out.println("Class/Section: " + student.sClass);
                    System.out.println("First Name: " + student.firstName);
                    System.out.println("Last Name: " + student.lastName);
                    System.out.println("CNIC: " + student.userCNIC);
                    System.out.println("Phone Number: " + student.userPhoneNo);
                    System.out.println("Email Address: " + student.userEMail);
                    System.out.println("Department: " + student.userDepartment);
                    System.out.println("-----------------------");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No student record found with ID " + studentId + ".");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }
    }
}