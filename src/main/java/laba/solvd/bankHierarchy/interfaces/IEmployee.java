package laba.solvd.bankHierarchy.interfaces;

import laba.solvd.bankHierarchy.people.Customer;

public interface IEmployee {
    void authorizeLoan(Customer customer, double amount);
}
