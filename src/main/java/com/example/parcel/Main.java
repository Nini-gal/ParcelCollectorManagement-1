package com.example.parcel;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
        long demoTrackingNum = 680078088310212L;
        Parcel studentParcel = new Parcel(demoTrackingNum, "Fathini", "2025801536@student.uitm.edu.my", "0123456789", "Block Sutera, Bilik 230", "2026-06-20", "Registered");
        Student student = new Student("Fathini", "S001", "2025801536@student.edu.my", 123456789,
                "Block A, Room 230", "2025801536",demoTrackingNum , studentParcel, 0);
        parcelCentre.addParcel(studentParcel, "Parcel with tracking number" + demoTrackingNum + "has been added to the system.");

        System.out.println("Please log in to the system.");
        System.out.print("Enter username: ");
        String username = in.nextLine();
        System.out.print("Enter password: ");
        String password = in.nextLine(); 

        if (username.equals("admin") && password.equals("password")){
            System.out.println("Login successful! Welcome, " + username + ".");
            runAdminMenu(in, parcelCentre);
        }else{
            runStudentFlow(in, student, parcelCentre);
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
            System.out.println("6. Exit");

            System.out.println("Pick : ");
            int choice = in.nextInt();
            in.nextLine();


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
                                
            System.out.println("Enter tracking number (15 digits): ");
            long trackingNum = in.nextLong();
            in.nextLine();

            //validation to ensure the tracking number only insist 15 digits only
            if(!isValidTrackingNum(trackingNum)){
                System.out.println("Tracking number must be 15 digits only. Parcel not registered.");
                System.out.println("Would you like to register another parcel? (yes/no)");
                response = in.nextLine();
                continue;
            }

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
        for(Parcel parcel: parcelCentre.getAllParcels()){
            System.out.println(parcel.getParcelDetails());
            System.out.println("---");
        }
    }

    //for case 3 updating status of parcel
    private static void updateParcelStatus(Scanner in, ParcelCentre parcelCentre){
        System.out.print("Enter tracking number to update: ");
        long trackingNum = in.nextLong();
        in.nextLine();
 
        System.out.print("Enter new status: ");
        String newStatus = in.nextLine();
 
        if(parcelCentre.updateParcelStatusByTracking(trackingNum, newStatus)){
            System.out.println("Parcel status updated successfully.");
        }else{
            System.out.println("No parcel found with that tracking number.");
        }
    }

    //for case 4 removing parcel from the hub
    private static void deleteParcel(Scanner in, ParcelCentre parcelCentre){
        System.out.print("Enter tracking number to be deleted: ");
        long trackingNum = in.nextLong();
        in.nextLine();
 
        Parcel removed = parcelCentre.removeParcelByTracking(trackingNum);
        if(removed != null) {
            System.out.println("Parcel " + trackingNum + " was deleted.");
        }else{
            System.out.println("No parcel found with that tracking number.");
        }
    }

    //case 5 allows admin to update status of the parcel
    private static void sendNotification(Scanner in, ParcelCentre parcelCentre){
        List<Parcel> unclaimed = new ArrayList<>();
        System.out.print("Enter tracking number of the parcel: ");
        long trackingNum = in.nextLong();
        in.nextLine();
 
        Parcel parcel = parcelCentre.findParcelByTracking(trackingNum);
        if(parcel == null){
            System.out.println("No parcel found with that tracking number.");
            return;
        }

        for(Parcel p : parcelCentre.getAllParcels()){
            if(!parcel.getStatus().equalsIgnoreCase("Claimed")){
                unclaimed.add(parcel);
            }
        }

        if(unclaimed.isEmpty()){
            System.out.println("No unclaimed parcels. No Reminder to send.");
            return;
        }
 
        System.out.print("Sending pickup remainders for "+ unclaimed.size() + " unclaimed parcel(s)...");
        for(Parcel unclaimedParcel : unclaimed){
            String message = "REMINDER: Your parcel (Tracking No: " + unclaimedParcel.getTrackingNum()
                    + ") is ready for pickup at " + parcelCentre.getCentreName()
                    + ". Please collect it as soon as possible.";
            parcelCentre.sendNotification(unclaimedParcel.getRecipientEmail(), message);
        }
        System.out.println("Reminders sent to all unclaimed parcel recipients.");
    }

    //if none of the admins password runs, then student gets to use it
    private static void runStudentFlow(Scanner in, Student student, ParcelCentre parcelCentre){
        System.out.print("Enter matric number: ");
        String matricNum = in.nextLine();


        if(!matricNum.equals(student.getMatricNum())){
            System.out.println("Matric number not recognized.");
            return;
        }

        //student's otp generated
        int otp = student.generateOTP();
        System.out.println("Your OTP is : " + otp);

        //to get the list of parcels form of array
        List<Parcel> parcelsToClaim = new ArrayList<>();

        System.out.println("Enter tracking number to collect, once every visit.");
        System.out.println("Enter 'done' when finished (max " + Student.MAX_PARCELS_PER_PICKUP + " parcels per visit).");

        while(parcelsToClaim.size() < Student.MAX_PARCELS_PER_PICKUP){
            System.out.print("Enter tracking number (or 'done'): ");
            String input = in.nextLine().trim();

            if(input.equalsIgnoreCase("done")){
                break;
            }

            long trackingNum;

            //ensure the tracking number entered are valid in 15 digit format or not
            try{
                trackingNum = Long.parseLong(input);
            }catch(NumberFormatException e){
                System.out.println("Invalid tracking number entered. Please try again.");
                continue;
            }

            //find the tracking number in parcel centre 
            Parcel parcel = parcelCentre.findParcelByTracking(trackingNum);
            if(parcel == null){
                System.out.println("Not parcel found.");
                continue;
            }

            //to tell the student whether the parcel already claimed yet or not
            if(parcel.getStatus().equalsIgnoreCase("Claimed")){
                System.out.println("The parcel already claimed.");
                continue;
            }

            //to ensure no collision of same parcel over and over again
            if(parcelsToClaim.contains(parcel)){
                System.out.println("The parcel already added to the hub.");
                continue;
            }

            parcelsToClaim.add(parcel);
            System.out.println("Parcel added. (" + parcelsToClaim.size() + " / " + Student.MAX_PARCELS_PER_PICKUP + ")");
        }

        //what if the parcel to be claimed empty?
        //we need to send message
        if(parcelsToClaim.isEmpty()){
            System.out.println("No parcel to be claimed.");
            return;
        }

        //what if the parcels reached limit?, display
        if(parcelsToClaim.size() == Student.MAX_PARCELS_PER_PICKUP){
            System.out.println("Number of parcels reached the maximum of " + Student.MAX_PARCELS_PER_PICKUP + " parcels for the visit.");

        }

        System.out.print("Enter OTP for verification: ");
        int enteredOTP = in.nextInt();
        in.nextLine();
 
        if(!student.verifyOTP(enteredOTP)){
            System.out.println("Invalid OTP. Please try again.");
            return;
        }

        //to count how much the student need to pay based on the quantity of the parcel thats been collected
        double totalCost = parcelCentre.claimParcels(parcelsToClaim);
        System.out.println("OTP verified successfully.");

        System.out.println(parcelsToClaim.size() + " parcel(s) claimed successfully.");
        System.out.printf("Total cost: RM%.2f ", totalCost);

        //creating receipt to record the parcel's collected
        String receipt = buildReceipt(student, parcelsToClaim, totalCost);
        System.out.println();
        System.out.println(receipt);
        saveReceiptToFile(student, receipt);

    }

    //build a format of receipt listing only student, every parcel claimed
    private static String buildReceipt(Student student, List<Parcel> parcelsToClaimed, double totalCost){
        StringBuilder sb = new StringBuilder();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
 
        sb.append("===== PARCEL PICKUP RECEIPT =====\n");
        sb.append("Date/Time: ").append(timestamp).append("\n");
        sb.append("Collected by: ").append(student.getName()).append("\n");
        sb.append("Matric Number: ").append(student.getMatricNum()).append("\n");
        sb.append("----------------------------------\n");
 
        int i = 1;
        for (Parcel parcel : parcelsToClaimed) {
            sb.append("Parcel ").append(i).append(":\n");
            sb.append(parcel.getParcelDetails()).append("\n");
            sb.append("----------------------------------\n");
            i++;
        }
 
        sb.append("Total parcels collected: ").append(parcelsToClaimed.size()).append("\n");
        sb.append(String.format("Total cost: RM%.2f (RM%.2f x %d parcel(s))%n",
                totalCost, Student.CHARGE_PER_PARCEL, parcelsToClaimed.size()));
        sb.append("==================================");
 
        return sb.toString();
    }

    //adding receipt for those paid the claimed parcel
    private static void saveReceiptToFile(Student student, String receipt){
        String timestampForFile = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "receipt_" + student.getMatricNum() + "_" + timestampForFile + ".txt";
 
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(receipt);
            System.out.println("\nReceipt saved to: " + fileName);
        }catch (IOException e){
            System.out.println("\nCould not save receipt to file: " + e.getMessage());
        }
    }

    //tracking number 15 digits only with help of claude
    private static boolean isValidTrackingNum(long trackingNum){
        return trackingNum >= 100_000_000_000_000L && trackingNum <= 999_999_999_999_999L;
    }
}