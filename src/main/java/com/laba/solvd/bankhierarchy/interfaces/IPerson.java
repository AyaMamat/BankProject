package com.laba.solvd.bankhierarchy.interfaces;

public interface IPerson {

    String getContactInfo(String name, String address, String phoneNumber);

    void updateContactInfo(String name, String address, String phoneNumber);
}
