package com.example.myfinalproject;

public class Animals {
    private String name;
    private int image;
    private String age;
    private String[] features;
    private String description;
    private String email;
    private String mobileNumber;
    private String address;



    public Animals(String name, int image, String age, String[] features, String email, String mobileNumber, String address, String description) {
        this.name = name;
        this.image = image;
        this.age = age;
        this.features = features;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.description = description;
    }

    public String getAge() {
        return this.age;
    }
    public String[] getFeatures() {
        return this.features;
    }
    public String getDescription() {
        return this.description;
    }
    public String getEmail() {
        return this.email;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public int getImage() {
        return this.image;
    }
}
