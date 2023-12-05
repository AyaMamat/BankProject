package com.laba.solvd.bankhierarchy.bankingcore;

import com.laba.solvd.bankhierarchy.customlinkedlist.GenericLinkedList;
import com.laba.solvd.bankhierarchy.enums.AccountType;
import com.laba.solvd.bankhierarchy.exceptions.CustomerAlreadyExistsException;
import com.laba.solvd.bankhierarchy.people.Customer;
import com.laba.solvd.bankhierarchy.people.Employee;
import com.laba.solvd.bankhierarchy.enums.Currency;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private final String bankName;
    private static List<Currency> currencyList;
    private List<Branch> branches;
    private Set<Customer> customers;
    private GenericLinkedList<Employee> employees;

    static {
        currencyList = new ArrayList<>();
        currencyList.add(Currency.USD);
        currencyList.add(Currency.EUR);
        currencyList.add(Currency.GBP);
    }

    public Bank(String bankName) {
        this.bankName=bankName;
        branches = new ArrayList<>();
        customers = new HashSet<>();
        employees = new GenericLinkedList<>();
    }

    public static List<Currency> getCurrencyList() {
        return currencyList;
    }

    public String getBankName() {
        return bankName;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public List<Branch> filterBranchesByAddress(String city){
        return branches.stream()
                .filter(branch ->branch.getAddress().contains(city))
                .peek(branch -> System.out.println(branch))
                .collect(Collectors.toList());

    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        if (customers.contains(customer)) {
            throw new CustomerAlreadyExistsException("Customer " + customer.getName() + " already exists in " + bankName);
        }
        customers.add(customer);
    }

    public GenericLinkedList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Set<Customer> filterCustomer(AccountType accountType){
        return customers.stream()
                .filter(customer -> customer.getAccount().equals(accountType))
                .peek(customer -> System.out.println(customer))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Bank{" + "bankName='" + bankName + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(bank.bankName, bankName);
    }
}
