package laba.solvd.bankHierarchy.bankingcore;

import laba.solvd.bankHierarchy.customLinkedList.CustomLinkedList;
import laba.solvd.bankHierarchy.exceptions.CustomerAlreadyExistsException;
import laba.solvd.bankHierarchy.people.Customer;
import laba.solvd.bankHierarchy.people.Employee;

import java.util.*;

public class Bank {

    private static final String bankName = "Chase";
    private static final List<String> currencyList;
    private final List<Branch> branches;
    private final Set<Customer> customers;
    private final CustomLinkedList<Employee> employees;

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

    public static List<String> getCurrencyList() {
        return currencyList;
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
        return Objects.equals(bankName, bankName);
    }
}
