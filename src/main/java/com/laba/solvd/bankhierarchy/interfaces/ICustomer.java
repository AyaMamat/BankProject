package com.laba.solvd.bankhierarchy.interfaces;

@FunctionalInterface
public interface ICustomer {

    void applyForLoan(String customerName, double loanAmount);
}
