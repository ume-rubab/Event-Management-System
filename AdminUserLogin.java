package project.oop;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;

public class AdminUserLogin extends JFrame implements Serializable {
    static ArrayList<Student> students=new ArrayList<>();
    JTextField t1, t2;
    JLabel l1, name,pass;
    JButton loginButton, signUpButton;
    boolean isStudent;
    boolean isFaculty;
     AdminUserLogin(boolean isAdmin,boolean isStudent, boolean isFaculty) {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1 = new JLabel("Login");
        name= new JLabel("Enter username:");
        pass = new JLabel("Enter Password:");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l1.setForeground(Color.GRAY);
        l1.setBounds(130, 10, 300, 40);
        name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        name.setForeground(Color.GRAY);
        name.setBounds(5, 60, 300, 40);
        pass.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pass.setForeground(Color.GRAY);
        pass.setBounds(5, 100, 300, 40);
        add(l1);
        add(name);
        add(pass);
        t1 = new JTextField(60);
        t2 = new JTextField(60);

        t1.setBounds(130, 60, 120, 30);
        t2.setBounds(130, 100, 120, 30);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(120, 140, 80, 30);
        loginButton.setBackground(Color.CYAN);
        add(t1);
        add(t2);
        add(loginButton);
        if (isStudent) {
            signUpButton = new JButton("SIGN UP");
            signUpButton.setBounds(120, 180, 80, 30);
            signUpButton.setBackground(Color.CYAN);
            add(signUpButton);
            signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    StudentRegistrationWindow s = new StudentRegistrationWindow();
                    s.setSize(300, 250);
                    s.setVisible(true);
                    
                }
            });

        }
        if (isFaculty) {
            signUpButton = new JButton("SIGN UP");
            signUpButton.setBounds(120, 180, 80, 30);
            signUpButton.setBackground(Color.CYAN);
            add(signUpButton);
            signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    FacultyRegistrationWindow f = new FacultyRegistrationWindow();
                    f.setSize(300, 250);
                    f.setVisible(true);
                    
                }
            });
        }
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean loginSuccessful = false;
               

                if (isAdmin) {
                    if (t1.getText().equals("Admin") && t2.getText().equals("123")) {
                        JOptionPane.showMessageDialog(null, "Admin Login successful!");
                        loginSuccessful = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin Login not successful!");
                    }
                } else {
                    String username = t1.getText();
                    String password = t2.getText();
                    if(isStudent){
                    if (studentCheckUserLogin(username, password,true)) {
                        JOptionPane.showMessageDialog(null, "User Login successful!");
                        loginSuccessful = true;


                    } 
                    else if(isFaculty){
                        if (facultycheckUserLogin(username, password,isFaculty)) {
                            JOptionPane.showMessageDialog(null, "User Login successful!");
                            loginSuccessful = true;
                        } 
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                    }
                }}
                if(isAdmin){
                if (loginSuccessful) {
                    Admin a=new Admin();
                    a.AdminMenu();
                    setVisible(false);
                    dispose();
                }}
                if(isStudent){
                    if (loginSuccessful) {
                        Student s=new Student();
                        s.StudentMenu();
                        setVisible(false);//to close login window
                        dispose();
                    }
                }
                if(isFaculty){
                    if (loginSuccessful) {
                        Faculty f=new Faculty();
                        f.FacultyMenu();
                        setVisible(false);//to close login window
                        dispose();
                    }
                }
            }
        });
        }
   public boolean studentCheckUserLogin(String username, String password, boolean isStudent) {
    try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Studentrecord.txt"));
        while (true) {
            try {
                Student student = (Student) ois.readObject();
                students.add(student);
            } catch (EOFException e) {
                break;
            }
        }
        for (Student student : students) {
            if( student.username!=null && student.username.equalsIgnoreCase(username) && student.password!=null&& student.password.equals(password)) {
                return true;
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println(e);
    }
    return false;
}

    public boolean facultycheckUserLogin(String username, String password, boolean isFaculty) {
    try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Facultyrecord.txt"));
        ArrayList<Faculty> faculties = new ArrayList<>();
        while (true) {
            try {
                Faculty faculty = ( Faculty) ois.readObject();
                faculties.add(faculty);
            } catch (EOFException e) {
                break;
            }
        }
        for (Faculty faculty : faculties) {
            if (faculty.username!=null&&faculty.username.equalsIgnoreCase(username) &&faculty.password!=null&& faculty.password.equals(password)) {
                return true;
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println(e);
    }
    return false;
}
    public  class FirstWindow extends JFrame {
        JButton adminButton, studentButton,facultyButton;
        JLabel loginLabel;

        FirstWindow() {
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            adminButton = new JButton("Admin");
            studentButton = new JButton("Student");
            facultyButton = new JButton("Faculty");
            loginLabel = new JLabel("LOGIN/SIGNUP");
            loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
            loginLabel.setForeground(Color.BLACK);
            studentButton.setBackground(Color.CYAN);
            facultyButton.setBackground(Color.CYAN);
            adminButton.setBackground(Color.CYAN);
            loginLabel.setBounds(90, 10, 200, 30);
            adminButton.setBounds(100, 50, 110, 30);
            studentButton.setBounds(100, 100, 110, 30);
            facultyButton.setBounds(100, 150, 110, 30);
            add(adminButton);
            add(studentButton);
            add(facultyButton);
            add(loginLabel);
            adminButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    AdminUserLogin adminLoginWindow = new AdminUserLogin(true,false,false);
                    adminLoginWindow.setSize(300, 300);
                    adminLoginWindow.setVisible(true);
                }
            });
            facultyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                //public static boolean isFaculty=true;
                    AdminUserLogin facultyLoginWindow = new AdminUserLogin(false,false,true);
                    facultyLoginWindow.setSize(300, 300);
                    facultyLoginWindow.setVisible(true);
                }
            });
            studentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                  //  public static boolean isStudent=true;
                    AdminUserLogin studentLoginWindow = new AdminUserLogin(false,true,false);
                    studentLoginWindow.setSize(300, 300);
                    studentLoginWindow.setVisible(true);
                    
                }
            });
        }
    }

    public class StudentRegistrationWindow extends JFrame implements Serializable{
        JLabel registerLabel, usernameLabel, passwordLabel,confirmPassLabel;
        JTextField usernameField,passwordField,confirmPassField;
        JButton submitButton;

        StudentRegistrationWindow() {
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            registerLabel = new JLabel("STUDENT SIGNUP");
            registerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
            registerLabel.setForeground(Color.BLACK);
            registerLabel.setBounds(90, 10, 200, 30);
            add(registerLabel);

            usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(20, 50, 80, 30);
            add(usernameLabel);

            passwordLabel = new JLabel("Password:");
            passwordLabel .setBounds(20, 90, 80, 30);
            add(passwordLabel );

            confirmPassLabel = new JLabel("Confirm Password:");
            confirmPassLabel .setBounds(20, 130, 80, 30);
            add(confirmPassLabel );

            usernameField = new JTextField(60);
            usernameField.setBounds(100, 50, 120, 30);
            add(usernameField);

            passwordField=new JTextField();
            passwordField.setBounds(100, 90, 120, 30);
            add(passwordField);

            confirmPassField= new JTextField(60);
            confirmPassField.setBounds(100, 130, 120, 30);
            add(confirmPassField);

            submitButton = new JButton("SUBMIT");
            submitButton.setBounds(120, 170, 80, 30);
            submitButton.setBackground(Color.CYAN);
            add(submitButton);

            submitButton.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent ae) {
                    Student student = new Student();
                    student.username = usernameField.getText();
                    student.password = passwordField.getText();
                     students.add(student);
                 try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Studentrecord.txt"))) {
                     for (Student s: students) {
                    objectOutputStream.writeObject(s);
            }
                } 
                 catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Student Signup not successful!");
                    }
                            JOptionPane.showMessageDialog(null, "Student Signup successful!");
                            Student s=new Student();
                            s.StudentMenu();
    
                            usernameField.setText("");
                            passwordField.setText("");
                            confirmPassField.setText("");
                }
            });
        }}
    public  class FacultyRegistrationWindow extends JFrame implements Serializable {
        JLabel registerLabel, usernameLabel, DesignationLabel,passwordLabel,  confirmPassLabel;
        JTextField usernameField,passwordField,confirmPassField;
        JButton submitButton;
    
        FacultyRegistrationWindow() {
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
    
            registerLabel = new JLabel("FACULTY REGISTRATION");
            registerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
            registerLabel.setForeground(Color.BLACK);
            registerLabel.setBounds(90, 10, 200, 30);
            add(registerLabel);
    
            usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(20, 50, 80, 30);
            add(usernameLabel);
    
            passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(20, 90, 80, 30);
            add(passwordLabel);

            confirmPassLabel = new JLabel("Confirm Password:");
            confirmPassLabel .setBounds(20, 130, 80, 30);
            add(confirmPassLabel );

            usernameField = new JTextField(60);
            usernameField.setBounds(100, 50, 120, 30);
            add(usernameField);

            passwordField=new JTextField(60);
            passwordField.setBounds(100, 90, 120, 30);
            add(passwordField);

            confirmPassField= new JTextField(60);
            confirmPassField.setBounds(100, 130, 120, 30);
            add(confirmPassField);

            submitButton = new JButton("SUBMIT");
            submitButton.setBounds(120, 170, 80, 30);
            submitButton.setBackground(Color.CYAN);
            add(submitButton);
    
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    String Fusername = usernameField.getText();
                    String Fpassword = passwordField.getText();
                    Faculty faculty = new Faculty();
                    faculty.username = Fusername;
                    faculty.password = Fpassword;
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Studentrecord.txt",true));
                        objectOutputStream.writeObject(faculty);
                        objectOutputStream.close();
                        }
                        catch (IOException e) 
                        {
                        System.out.println("Error writing to file: " + e.getMessage());
                        }
                            JOptionPane.showMessageDialog(null, "Faculty Signup successful!");
                            Faculty f=new Faculty();
                            f.FacultyMenu();
    
                            usernameField.setText("");
                            passwordField.setText("");
                            confirmPassField.setText("");
                            
                    }
                });
            }
        }          
    public void callGui() {
        FirstWindow firstWindow = new FirstWindow();
        firstWindow.setSize(300, 300);
        firstWindow.setVisible(true);
    }
}
