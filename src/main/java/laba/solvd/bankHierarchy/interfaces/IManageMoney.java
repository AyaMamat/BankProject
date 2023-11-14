package laba.solvd.bankHierarchy.interfaces;

import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.people.Customer;

public interface IManageMoney {
    void checkBalance(Customer customer);

    void deposit(Customer customer, double amount);

    void withdrawCash(Customer customer, double amount) throws InsufficientFundsException;
}
