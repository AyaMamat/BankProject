package laba.solvd.bankHierarchy.bankingcore;

import laba.solvd.bankHierarchy.customLinkedList.CustomLinkedList;
import laba.solvd.bankHierarchy.exceptions.CustomerAlreadyExistsException;
import laba.solvd.bankHierarchy.people.Customer;
import laba.solvd.bankHierarchy.people.Employee;

import java.util.*;

public class Bank {
    private static final String bankName = "Chase";
    private List<Branch> branches;
    private Set<Customer> customers;
    private CustomLinkedList<Employee> employees;
    private static List<String> currencyList;

    static {
        currencyList = new ArrayList<>();
        currencyList.add("USD");
        currencyList.add("EUR");
        currencyList.add("JPY");
    }

    public Bank(String bankName) {
        branches = new ArrayList<>();
        customers = new HashSet<>();
        employees = new CustomLinkedList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public List<Branch> getBranches() {
        return branches;
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

    public CustomLinkedList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static List<String> getCurrencyList() {
        return currencyList;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                '}';
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
        return Objects.equals(bankName, bank.bankName);
    }
}
