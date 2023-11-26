package com.laba.solvd.bankhierarchy.interfaces;

import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.exceptions.InvalidCustomerException;
import com.laba.solvd.bankhierarchy.people.Customer;

public interface ICard {

    void makePayment(Customer customer, double amount) throws InsufficientFundsException, InvalidCustomerException;
}
