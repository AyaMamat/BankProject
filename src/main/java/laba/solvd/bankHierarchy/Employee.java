package laba.solvd.bankHierarchy;

import java.util.Objects;

public class Employee extends Person{

    private int employeeId;
    private Position position;

    public Employee(String name, String address, String phoneNumber,Position position) {
        super(name, address, phoneNumber);
        this.position=position;
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

}
