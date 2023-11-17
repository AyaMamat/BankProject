package laba.solvd.bankHierarchy.people;

import laba.solvd.bankHierarchy.financial.Account;
import laba.solvd.bankHierarchy.financial.Card;
import laba.solvd.bankHierarchy.interfaces.ICustomer;

import java.util.Objects;

public class Customer extends Person implements ICustomer {

    private Account account;
    private Card card;

    public Customer(String name, String address, String phoneNumber, Account account, Card card) {
        super(name, address, phoneNumber);
        this.account = account;
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }

    @Override
    public void applyForLoan(String customerName, double loanAmount) {

        if (loanAmount <= 0) {
            System.out.println("Invalid loan amount.Loan amount should be greater than 0.");
        } else if (loanAmount >= 100000) {
            System.out.println("Loan amount exceeds the maximum limit of $100000.");
        }

        double interestRate = 0.05;
        double interest = loanAmount * interestRate;
        double totalAmount = loanAmount + interest;

        System.out.println("Loan application for " + customerName + ":\n");
        System.out.println("Loan Amount: $" + loanAmount);
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("Interest Amount: $" + interest);
        System.out.println("Total Amount Payable: $" + totalAmount);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getPhoneNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }
}
