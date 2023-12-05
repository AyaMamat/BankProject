package com.laba.solvd.bankhierarchy.interfaces;

import com.laba.solvd.bankhierarchy.people.Customer;

@FunctionalInterface
public interface IEmployee {

    void authorizeLoan(Customer customer, double amount);
}
