package com.example.parcel.model;

public class Admin extends User{
    private String role;
    private String password;

    public Admin(String name, String id, String email, String role, String password){
        super(name, id, email, 0);
        this.role = role;
        this.password = password;
    }

    // Convenience constructor using a default userType (e.g., 0) for existing call sites
    public Admin(String name, String id, String email, String password){
        this(name, id, email, "Administrator", password);
    }

    //getters
    public String getRole(){
        return role;
    }

    public String getPassword(){
        return password;
    }

    //setters
    public void setRole(String role){
        this.role = role;
    }

    public void setPassword(String password){
        this.password = password;
    }

    //method to authenticate admin login
    public boolean login(String username, String passwordAttempt){
        return getID().equals(username) && getPassword().equals(passwordAttempt);
    }

    //method to register new parcel to the system
    public Parcel registerParcel(long trackingNum, String recipientName, String recipientEmail, String recipientPhone, String deliveryAddress, String deliveryDate, String status){
        //adding new parcel, save into database or data structure
        return new Parcel(trackingNum, recipientName, recipientEmail, recipientPhone, deliveryAddress, deliveryDate, status);
    }

    //method to update parcel status
    public void updateParcelStatus(Parcel parcel, String status){
        parcel.setStatus(status);
    }
}