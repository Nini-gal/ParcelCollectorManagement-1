package com.example.parcel.model;

import java.util.ArrayList;//jdk
import java.util.List;//jdk configuration

public class ParcelCentre{
    private String centreName;
    private String activeUser;
    //to store all the parcels in the parcel centre
    private final List<Parcel> parcelDatabase;
    private String message;

    public ParcelCentre(String centreName, String activeUser, String message){
        this.centreName = centreName;
        this.activeUser = activeUser;
        this.message = message;
        //Initialize the parcel database as an empty list
        this.parcelDatabase = new ArrayList<>();
    }

    //getter
    public String getCentreName(){
        return centreName;
    }
    
    public String getActiveUser(){
        return activeUser;
    }

    public String getMessage(){
        return message;
    }

    //setter
    public void setCentreName(String centreName){
        this.centreName = centreName;
    }

    public void setActiveUser(String activeUser){
        this.activeUser = activeUser;
    }

    public void setMessage(String message){
        this.message = message;
    }

    //method to assign staff to the parcel centre
    public void assignStaff(String admin){
        this.activeUser = admin;
    }

    //method to sent notification to the user about the parcel status
    public void sendNotification(String recipientEmail, String message){
        //code to send email notification to the user
        System.out.println("Notification sent to " + recipientEmail + ": " + message);
        //In a real application, you would integrate with an email service provider to send the email
        //message could be something like "Your parcel with tracking number XYZ has been delivered."
    }

    //method to register a new parcel by admin
    public Parcel registerParcel(long trackingNum, String recipientName, String recipientEmail, String recipientPhone, String deliveryAddress, String deliveryDate, String status){
        return new Parcel (trackingNum, recipientName, recipientEmail, recipientPhone, deliveryAddress, deliveryDate, status);
    }

    //method to add parcel based on the details provided by the admin
    public void addParcel(Parcel parcel, String message){
        this.message = message;
        //add parcel to the database
        parcelDatabase.add(parcel);
    }

    //method to remove parcel
    public void removeParcel(Parcel parcel) {
        parcelDatabase.remove(parcel);
    }

    //method to remove a parcel by tracking number
    public Parcel removeParcelByTracking(long trackingNum){
        Parcel foundParcel = findParcelByTracking(trackingNum);
        if(foundParcel != null){
            parcelDatabase.remove(foundParcel);
        }
        return foundParcel;
    }

    //method to get all parcels
    public List<Parcel> getAllParcels(){
        return parcelDatabase;
    }

    //method to update a parcel's status by tracking number
    public boolean updateParcelStatusByTracking(long trackingNum, String newStatus){
        Parcel parcel = findParcelByTracking(trackingNum);
        if (parcel != null){
            parcel.setStatus(newStatus);
            this.message = "Parcel " + trackingNum + " status updated to " + newStatus;
            return true;
        }
        return false;
    }

    //method to find a parcel by tracking the number
    public Parcel findParcelByTracking(long trackingNum){
        for (Parcel parcel : parcelDatabase){
            if (parcel != null && parcel.getTrackingNum() == trackingNum){
                return parcel;
            }
        }
        return null;
    }
}
