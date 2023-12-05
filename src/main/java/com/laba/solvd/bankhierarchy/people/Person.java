package com.laba.solvd.bankhierarchy.people;

import com.laba.solvd.bankhierarchy.interfaces.IPerson;

public abstract class Person implements IPerson {

    private String name;
    private String address;
    private String phoneNumber;

    public Person(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getContactInfo(String name, String address, String phoneNumber) {
        return "Name: " + name + ", Address: " + address + ", Phone Number: " + phoneNumber;
    }

    @Override
    public void updateContactInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
