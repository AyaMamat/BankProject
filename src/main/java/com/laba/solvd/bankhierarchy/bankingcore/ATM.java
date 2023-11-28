package com.laba.solvd.bankhierarchy.bankingcore;

import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.interfaces.IManageMoney;
import com.laba.solvd.bankhierarchy.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ATM implements IManageMoney {
    private static final Logger LOGGER = LogManager.getLogger(ATM.class);
    private static final int MAX_WITHDRAWAL_AMOUNT = 500;
    private long atmCode;

    public ATM(long atmCode) {
        this.atmCode = atmCode;
    }

    public long getAtmCode() {
        return atmCode;
    }

    public void setAtmCode(long atmCode) {
        this.atmCode = atmCode;
    }

    @Override
    public void checkBalance(Customer customer) {
        if (customer.getCard() != null && customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            LOGGER.info("ATM " + atmCode + " - Account Balance for " + customer.getName() + ": $" + accountBalance);
        } else {
            LOGGER.info("ATM " + atmCode + " - Unable to check balance. Invalid customer or card information.");
        }
    }

    @Override
    public void withdrawCash(Customer customer, double amount) throws InsufficientFundsException {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (amount <= MAX_WITHDRAWAL_AMOUNT && accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                LOGGER.info(customer.getName() + " has withdrawn $" + amount + " from the atm " + atmCode);
            } else {
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
        }
    }

    @Override
    public void deposit(Customer customer, double amount) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            accountBalance += amount;
            customer.getAccount().setAccountBalance(accountBalance);
            LOGGER.info(customer.getName() + " has deposited $" + amount + " into the atm " + atmCode);
        }
    }
}
