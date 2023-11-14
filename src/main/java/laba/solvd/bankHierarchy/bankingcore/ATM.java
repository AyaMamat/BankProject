package laba.solvd.bankHierarchy.bankingcore;

import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.interfaces.IManageMoney;
import laba.solvd.bankHierarchy.people.Customer;

public class ATM implements IManageMoney {
    private static final int maxWithdrawalAmount = 500;
    private long atmCode;

    public ATM(long atmCode) {
        this.atmCode = atmCode;
    }

    public long getAtmCode() {
        return atmCode;
    }

    public void setAtmCode(long atmCode) {
        this.atmCode = atmCode;
    }

    @Override
    public void checkBalance(Customer customer) {
        if (customer.getCard() != null && customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            System.out.println("ATM " + atmCode + " - Account Balance for " + customer.getName() + ": $" + accountBalance);
        } else {
            System.out.println("ATM " + atmCode + " - Unable to check balance. Invalid customer or card information.");
        }
    }

    @Override
    public void withdrawCash(Customer customer, double amount) throws InsufficientFundsException {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                System.out.println(customer.getName() + " has withdrawn $" + amount + " from the atm " + atmCode);
            } else {
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
        }
    }

    @Override
    public void deposit(Customer customer, double amount) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            accountBalance += amount;
            customer.getAccount().setAccountBalance(accountBalance);
            System.out.println(customer.getName() + " has deposited $" + amount + " into the atm " + atmCode);
        }
    }
}
