package laba.solvd.bankHierarchy.people;

import laba.solvd.bankHierarchy.exceptions.InvalidCustomerException;
import laba.solvd.bankHierarchy.exceptions.LoanAuthorizationException;
import laba.solvd.bankHierarchy.interfaces.IEmployee;

import java.util.Objects;

public class Employee extends Person implements IEmployee {

    private int employeeId;
    private Position position;

    public Employee(String name, String address, String phoneNumber, Position position) {
        super(name, address, phoneNumber);
        this.position = position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", employeeId=" + getEmployeeId() +
                ", position=" + getPosition() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getPhoneNumber(), getEmployeeId(), getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getAddress(), employee.getAddress()) &&
                Objects.equals(getPhoneNumber(), employee.getPhoneNumber()) &&
                Objects.equals(getPosition(), employee.getPosition());
    }

    @Override
    public void authorizeLoan(Customer customer, double amount) {
        try {
            if (customer != null && customer.getAccount() != null) {
                double currentBalance = customer.getAccount().getAccountBalance();
                double loanLimit = currentBalance * 2;

                if (amount <= loanLimit) {
                    System.out.println("Loan authorized for " + customer.getName() + " amount: $" + amount);
                    currentBalance -= amount;
                    customer.getAccount().setAccountBalance(currentBalance);
                } else {
                    throw new LoanAuthorizationException("Loan amount exceeds the allowed limit.");
                }
            } else {
                throw new InvalidCustomerException("Invalid customer or account information.");
            }
        } catch (LoanAuthorizationException | InvalidCustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
