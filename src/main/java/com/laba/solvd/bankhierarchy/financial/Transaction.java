package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.enums.TransactionType;

import java.util.Date;

public class Transaction {

    private TransactionType transactionType;
    private double amount;
    private Date transactionDate;

    public Transaction(TransactionType transactionType, double amount, Date transactionDate) {
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
