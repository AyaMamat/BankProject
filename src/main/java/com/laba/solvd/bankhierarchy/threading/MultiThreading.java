package com.laba.solvd.bankhierarchy.threading;

import com.laba.solvd.bankhierarchy.bankingcore.ATM;
import com.laba.solvd.bankhierarchy.enums.AccountType;
import com.laba.solvd.bankhierarchy.enums.CardType;
import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.financial.Account;
import com.laba.solvd.bankhierarchy.financial.Card;
import com.laba.solvd.bankhierarchy.people.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreading {
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initializeConnections(5);

        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        Customer customerAlice=new Customer("Alice Wonder", "1234 NE Talman Ave", "987-654-3210");
        Account aliceAccount = new Account("123456", 1000.0, AccountType.SAVINGS);
        Card aliceCard = new Card("1234-5678-9012-3456", "12/25", CardType.DEBIT);

        Customer customerBob=new Customer("Bob Smith", "3344 NE Beau Ave", "312-654-3711");
        Account bobAccount = new Account("14523456", 900.0, AccountType.CHECKING);
        Card bobCard = new Card("4564-3466-9462-3463", "11/26", CardType.CREDIT);

        customerAlice.addAccount(aliceAccount);
        customerAlice.addCard(aliceCard);

        customerBob.addAccount(bobAccount);
        customerBob.addCard(bobCard);

        ATM atmLeft=new ATM(239874322);
        ATM atmRight = new ATM(678907798);

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    atmRight.checkBalance(customerAlice);
                    atmRight.withdrawCash(customerAlice, 100);
                    atmRight.deposit(customerAlice, 120);
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException | InsufficientFundsException e) {
                    e.printStackTrace();
                }
            }, threadPool);
            futures.add(future);
        }

        for (int i = 0; i < 2; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    atmLeft.checkBalance(customerBob);
                    atmLeft.withdrawCash(customerBob, 50.0);
                    atmLeft.deposit(customerBob, 150.0);
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException | InsufficientFundsException e) {
                    e.printStackTrace();
                }
            }, threadPool);
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

