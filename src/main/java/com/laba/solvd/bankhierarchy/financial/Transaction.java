package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.enums.TransactionType;

import java.time.LocalDate;

public class Transaction {

    private TransactionType transactionType;
    private double amount;
    private LocalDate transactionDate;

    public Transaction(TransactionType transactionType, double amount, LocalDate transactionDate) {
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
