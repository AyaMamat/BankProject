package laba.solvd.bankHierarchy;

public class Card {
    private String cardNumber;
    private String expirationDate;

    public Card(String cardNumber, String expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    public void checkBalance(Customer customer) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            System.out.println("Card " + cardNumber + " - Account Balance for " + customer.getName() + ": $" + accountBalance);
        } else {
            System.out.println("Card " + cardNumber + " - Unable to check balance. Invalid customer or account information.");
        }
    }

    public void makePayment(Customer customer, double amount) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                System.out.println("Card " + cardNumber + " - Payment of $" + amount + " made for " + customer.getName());
            } else {
                System.out.println("Card " + cardNumber + " - Insufficient funds for payment.");
            }
        } else {
            System.out.println("Card " + cardNumber + " - Unable to make a payment. Invalid customer or account information.");
        }
    }

}
