package laba.solvd.bankHierarchy.financial;

import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.exceptions.InvalidCustomerException;
import laba.solvd.bankHierarchy.interfaces.ICard;
import laba.solvd.bankHierarchy.people.Customer;

public class Card implements ICard {

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
        if (customer.getAccount() != null && customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                System.out.println("Card " + cardNumber + " - Payment of $" + amount + " made for " + customer.getName());
            } else {
                throw new InsufficientFundsException("Card: " + cardNumber + " has insufficient funds for payment.");
            }
        } else {
            throw new InvalidCustomerException("Card " + cardNumber + " - Unable to make a payment. Invalid customer or account information.");
        }
    }
}
