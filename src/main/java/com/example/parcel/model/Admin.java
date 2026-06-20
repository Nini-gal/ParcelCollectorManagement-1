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
    public boolean login(String username, String passwor){
        if (getEmail().equals(username) && getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    //method to register new admin and return a created object
    public static Admin registerAdmin(String name, String id, String email, String role, String password){
        //adding new admin, save into database or data structure
        Admin newAdmin = new Admin(name, id, email, role, password);
        return newAdmin;
    }

    //method to register new parcel to the system
    public Parcel registerParcel(int trackingNum, String recipientName, String recipientEmail, String recipientPhone, String deliveryAddress, String deliveryDate, String status){
        //adding new parcel, save into database or data structure
        Parcel newParcel = new Parcel(trackingNum, recipientName, recipientEmail, recipientPhone, deliveryAddress, deliveryDate, status);
        return newParcel;
    }

    //method to update parcel status
    public void updateParcelStatus(Parcel parcel, String status){
        parcel.setStatus("Arrived");
    }
}