package project.oop;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class EMS extends JFrame {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        Admin ad = new Admin();
        System.out.println("-------------------- EVENT MANAGEMENT SYSTEM -----------------");
        System.out.println("-----------------------------Admin:-------------------------");
        System.out.println("_____________________________________________________________");
        AdminUserLogin l = new AdminUserLogin(true, true, true);
        System.out.println("Login/Signup:");
        l.callGui();
    }
}