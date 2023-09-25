package project.oop;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class BookingEvent implements Serializable
{
    private ArrayList<BookingEvent> bookings;
    private transient Scanner input; // Use transient keyword to exclude Scanner from serialization1
    
    private String date;
    private String time;
    private String eventType;
    private int bookingId;
    private int userId;
    private int roomNo;
    private int attendees;
    
    public BookingEvent() {
        bookings = new ArrayList<>();
        input = new Scanner(System.in);
        date = null;
        time = null;
        eventType = null;
        bookingId = 0;
        userId = 0;
        roomNo = 0;
        attendees = 0;
  }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public String getEventType() {
        return eventType;
    }
    public void setBookingId(int bookingId) 
    {
        this.bookingId = bookingId;
    }
  public int getBookingId() {
        return bookingId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
    
    public int getRoomNo() {
        return roomNo;
    }
    
    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }
    
    public int getAttendees() {
        return attendees;
    }
    
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Booking event menu ");
        BookingEvent bookingEvent = new BookingEvent();
        bookingEvent.EventBookingMenu();
    }
    
//        public static void main (String[] args){
//        Scanner input = new Scanner(System.in);
//        System.out.println("Welcome to the Smart Room Booking Event System!");
//
//        // Create an instance of the BookingEvent class
//        BookingEvent bookingEvent = new BookingEvent();
//
//        // Create a menu for user interaction
//        int choice;
//        do {
//            System.out.println("\n----- Menu -----");
//            System.out.println("1. Create Booking");
//            System.out.println("2. Cancel Booking");
//            System.out.println("3. update Booking");
//            System.out.println("4. View Booking");
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            choice = input.nextInt();
//            input.nextLine(); // Consume the remaining newline character
//
//            switch (choice) {
//                case 1:
//                    bookingEvent.createBooking();
//                    break;
//                case 2:
//                    bookingEvent.cancelBooking();
//                    break;  
//                case 3:
//                    bookingEvent.updateBooking();
//                    break; 
//                case 4:
//                    bookingEvent.viewBooking();
//                    break;     
//                case 0:
//                    System.out.println("Exiting the program. Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        } while (choice != 2);
//    }
    
    public  void EventBookingMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Smart Room Booking Event System!");
        
        // Create a menu for user interaction
        String choice;
        while(true){
            System.out.println("\n----- Event Booking Menu -----");
            System.out.println("1. Create Booking");
            System.out.println("2. Cancel Booking");
            System.out.println("3. update Booking");
            System.out.println("4. View Booking");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.next();
            while(choice.isBlank())
            {
                System.out.println("Field cannot be empty..enter an option:");
                choice = input.next();
                input.nextLine(); // Consume the remaining newline character
            }
            int ch;
            try {
            ch = Integer.parseInt(choice);
            switch (ch) {
                case 1:
                    createBooking();
                    break;
                case 2:
                    cancelBooking();
                    break;  
                case 3:
                    updateBooking();
                    break; 
                case 4:
                    viewBooking();
                    break;     
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(ch);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            } catch(NumberFormatException e){
                    System.out.println("Invalid input. Please enter a valid number.");
                    }
    }
    }
        
        
    public void createBooking() {
        System.out.println("\t!!..Enter Booking Details..!!");
        int bookingId = (int) (Math.random() * 999);
        System.out.println("Your Booking Id Number: " + bookingId);
        System.out.print("Enter Your Id Number: ");
        String id = input.next();
        input.nextLine(); // Consume the remaining newline character

        System.out.print("Enter Date in (dd/mm/yyyy) format: ");
        String date = checkDate();

        System.out.print("Enter time in the format HH:MM (24-hour format): ");
        String time = checkTime();

        System.out.print("Enter The Event Type of Booking: ");
        String eventType = checkEventType();

        System.out.print("Enter The Number of Attendees: ");
        int attendees = input.nextInt();

        // Check for available rooms that match the specified date, time, and capacity
        Admin admin = new Admin();
        admin.searchRoom(attendees);
        System.out.print("Enter the Room Number for your booking: ");
        int roomNumber = input.nextInt();

        // Find the selected room from the available rooms
        Room selectedRoom = admin.findRoomByNumber(roomNumber);
        if (selectedRoom == null) {
            System.out.println("Invalid room number. Booking creation failed.");
            return;
        }

        // Check if the room is available at the specified date and time
        if (!checkRoom(selectedRoom, date, time)) {
            System.out.println("The room is already booked at the specified date and time. Booking creation failed.");
            return;
        }

        // Create the booking
        BookingEvent booking = new BookingEvent();
        booking.setDate(date);
        booking.setTime(time);
        booking.setEventType(eventType);
        booking.setBookingId(bookingId);
        booking.setUserId(Integer.parseInt(id));
        booking.setRoomNo(selectedRoom.getRoomNo());
        booking.setAttendees(attendees);

        // Store the booking in the system (e.g., add it to the ArrayList)
        bookings.add(booking);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Bookingrecord.txt"))) {
            for (BookingEvent b : bookings) {
                objectOutputStream.writeObject(b);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("Booking created and added successfully!");
    }

    public void cancelBooking() {
        System.out.println("\t!!..Cancel Booking..!!");
        System.out.print("Enter the Booking ID: ");
        String id = checkId();
        int bookingId = Integer.parseInt(id);
        input.nextLine(); // Consume the remaining newline character

        // Check if the booking exists in the record
        boolean bookingFound = false;

        for (BookingEvent booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                bookings.remove(booking);
                bookingFound = true;
                break;
            }
        }

        if (bookingFound) {
            // Remove the booking from the file
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Bookingrecord.txt"))) {
                for (BookingEvent booking : bookings) {
                    objectOutputStream.writeObject(booking);
                }
                System.out.println("Booking canceled successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Booking not found. Cancellation failed.");
        }
    }

    public void updateBooking() {
        System.out.println("\t!!..Update Booking..!!");
        System.out.print("Enter the Booking ID: ");
        String id = checkId();
        int bookingId = Integer.parseInt(id);
        input.nextLine(); // Consume the remaining newline character
        
        boolean bookingFound = false;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Bookingrecord.txt"))) {
            try {
                while (true) {
                    BookingEvent booking = (BookingEvent) objectInputStream.readObject();
                    bookings.add(booking);
                }
            } catch (EOFException e) {
                // Reached end of file
            }
            
            for (BookingEvent booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    System.out.print("Enter The New Date in (dd/mm/yyyy) format: ");
                    String newDate = checkDate();
                    booking.setDate(newDate);

                    System.out.print("Enter The New Time in the format HH:MM (24-hour format): ");
                    String newTime = checkTime();
                    booking.setTime(newTime);

                    System.out.print("Enter The New Event Type of Booking: ");
                    String newEventType = checkEventType();
                    booking.setEventType(newEventType);

                    System.out.print("Enter The New Number of Attendees: ");
                    int newAttendees = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    Admin admin = new Admin();
                    admin.searchRoom(newAttendees);
                    booking.setAttendees(newAttendees);

                    System.out.print("Enter The New Room Number for the Booking: ");
                    int newRoomNumber = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    // Check if the new room number exists in the room record file
                    Room newRoom = admin.findRoomByNumber(newRoomNumber);
                    if (newRoom == null) {
                        System.out.println("Invalid room number. Booking update failed.");
                        return;
                    }
                    // Check if the room is available at the specified date and time
                    if (!checkRoom(newRoom, newDate ,newTime)) {
                        System.out.println("The room is already booked at the specified date and time. Booking creation failed.");
                        return;
                    }
                    // Update the booking information
                    booking.setRoomNo(newRoom.getRoomNo());

                    // Update the booking in the file
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Bookingrecord.txt"));
                    for (BookingEvent updatebooking : bookings) {
                        objectOutputStream.writeObject(updatebooking);
                    }
                    objectOutputStream.close();
                    System.out.println("Booking updated successfully!");
                    bookingFound = true;
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error updating Booking information: " + e.getMessage());
        }

        if (!bookingFound) {
            System.out.println("Sorry...Booking not Found!");
        }
    }
    public void viewBooking() {
        System.out.println("\t!!..Cancel Booking..!!");
        System.out.print("Enter the Booking ID: ");
        String id = checkId();
        int bookingId = Integer.parseInt(id);
        input.nextLine(); // Consume the newline character

        boolean bookingFound = false;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Bookingrecord.txt"));
            try {
                while (true) {
                    BookingEvent booking = (BookingEvent) objectInputStream.readObject();
                    bookings.add(booking);
                }
            } catch (EOFException e) {
                // Reached end of file
            }
            objectInputStream.close();

            for (BookingEvent booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    System.out.println("Booking Details:");
                    System.out.println("-----------------------");
                    System.out.println("User Id: " + booking.getUserId());
                    System.out.println("Date: " + booking.getDate());
                    System.out.println("Time: " + booking.getTime());
                    System.out.println("Room Number: " + booking.getRoomNo());
                    System.out.println("Attendees: " + booking.getAttendees());
                    System.out.println("Event Type: " + booking.getEventType());
                    System.out.println("-----------------------");
                    bookingFound = true;
                    break;
                }
            }

            if (!bookingFound) {
                System.out.println("Booking not Found!");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }
    }
    public String checkDate() {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
            if (!valid) {
                System.out.println("Enter a correct Enter Date in (dd/mm/yyyy) format: ");
            }
        } while (!valid);
        return s;
    }
    public String checkTime() {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            if (!valid) {
                System.out.println("Enter a correct time in the format HH:MM (24-hour format): ");
            }
        } while (!valid);
        return s;
    }
    public boolean checkRoom(Room room, String date, String time) {
        for (BookingEvent booking : bookings) {
            if (booking.getRoomNo() == room.getRoomNo() && booking.getDate().equals(date) && booking.getTime().equals(time)) {
                return false; // Room is already booked at the specified date and time
            }
        }
        return true; // Room is available at the specified date and time
    }
    public String checkEventType() 
    {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("[a-zA-Z]+");
            if (!valid) {
                System.out.println("Enter Correct Event type  (e.g Lecture): ");
            }
        } 
        while (!valid);
        return s;
    }
    public String checkId() 
    {
        String s;
        boolean valid;
        do {
            s = input.nextLine();
            valid = s.matches("^\\d+$");
            if (!valid) {
                System.out.println("Enter Correct Id  (e.g 121): ");
            }
        } 
        while (!valid);
        return s;
    }
}