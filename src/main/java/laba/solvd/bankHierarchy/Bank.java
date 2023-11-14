package laba.solvd.bankHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    private String bankName;
    private List<Branch> branches;
    private List<Customer> customers;
    private List<Employee> employees;


    public Bank(String bankName) {
        this.bankName = bankName;
        branches = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Employee> getEmployees() {
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
        return Objects.equals(bankName, bank.bankName);
    }
}
