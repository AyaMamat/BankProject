package com.laba.solvd.bankhierarchy.people;

public class Position {
    private String title;
    private double salary;

    public Position(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String positionName) {
        this.title = positionName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "'" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}
