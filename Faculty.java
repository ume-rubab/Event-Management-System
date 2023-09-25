package project.oop;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Faculty extends User implements Serializable {
    private ArrayList<Faculty> facultys;
    private String designation;

    Faculty() {
        super();
        facultys = new ArrayList<>();
        designation = null;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
    
    public void FacultyMenu()
    {
        BookingEvent newEvent=new BookingEvent();
        Scanner input = new Scanner(System.in);
        while (true) { 
                    System.out.print("Enter your choice: ");
                    System.out.println("\n\t*** Faculty Account ***");
                    System.out.println("1. View Faculty");
                    System.out.println("2. Update Faculty");
                    System.out.println("3. Delete Faculty");
                    System.out.println("4. Manage Event Booking");
                    System.out.println("5. Back to Main Menu");
                    System.out.println("6. Exit");
                    
                    System.out.print("Enter your choice: ");
                    int facultyChoice = input.nextInt();
                    input.nextLine(); // Consume the newline character

                    switch (facultyChoice) {
                        case 1:
                             viewFaculty();
                            break;
                        case 2:
                             updateFaculty();
                            break;
                        case 3:
                           deleteFaculty();
                          break;
                         case 4:
                            newEvent.EventBookingMenu();
                            break;
                        case 5:
                           continue;
                        case 6:
                            System.out.println("Exiting program...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
        }
    }

    public void addFaculty() {
        
        System.out.println("\t!!..Enter Faculty Details..!!");
        
        int facultyId = (int) (Math.random() * 999);
        System.out.println("Your faculty Id is: " + facultyId);
       
        System.out.print("Enter Your First Name: ");
        String fName = input.next();
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
        input.nextLine();
        
        System.out.print("Enter your Department: ");
        String department = input.next();
        while(department.isEmpty() || !department.matches("[a-zA-Z]+"))
        {
            System.out.println("Invalid input. deaprtment should contain only alphabetic characters. Enter your department name:");
            department = input.next();
            
        }
        input.nextLine();
        
        System.out.print("Enter your Designation: ");
        String desig = input.next();
        while(desig.isEmpty() || !desig.matches("[a-zA-Z]+"))
        {
            System.out.println("Invalid input. designation should contain only alphabetic characters. Enter your designation name:");
            desig = input.next();
            
        }

        Faculty newfaculty = new Faculty();
        newfaculty.userId = facultyId;
        newfaculty.firstName = fName;
        newfaculty.lastName = lName;
        newfaculty.userCNIC = cnic;
        newfaculty.userPhoneNo = phoneNo;
        newfaculty.userEMail = email;
        newfaculty.userDepartment = department;
        newfaculty.setDesignation(desig);
        facultys.add(newfaculty);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Facultyrecord.txt", true));
             for (Faculty faculty : facultys) {
                objectOutputStream.writeObject(faculty);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("\n\t\t!!..Your Information Saved Successfully..!!");
    }

    
    public void updateFaculty() {
        System.out.print("\n\t!!..Update Faculty Details..!!");
        String id;
        do {
            System.out.print("\nEnter Faculty ID Number to update: ");
            id = input.nextLine().trim();

            if (!id.matches("[0-9]+")) {
                System.out.println("Invalid ID. Please enter only numeric digits.");
            } else if (id.isEmpty()) {
                System.out.println("ID cannot be empty. Please enter a value.");
            }
            else { }
        } while (!id.matches("[0-9]+") || id.isEmpty());
        
        int facultyId = Integer.parseInt(id);
        input.nextLine(); // Consume the newline character

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Facultyrecord.txt"));
            try {
                while (true) {
                    Faculty faculty = (Faculty) objectInputStream.readObject();
                    facultys.add(faculty);
                }
            } catch (EOFException e) {
                // Reached end of file
            }

            objectInputStream.close();

            boolean found = false;
            for (int i = 0; i < facultys.size(); i++) {
                if (facultys.get(i).userId == facultyId) {
                    Faculty faculty = facultys.get(i);
                    found = true;

                    System.out.print("Enter Your First Name: ");
                    String fName = input.next();
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
                input.nextLine();

                System.out.print("Enter your Department: ");
                String department = input.next();
                while(department.isEmpty() || !department.matches("[a-zA-Z]+"))
                {
                    System.out.println("Invalid input. deaprtment should contain only alphabetic characters. Enter your department name:");
                    department = input.next();

                }
                input.nextLine();

                System.out.print("Enter your Designation: ");
                String desig = input.next();
                while(desig.isEmpty() || !desig.matches("[a-zA-Z]+"))
                {
                    System.out.println("Invalid input. designation should contain only alphabetic characters. Enter your designation name:");
                    desig = input.next();

                }

                    faculty.firstName = fName;
                    faculty.lastName = lName;
                    faculty.setDesignation(desig);
                    faculty.userCNIC = cnic;
                    faculty.userPhoneNo = phoneNo;
                    faculty.userEMail = email;
                    faculty.userDepartment = department;

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Facultyrecord.txt"));
                    for (Faculty f : facultys) {
                        objectOutputStream.writeObject(f);
                    }
                    objectOutputStream.close();

                    System.out.println("\n\t\t!!..Faculty Information Updated Successfully..!!");
                    break;
                }
            }

            if (!found) {
                System.out.println("No faculty record found with ID " + facultyId + ".");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }
    }
    
    
    public void deleteFaculty() {
        
        String id;
        do {
            System.out.print("Enter Faculty ID Number to Delete: ");
            id = input.nextLine().trim();

            if (!id.matches("[0-9]+")) {
                System.out.println("Invalid ID. Please enter only numeric digits.");
            } else if (id.isEmpty()) {
                System.out.println("ID cannot be empty. Please enter a value.");
            }
            else { }
        } while (!id.matches("[0-9]+") || id.isEmpty());
        
        int facultyId = Integer.parseInt(id);
        input.nextLine(); // Consume the newline character
        
            boolean found = false;
            for (int i = 0; i < facultys.size(); i++) {
                if (facultys.get(i).userId == facultyId) {
                    facultys.remove(i);
                    found = true;
                    break;
                }
            }

            if (found) {
            // Remove the booking from the file
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Bookingrecord.txt"))) {
                for (Faculty faculty : facultys) {
                    objectOutputStream.writeObject(faculty);
                }
                System.out.println("faculty Data Deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("faculty Data not found. Cancellation failed.");
        }
    }    
    public void viewFaculty() {
        System.out.println("\n\t!!..View Faculty Details..!!");
        String id;
        do {
            System.out.print("Enter Faculty ID Number: ");
            id = input.nextLine().trim();

            if (!id.matches("[0-9]+")) {
                System.out.println("Invalid ID. Please enter only numeric digits.");
            } else if (id.isEmpty()) {
                System.out.println("ID cannot be empty. Please enter a value.");
            }
            else { }
        } while (!id.matches("[0-9]+") || id.isEmpty());
        
        int facultyId = Integer.parseInt(id);
        input.nextLine(); // Consume the newline character  
        
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Facultyrecord.txt"));
            try {
                while (true) {
                    Faculty faculty = (Faculty) objectInputStream.readObject();
                    facultys.add(faculty);
                }
            } catch (EOFException e) {
                // Reached end of file
            }

            objectInputStream.close();

            boolean found = false;
            for (Faculty faculty : facultys) {
                if (faculty.userId == facultyId) {
                    found = true;
                    System.out.println("Faculty Details:");
                    System.out.println("-----------------------");
                    System.out.println("\nFaculty ID: " + faculty.userId);
                    System.out.println("First Name: " + faculty.firstName);
                    System.out.println("Last Name: " + faculty.lastName);
                    System.out.println("Designation: " + faculty.getDesignation());
                    System.out.println("CNIC: " + faculty.userCNIC);
                    System.out.println("Phone Number: " + faculty.userPhoneNo);
                    System.out.println("Email Address: " + faculty.userEMail);
                    System.out.println("Department: " + faculty.userDepartment);
                    System.out.println("-----------------------");
                    break;
                }
            }

            if (!found) {
                System.out.println("No faculty record found with ID " + facultyId + ".");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }
    }
}