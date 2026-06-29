package com.example.parcel.model;


public class Student extends User{
    //update, max parcel perpickup are 10 logically
    public static final int MAX_PARCELS_PER_PICKUP = 10;
    //charges student rm2 each parcel
    public static final double CHARGE_PER_PARCEL = 2.0;
    private String campusAddress;
    private String matricNum;
    private int currentOTP;

    //Constructor
    public Student(String name, String id, String email, int numPhone, String campusAddress, String matricNum, long trackingNum, Parcel parcel, int currentOTP){
        super(name, id, email, numPhone);
        this.campusAddress = campusAddress;
        this.matricNum = matricNum;
        this.currentOTP = currentOTP;
    }

    //Getters
    public String getCampusAddress(){
        return campusAddress;
    }

    public String getMatricNum(){
        return matricNum;
    }

    public int getCurrentOTP(){
        return currentOTP;
    }

    // Setters
    public void setCampusAddress(String campusAddress){
        this.campusAddress = campusAddress;
    }

    public void setMatricNum(String matricNum){
        this.matricNum = matricNum;
    }

    public void setCurrentOTP(int currentOTP){
        this.currentOTP = currentOTP;
    }

    //mathod to verifies a submitted OTP against the currently active one
    public boolean verifyOTP(int otp) {
        return otp == currentOTP;
    }

    //method to generate 4 digits otp
    public int generateOTP(){
        currentOTP = (int)(Math.random() * 9000) + 1000;
        return currentOTP;
    }
}