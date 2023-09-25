package project.oop;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Admin

{
    private ArrayList<Room> rooms;
    private String username;
    private String password;
    private Scanner input;

    public Admin() {
        username = "Admin";
        password = "123";
        rooms = new ArrayList<Room>();
        input = new Scanner(System.in);
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void AdminMenu(){
        while (true) {
                System.out.println("\n\t *** ADMIN MENU ***");
                System.out.println("1. Add Room");
                System.out.println("2. View Rooms");
                System.out.println("3. Update Room");
                System.out.println("4. Delete Room");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addRoom();
                        break;
                    case 2:
                        viewRoom();
                        break;
                    case 3:
                        updateRoom();
                        break;
                    case 4:
                        deleteRoom();
                        break;
                    case 5:
                         System.out.println("Exiting program...");
                            System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    
    public void addRoom() 
    {
        System.out.println("\t!!..Enter Room Details..!!");
        System.out.print("Enter Room No: ");
        String rollNo = checkRoomNO();
        int rNo = Integer.parseInt(rollNo);
        input.nextLine(); // Consume the newline character

        boolean matchFound = true;
        while (matchFound) {
            matchFound = false;
            for (Room room : rooms) {
                if (room.getRoomNo() == rNo) {
                    System.out.println("This room number is already taken...");
                    rollNo = checkRoomNO();
                    rNo = Integer.parseInt(rollNo);
                    input.nextLine(); // Consume the newline character
                    matchFound = true;
                    break;
                }
            }
        }

        System.out.print("Enter Room Capacity: ");
        int capacity = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.print("Enter Room Type (Classroom, lab, seminarHall): ");
        String type = checkRoomType();

        System.out.print("Enter department of new Room: ");
        String department = checkDepartment();

        Room newRoom = new Room();
        newRoom.setRoomNo(rNo);
        newRoom.setCapacity(capacity);
        newRoom.setType(type);
        newRoom.setDepartment(department);

        rooms.add(newRoom);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Roomrecord.txt"));
            for (Room room : rooms) {
                objectOutputStream.writeObject(room);
            }
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("Room added Successfully!");
    }
    
    public void deleteRoom() {
    System.out.print("Enter Room No: ");
    String rollNo = checkRoomNO();
    int roomNo = Integer.parseInt(rollNo);
    input.nextLine(); // Consume the newline character

    try {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Roomrecord.txt"));
        try {
            while (true) {
                Room room = (Room) objectInputStream.readObject();
                rooms.add(room);
            }
        } catch (EOFException e) {
            // Reached end of file
        }
        objectInputStream.close();

        boolean roomFound = false;
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) {
                rooms.remove(room);
                roomFound = true;
                break;
            }
        }

        if (roomFound) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Roomrecord.txt"));
                for (Room room : rooms) {
                    objectOutputStream.writeObject(room);
                }
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
            System.out.println("Room deleted successfully!!");
        } else {
            System.out.println("Sorry...Room not Found!");
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error accessing file: " + e.getMessage());
    }
}

    public void updateRoom() {
    System.out.println("Enter Room Number to Update:");
    int roomNo = input.nextInt();
    input.nextLine(); // Consume the newline character
    boolean roomFound = false;

    try {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Roomrecord.txt"));
        try {
            while (true) {
                Room room = (Room) objectInputStream.readObject();
                rooms.add(room);
            }
        } catch (EOFException e) {
            // Reached end of file
        }
        objectInputStream.close();

        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) {
                roomFound = true;
                break;
            }
        }

        if (roomFound) {
            for (Room room : rooms) {
                if (room.getRoomNo() == roomNo) {
                    System.out.print("Enter new Room Capacity: ");
                    int capacity = input.nextInt();
                    input.nextLine(); // Consume the newline character

                    System.out.print("Enter new Room Type (Classroom, lab, seminarHall): ");
                    String type = checkRoomType() ;

                    System.out.print("Enter new department of the Room: ");
                    String department = checkDepartment();

                    room.setCapacity(capacity);
                    room.setType(type);
                    room.setDepartment(department);
                    break;
                }
            }

            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Roomrecord.txt"));
                for (Room room : rooms) {
                    objectOutputStream.writeObject(room);
                }
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
            System.out.println("Room information updated successfully!");
        } else {
            System.out.println("Sorry...Room not Found!");
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error accessing file: " + e.getMessage());
    }
}


    public void viewRoom() {
    System.out.print("Enter Room No to View: ");
    String rollNo = checkRoomNO();
    int roomNo = Integer.parseInt(rollNo);
    input.nextLine(); // Consume the newline character

    boolean roomFound = false;

    try {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Roomrecord.txt"));
        try {
            while (true) {
                Room room = (Room) objectInputStream.readObject();
                rooms.add(room);
            }
        } catch (EOFException e) {
            // Reached end of file
        }
        objectInputStream.close();

        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) {
                roomFound = true;
                System.out.println("Room Details:");
                System.out.println("-----------------------");
                System.out.println("Room No: " + room.getRoomNo());
                System.out.println("Capacity: " + room.getCapacity());
                System.out.println("Type: " + room.getType());
                System.out.println("Department: " + room.getDepartment());
                System.out.println("-----------------------");
                break;
            }
        }

        if (!roomFound) {
            System.out.println("Room not Found!");
        }

    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error accessing file: " + e.getMessage());
    }
}

    public void searchRoom(int attendees) {
    boolean roomFound = false;

    try {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Roomrecord.txt"));
        try {
            while (true) {
                Room room = (Room) objectInputStream.readObject();
                rooms.add(room);
            }
        } catch (EOFException e) {
            // Reached end of file
        }
        objectInputStream.close();

        for (Room room : rooms) {
            if (room.getCapacity() >= attendees) {
                System.out.println("Available Rooms:");
                System.out.println("-----------------------");
                System.out.println("Room No: " + room.getRoomNo());
                System.out.println("Capacity: " + room.getCapacity());
                System.out.println("Type: " + room.getType());
                System.out.println("Department: " + room.getDepartment());
                System.out.println("-----------------------");
                roomFound = true;
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error searching room: " + e.getMessage());
    }

    if (!roomFound) {
        System.out.println("No available rooms found for the specified capacity.");
    }
}
    public Room findRoomByNumber(int roomNumber) {
    try {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Roomrecord.txt"));
        try {
            while (true) {
                Room room = (Room) objectInputStream.readObject();
                rooms.add(room);
            }
        } catch (EOFException e) {
            // Reached end of file
        }
        objectInputStream.close();

        for (Room room : rooms) {
            if (room.getRoomNo() == roomNumber) {
                return room;
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error accessing file: " + e.getMessage());
    }

    return null; 
    } 
    public String checkRoomType() 
    {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("[a-zA-Z]+");
            if (!valid) {
                System.out.println("Enter Correct Room type  (e.g class,seminar,Lab): ");
            }
        } 
        while (!valid);
        return s;
    }
    public String checkDepartment() 
    {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("[a-zA-Z]+");
            if (!valid) {
                System.out.println("Enter Correct Department (e.g Computer,English,BBA): ");
            }
        } 
        while (!valid);
        return s;
    }
    public String checkRoomNO() 
    {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("^\\d+$");
            if (!valid) {
                System.out.println("Enter Correct Room NO  (e.g 121): ");
            }
        } 
        while (!valid);
        return s;
    }
}


