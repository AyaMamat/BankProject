package com.laba.solvd.bankhierarchy.enums;

public enum JobTitle {

    MANAGER("MANAGER", 150000),
    TELLER("TELLER", 75000),
    LOAN_OFFICER("LOAN OFFICER", 85000),
    TECHNOLOGY_ANALYST("TECHNOLOGY ANALYST", 110000),
    CUSTOMER_SERVICE_REPRESENTATIVE("CUSTOMER SERVICE REPRESENTATIVE", 65000);

    private final String title;
    private final double salary;

    JobTitle(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public double getSalary() {
        return salary;
    }
}
