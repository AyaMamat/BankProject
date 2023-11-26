package com.laba.solvd.bankhierarchy.interfaces;

import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.people.Customer;

public interface IManageMoney {

    void checkBalance(Customer customer);

    void deposit(Customer customer, double amount);

    void withdrawCash(Customer customer, double amount) throws InsufficientFundsException;
}
