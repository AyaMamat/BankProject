package com.laba.solvd.bankhierarchy.people;

import com.laba.solvd.bankhierarchy.bankingcore.ATM;
import com.laba.solvd.bankhierarchy.enums.CardType;
import com.laba.solvd.bankhierarchy.enums.JobTitle;
import com.laba.solvd.bankhierarchy.exceptions.InvalidCustomerException;
import com.laba.solvd.bankhierarchy.exceptions.LoanAuthorizationException;
import com.laba.solvd.bankhierarchy.interfaces.IEmployee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Employee extends Person implements IEmployee {

    private static final Logger LOGGER = LogManager.getLogger(Employee.class);
    private int employeeId;
    private JobTitle jobTitle;

    public Employee(String name, String address, String phoneNumber, JobTitle jobTitle) {
        super(name, address, phoneNumber);
        this.jobTitle = jobTitle;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void authorizeLoan(Customer customer, double amount) {
        try {
            if (customer != null && customer.getAccount() != null) {
                double currentBalance = customer.getAccount().getAccountBalance();
                double loanLimit = currentBalance * 2;

                if (amount <= loanLimit) {
                    LOGGER.info("Loan authorized for " + customer.getName() + " amount: $" + amount);
                    currentBalance -= amount;
                    customer.getAccount().setAccountBalance(currentBalance);
                } else {
                    throw new LoanAuthorizationException("Loan amount exceeds the allowed limit.");
                }
            } else {
                throw new InvalidCustomerException("Invalid customer or account information.");
            }
        } catch (LoanAuthorizationException | InvalidCustomerException e) {
            LOGGER.info("Error: " + e.getMessage());
        }
    }
}
