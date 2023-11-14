package laba.solvd.bankHierarchy.interfaces;

import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.exceptions.InvalidCustomerException;
import laba.solvd.bankHierarchy.people.Customer;

public interface ICard {
    void makePayment(Customer customer, double amount) throws InsufficientFundsException, InvalidCustomerException;
}
