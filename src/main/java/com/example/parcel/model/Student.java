package com.example.parcel.model;

import java.util.ArrayList;
import java.util.List;
public class Student extends User{
    //update, max parcel perpickup are 10 logically
    public static final int MAX_PARCEL_PER_PICKUP = 10;
    //charges student rm2 each parcel
    public static final double CHARGE_PER_PARCEL = 2.0;
    private String campusAddress;
    private String matricNum;
    private long trackingNum;
    private final List<Parcel> parcels;
    private int currentOTP;

    //Constructor
    public Student(String name, String id, String email, int numPhone, String campusAddress, String matricNum, long trackingNum, Parcel parcel, int currentOTP){
        super(name, id, email, numPhone);
        this.campusAddress = campusAddress;
        this.matricNum = matricNum;
        this.trackingNum = trackingNum;
        this.currentOTP = currentOTP;
        this.parcels = new ArrayList<>();
        if(parcel != null){
            this.parcels.add(parcel);
        }
    }

    //Getters
    public String getCampusAddress(){
        return campusAddress;
    }

    public String getMatricNum(){
        return matricNum;
    }

    public long getTrackingNum(){
        return trackingNum;
    }

    public int getCurrentOTP(){
        return currentOTP;
    }

    //student class gets to hold parcels
    public List<Parcel> getParcel(){
        return parcels;
    }

    // Setters
    public void setCampusAddress(String campusAddress){
        this.campusAddress = campusAddress;
    }

    public void setMatricNum(String matricNum){
        this.matricNum = matricNum;
    }

    public void setTrackingNum(long trackingNum){
        this.trackingNum = trackingNum;
    }

    public void setCurrentOTP(int currentOTP){
        this.currentOTP = currentOTP;
    }

    public void updateParcelStatus(String status){
        parcel.setStatus(status);
        //to update the status of the parcel
    }

    //method to check status of the parcel based on the supplied tracking number
    //if it matches, a fresh otp is generated
    public String getTrackingParcel(long trackingNum){
        if(getTrackingNum() == trackingNum){
            currentOTP = generateOTP();
            System.out.println("Your OTP is: " + currentOTP);
            return "Ready to pick up.";
        }else{
            return "Unidentified number";
        }
    }

    //method to generate 4 digits otp
    public int generateOTP(){
        return (int)(Math.random() * 9000) + 1000;
    }

    //method to verify otp against current active otp number
    public boolean verifyOTP(int otp){
        return otp == currentOTP;
    }

    //method to claim the package if the submitted otp match
    // update parcel status
    public boolean claimPackage(int otp){
        if(otp == currentOTP){
            updateParcelStatus("Claimed");
            return true;
        }else{
            return false;
        }
    }
}