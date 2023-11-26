package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.exceptions.InvalidCustomerException;
import com.laba.solvd.bankhierarchy.interfaces.ICard;
import com.laba.solvd.bankhierarchy.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Card implements ICard {
    private static final Logger LOGGER = LogManager.getLogger(Card.class);
    private static String cardNumber;
    private String expirationDate;
    private int pin;

    public Card(String cardNumber, String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void makePayment(Customer customer, double amount) throws InsufficientFundsException, InvalidCustomerException {
        if (customer.getCard() != null && customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                LOGGER.info("Card " + cardNumber + " - Payment of $" + amount + " made for " + customer.getName());
            } else {
                throw new InsufficientFundsException("Card: " + cardNumber + " has insufficient funds for payment.");
            }
        } else {
            throw new InvalidCustomerException("Card " + cardNumber + " - Unable to make a payment. Invalid customer or account information.");
        }
    }
}
