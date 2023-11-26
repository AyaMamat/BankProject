package com.laba.solvd.bankhierarchy.people;

import com.laba.solvd.bankhierarchy.financial.Account;
import com.laba.solvd.bankhierarchy.financial.Card;
import com.laba.solvd.bankhierarchy.interfaces.ICustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Customer extends Person implements ICustomer {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private Account account;
    private Card card;

    public Customer(String name, String address, String phoneNumber, Account account, Card card) {
        super(name, address, phoneNumber);
        this.account = account;
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }

    @Override
    public void applyForLoan(String customerName, double loanAmount) {
        if (loanAmount <= 0) {
            LOGGER.info("Invalid loan amount.Loan amount should be greater than 0.");
        } else if (loanAmount >= 100000) {
            System.out.println("Loan amount exceeds the maximum limit of $100000.");
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
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getPhoneNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }
}
