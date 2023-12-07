package com.laba.solvd.bankhierarchy.people;

import com.laba.solvd.bankhierarchy.financial.Account;
import com.laba.solvd.bankhierarchy.financial.Card;
import com.laba.solvd.bankhierarchy.interfaces.ICustomer;
import com.laba.solvd.bankhierarchy.interfaces.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;

public class Customer extends Person implements ICustomer,Info {

    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private Account account;
    private Card card;

    public Customer(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }

    public Account getAccount() {
        return account;
    }

    public void addAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void addCard(Card card) {
        this.card = card;
    }

    @Override
    public void applyForLoan(String customerName, double loanAmount) {
        if (loanAmount <= 0) {
            LOGGER.info("Invalid loan amount.Loan amount should be greater than 0.");
        } else if (loanAmount >= 100000) {
            LOGGER.info("Loan amount exceeds the maximum limit of $100000.");
        }

        double interestRate = 0.05;
        double interest = loanAmount * interestRate;
        double totalAmount = loanAmount + interest;

        LOGGER.info("Loan application for " + customerName + ":\n");
        LOGGER.info("Loan Amount: $" + loanAmount);
        LOGGER.info("Interest Rate: " + (interestRate * 100) + "%");
        LOGGER.info("Interest Amount: $" + interest);
        LOGGER.info("Total Amount Payable: $" + totalAmount);
    }

    @Override
    public void getInfo() {
        LOGGER.info("<<<< CUSTOMER INFO >>>>");
        LOGGER.info("Name: " + getName());
        LOGGER.info("Address: " + getAddress());
        LOGGER.info("Phone Number: " + getPhoneNumber());

        if (account != null) {
            LOGGER.info("<<<< ACCOUNT INFO >>>>");
            LOGGER.info("Account Number: " + account.getAccountNumber());
            LOGGER.info("Account Balance: $" + account.getAccountBalance());
            LOGGER.info("Account Type: " + account.getAccountType());
        }

        if (card != null) {
            LOGGER.info("<<<< CARD INFO >>>>");
            LOGGER.info("Card Number: " + card.getCardNumber());
            LOGGER.info("Expiration Date: " + card.getExpirationDate());
            LOGGER.info("Card Type: " + card.getCardType());
            LOGGER.info("PIN: " + card.getPin());
        }

        LOGGER.info(" ");
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getPhoneNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) && Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }
    @Override
    public String toString() {
        return "Customer{" + "name='" + getName() + '\'' + ", address='" + getAddress() + '\'' + ", phoneNumber='" + getPhoneNumber() + '\'' + '}';
    }
}
