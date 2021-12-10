package com.example.myfinalproject;

public class AdoptionPet {
    String name;
    int age;
    String description;
    String[] feature;
    String mobile;
    String email;
    String address;
    byte[] image;

    public AdoptionPet(String name, int age, String description, String[] features, String mobile, String email, String address, byte[] image) {
        this.name = name;
        this.age = age;
        this.feature = features;
        this.description = description;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String[] getFeatures() {
        return feature;
    }

    public String getMobile() {
        return mobile;
    }
}
