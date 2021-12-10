package com.example.myfinalproject;

public class MissingPet {
    String name;
    int age;
    String description;
    String lastSeen;
    String mobile;
    String email;
    String address;
    byte[] image;

    public MissingPet(String name, int age, String description, String lastSeen, String mobile, String email, String address, byte[] image) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.lastSeen = lastSeen;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public String getDescription() {return this.description;}
    public String getLastSeen() {return this.lastSeen;}
    public String getMobile(){return this.mobile;}
    public String getEmail() {return this.email;}
    public String getAddress() {return this.address;}
    public byte[] getImage() {return this.image;}

}
