package com.example.parcel;

import java.util.Scanner;

import com.example.parcel.model.Admin;
import com.example.parcel.model.Parcel;
import com.example.parcel.model.ParcelCentre;
import com.example.parcel.model.Student;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Parcel Collector Management System!");

        //central registry of parcels for camous centre
        ParcelCentre parcelCentre = new ParcelCentre("UITM Kuala Terengganu Parcel Centre", "Admin", "No new messages");

        //demo student account with a parcel already linked to it
        //real system this would come from a database look up at login time
        Parcel studentParcel = new Parcel(650849722292L, "Fathini", "jane@student.uitm.edu.my", "0123456789", "Block Sutera, Bilik 230", "2026-06-20", "Registered");
        Student student = new Student("Fathini", "S001", "jane@student.edu", 123456789,
                "Block A, Room 12", "2025801536", 1001, studentParcel, 0);
        parcelCentre.addParcel(studentParcel, "Parcel with tracking number 1001 has been added to the system.");

        System.out.println("Please log in to the system.");
        System.out.print("Enter username: ");
        String username = in.nextLine();
        System.out.print("Enter password: ");
        String password = in.nextLine();

        if (username.equals("admin") && password.equals("password")){
            System.out.println("Login successful! Welcome, " + username + ".");
            runAdminMenu(in, parcelCentre);
        }else{
            runStudentFlow(in, student);
        }

        in.close();
    }

    private static void runAdminMenu(Scanner in, ParcelCentre parcelCentre){
        boolean exit = false;

        while(!exit){
           //admin functionalities
            System.out.println("Admin functionalities:");
            System.out.println("1. Register new parcel");
            System.out.println("2. View all parcels");
            System.out.println("3. Update parcel status");
            System.out.println("4. Delete parcel");
            System.out.println("5. Send notification to student");
            System.out.println("6. Assign staff to parcel centre");
            System.out.println("7. Register new admin");
            System.out.println("8. Exit");

            System.out.println("Pick : ");
            int choice = in.nextInt();
            in.nextLine();

            int count = 0; 

            switch(choice){
                case 1: 
                    registerParcels(in, parcelCentre);
                    break;
                case 2:
                    viewAllParcels(parcelCentre);
                    break;
                case 3:
                    updateParcelStatus(in, parcelCentre);
                    //update the parcel status immediately
                    break;
                case 4:
                    deleteParcel(in, parcelCentre);
                    break;
                case 5:
                    sendNotification(in, parcelCentre);
                    break;
                case 6:
                    assignStaff(in, parcelCentre);
                    //assign staff to the centre
                    break;
                case 7:
                    registerNewAdmin(in);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please pick a number from 1-8.");
            }
        }
    }

    //using methods for main class
    //more organized

    //for case1
    private static void registerParcels(Scanner in, ParcelCentre parcelCentre){
        System.out.println("Would you like to register a new parcel? (yes/no)");
        String response = in.nextLine();
        while(response.equalsIgnoreCase("yes")){
            if(response.equalsIgnoreCase("yes")){
                                
                System.out.println("Enter tracking number: ");
                String trackingNum = in.nextLine();

                System.out.println("Enter recipient name: ");
                String recipientName = in.nextLine();
                                
                System.out.println("Enter recipient email: ");
                String recipientEmail = in.nextLine();
                                
                System.out.println("Enter recipient phone: ");
                String recipientPhone = in.nextLine();
                                
                System.out.println("Enter delivery address: ");
                String deliveryAddress = in.nextLine();
                                
                System.out.println("Enter delivery date: ");
                String deliveryDate = in.nextLine();
                                
                String status = "Registered";
                                
                Parcel newParcel = parcelCentre.registerParcel(trackingNum, recipientName, recipientEmail, recipientPhone, deliveryAddress, deliveryDate, status);
                parcelCentre.addParcel(newParcel, "Parcel with tracking number " + trackingNum + " has been added to the system.");
                                
                System.out.println("Parcel registered successfully.");
            }
                            
            System.out.println("Would you like to register another parcel? (yes/no)");
            response = in.nextLine();
        }
    }

    //for case2 viewing all the parcels
    private static void viewAllParcels(ParcelCentre parcelCentre){
        if(parcelCentre.getAllParcels().isEmpty()){
            System.out.println("No parcel registered yet.");
            return;
        }
        for (Parcel parcel: parcelCentre.getAllParcels()){
            System.out.println(parcel.getParcelDetails());
            System.out.println("---");
        }
    }

    //for case 3 updating status of parcel
    private static void updateParcelStatus(Scanner in, ParcelCentre parcelCentre){
        System.out.print("Enter tracking number to update: ");
        int trackingNum = in.nextInt();
        in.nextLine();
 
        System.out.print("Enter new status: ");
        String newStatus = in.nextLine();
 
        if (parcelCentre.updateParcelStatusByTracking(trackingNum, newStatus)){
            System.out.println("Parcel status updated successfully.");
        } else {
            System.out.println("No parcel found with that tracking number.");
        }
    }

    private static void deleteParcel(Scanner in, ParcelCentre parcelCentre) {
        System.out.print("Enter tracking number to be deleted: ");
        int trackingNum = in.nextInt();
        in.nextLine();
 
        Parcel removed = parcelCentre.removeParcelByTracking(trackingNum);
        if (removed != null) {
            System.out.println("Parcel " + trackingNum + " was deleted.");
        } else {
            System.out.println("No parcel found with that tracking number.");
        }
    }
 
    private static void sendNotification(Scanner in, ParcelCentre parcelCentre) {
        System.out.print("Enter tracking number of the parcel: ");
        int trackingNum = in.nextInt();
        in.nextLine();
 
        Parcel parcel = parcelCentre.findParcelByTracking(trackingNum);
        if (parcel == null) {
            System.out.println("No parcel found with that tracking number.");
            return;
        }
 
        System.out.print("Enter message to send: ");
        String message = in.nextLine();
 
        parcelCentre.sendNotification(parcel.getRecipientEmail(), message);
    }
 
    private static void assignStaff(Scanner in, ParcelCentre parcelCentre) {
        System.out.print("Enter staff name to assign to this centre: ");
        String staffName = in.nextLine();
        parcelCentre.assignStaff(staffName);
        System.out.println(staffName + " has been assigned to " + parcelCentre.getCentreName() + ".");
    }
 
    private static void registerNewAdmin(Scanner in) {
        System.out.print("Enter name: ");
        String name = in.nextLine();
 
        System.out.print("Enter id: ");
        String id = in.nextLine();
 
        System.out.print("Enter email: ");
        String email = in.nextLine();
 
        System.out.print("Enter role: ");
        String role = in.nextLine();
 
        System.out.print("Enter password: ");
        String password = in.nextLine();
 
        Admin newAdmin = Admin.registerAdmin(name, id, email, role, password);
        System.out.println("Admin registered successfully: " + newAdmin.getName());
    }
 
    private static void runStudentFlow(Scanner in, Student student) {
        System.out.print("Enter matric number: ");
        String matricNum = in.nextLine();
 
        System.out.print("Enter tracking number: ");
        int trackingNum = in.nextInt();
        in.nextLine(); // consume the newline left by nextInt()
 
        if (!matricNum.equals(student.getMatricNum())) {
            System.out.println("Matric number not recognized.");
            return;
        }
 
        String status = student.getTrackingParcel(trackingNum);
        System.out.println("Status: " + status);
 
        if (!status.equals("Ready to pick up")) {
            return;
        }
 
        System.out.print("Enter OTP for verification: ");
        int otp = in.nextInt();
 
        if (student.verifyOTP(otp)) {
            System.out.println("OTP verified successfully.");
            System.out.println("Package claimed successfully.");
            student.claimPackage(otp);
        } else {
            System.out.println("Invalid OTP. Please try again.");
        }
    }
}