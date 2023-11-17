package laba.solvd.bankHierarchy.financial;

import laba.solvd.bankHierarchy.Account;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private static final List<Transaction> transactionList = new ArrayList<>();
    private String transactionType;
    private double amount;
    private String transactionDate;

    public Transaction(String transactionType, double amount, String transactionDate) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
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

    public void recordTransaction(Account account) {
        Transaction newTransaction = new Transaction(transactionType, amount, transactionDate);
        transactionList.add(newTransaction);

    }

}
