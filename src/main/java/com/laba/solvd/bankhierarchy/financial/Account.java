package com.laba.solvd.bankhierarchy.financial;

import com.laba.solvd.bankhierarchy.enums.AccountType;
import com.laba.solvd.bankhierarchy.enums.CardType;
import com.laba.solvd.bankhierarchy.enums.TransactionType;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    private final String accountNumber;
    private double accountBalance;
    private List<Card> cardList = new ArrayList<>();
    private AccountType accountType;

    public Account(String accountNumber, double accountBalance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountType=accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public List<Card> getCardList(){
        return new ArrayList<>(cardList);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public List<Card> filterCardByType(CardType cardType) {
        return cardList.stream()
                .filter(transaction -> transaction.getCardType().equals(cardType))
                .collect(Collectors.toList());
    }
}
