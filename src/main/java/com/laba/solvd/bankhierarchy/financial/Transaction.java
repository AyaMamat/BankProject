package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.enums.TransactionType;

public class Transaction {

    private TransactionType transactionType;
    private double amount;
    private String transactionDate;

    public Transaction(TransactionType transactionType, double amount, String transactionDate) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;

    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
