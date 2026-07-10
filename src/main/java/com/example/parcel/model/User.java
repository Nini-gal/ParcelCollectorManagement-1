package com.example.parcel.model;
public class User{
    private String name;
    private String id;
    private String email;
    private int numPhone;

    //Constructor
    public User(String name, String id, String email, int numPhone){
        this.name = name;
        this.id = id;
        this.email = email;
        this.numPhone = numPhone;
    }

    // Getters
    public String getName(){
        return name;
    }

    public String getID(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public int getNumPhone(){
        return numPhone;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setID(String id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNumPhone(int numPhone){
        this.numPhone = numPhone;
    }

    //method login is to authenticate user
    public boolean login(String username, String password) {
        //just check if username matches the user's ID
        return this.getID().equals(username);
    }

    //method to get full profile details similar toString
    public String getProfileDetails(){
        return "\nName: " + name + 
            "\nId: " + id + 
            "\nEmail: " + email + 
            "\nNumber Phone: " + numPhone;
    }
}