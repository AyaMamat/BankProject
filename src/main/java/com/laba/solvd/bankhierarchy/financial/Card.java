package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.enums.CardType;
import com.laba.solvd.bankhierarchy.enums.TransactionType;
import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.exceptions.InvalidCustomerException;
import com.laba.solvd.bankhierarchy.interfaces.ICard;
import com.laba.solvd.bankhierarchy.interfaces.Info;
import com.laba.solvd.bankhierarchy.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Card implements ICard, Info {

    private static final Logger LOGGER = LogManager.getLogger(Card.class);
    private List<Transaction> transactionList = new ArrayList<>();
    private static String cardNumber;
    private String expirationDate;
    private int pin;
    private CardType cardType;

    public Card(String cardNumber, String expirationDate, CardType cardType) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public static void setCardNumber(String cardNumber) {
        Card.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
        } else {
            LOGGER.info("Invalid PIN.Please enter 4 digit number");
        }
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
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

    public void recordTransaction(TransactionType transactionType, double amount, LocalDate transactionDate) {
        Transaction newTransaction = new Transaction(transactionType, amount, transactionDate);
        transactionList.add(newTransaction);
    }

    public List<Transaction> filterTransactionsByType(TransactionType transactionType) {
        return transactionList.stream()
                .filter(transaction -> transaction.getTransactionType() == transactionType)
                .sorted(Comparator.comparing(Transaction::getTransactionDate))
                .collect(Collectors.toList());
    }

    @Override
    public void getInfo() {
        LOGGER.info("<<<< CARD INFO >>>>");
        LOGGER.info("Card Number: " + cardNumber);
        LOGGER.info("Expiration Date: " + expirationDate);
        LOGGER.info("Card Type: " + cardType);

        LOGGER.info("<<<< TRANSACTIONS >>>>");
        if (transactionList.isEmpty()) {
            LOGGER.info("No transactions recorded for this card.");
        } else {
            transactionList.forEach(transaction -> LOGGER.info("\t >> " + transaction));
        }
        LOGGER.info(" ");
    }
}
