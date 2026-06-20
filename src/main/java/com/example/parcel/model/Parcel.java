package com.example.parcel.model;

public class Parcel{
    private String status;
    private long trackingNum;
    private String recipientName;
    private String recipientEmail;
    private String recipientPhone;
    private String deliveryAddress;
    private String deliveryDate;

    //Constructor
    public Parcel(long trackingNum, String recipientName, String recipientEmail, String recipientPhone, String deliveryAddress, String deliveryDate, String status){
        this.trackingNum = trackingNum;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.recipientPhone = recipientPhone;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    //getter
    public String getStatus(){
        return status;
    }

    public long getTrackingNum(){
        return trackingNum;
    }

    public String getRecipientName(){
        return recipientName;
    }

    public String getRecipientEmail(){
        return recipientEmail;
    }

    public String getRecipientPhone(){
        return recipientPhone;
    }

    public String getDeliveryAddress(){
        return deliveryAddress;
    }
    
    public String getDeliveryDate(){
        return deliveryDate;
    }

    //setter
    public void setStatus(String status){
        this.status = status;
    }

    public void setTrackingNum(long trackingNum){
        this.trackingNum = trackingNum;
    }

    public void setRecipientName(String recipientName){
        this.recipientName = recipientName;
    }

    public void setRecipientEmail(String recipientEmail){
        this.recipientEmail = recipientEmail;
    }

    public void setRecipientPhone(String recipientPhone){
        this.recipientPhone = recipientPhone;
    }

    public void setDeliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
    }

    //verification method to check if the tracking number mataches the parcel's tracking number
    public boolean verifyTrackingNum(long trackingNum){
        return this.trackingNum == trackingNum;
    }

    //method to get parcel details
    public String getParcelDetails(){
        return "Parcel Status: " + status + "\nTracking Number: " + trackingNum + "\nRecipient Name: " + recipientName + "\nRecipient Email: " + recipientEmail + "\nRecipient Phone: " + recipientPhone + "\nDelivery Address: " + deliveryAddress + "\nDelivery Date: " + deliveryDate;
    }
}
