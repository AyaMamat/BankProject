package laba.solvd.bankHierarchy;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Creating a bank
        Bank bank = new Bank("Capital One");
        System.out.println(bank);

        // Creating branches
        Branch branch1 = new Branch("2134 N Knollwood Ave", 2);
        Branch branch2 = new Branch("7898 W Delphia Ave", 3);

        //Creating an ATM
        ATM atm1 = new ATM(239874322);
        ATM atm2 = new ATM(987654432);

        //Adding atms to the branch
        branch1.addAtms(atm1);
        branch1.addAtms(atm2);

        // Adding branches to the bank
        bank.addBranch(branch1);
        bank.addBranch(branch2);

        // Creating an employee
        Employee employee = new Employee("Aika Mamat", "2134 N Knollwood Ave", "123-456-7890", new Position("Manager", 50000.0));
        employee.setEmployeeId(1356888);
        System.out.println(employee);

        // Adding the employee to the bank
        bank.addEmployee(employee);

        // Employee contact info being updated
        employee.updateContactInfo("Angelina Jolie", "2368 W Agile Street", "555-555-5555");
        employee.setEmployeeId(135698765);
        System.out.println(employee);

        // Creating an account and card
        Account customerAccount1 = new Account("1234567890", 1000.0);
        Card customerCard1 = new Card("1234-5678-9012-3456", "12/25");
        Account customerAccount2 = new Account("1234567890", 1000.0);
        Card customerCard2 = new Card("1234-5678-9012-3456", "12/25");

        // Create customers using the shared account and card
        Customer customer1 = new Customer("Alice Wonder", "1234 NE Talman Ave", "987-654-3210", customerAccount1, customerCard1);
        Customer customer2 = new Customer("Bob Smith", "5785 NE Talman Ave", "987-654-3210", customerAccount2, customerCard2);
        System.out.println(customer1);
        System.out.println(customer2);
        boolean areEqual = customer1.equals(customer2);

        // Making a payment using the card
        double paymentAmount = 200.0;
        customerCard1.makePayment(customer1, paymentAmount);

        // Print customer's transaction history
        List<String> transactionHistory = customer1.getTransactionHistory();
        System.out.println("Customer Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }


        System.out.println("=====================================================");

        System.out.println("Welcome to " + bank.getBankName() + "!");


        while (true) {

            System.out.println("Select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Deposit
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm1.deposit(customer1, depositAmount);
                    System.out.println("Deposit successful.");
                    break;

                case 2:
                    // Withdrawal
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm1.withdrawCash(customer1, withdrawAmount);
                    System.out.println("Withdrawal successful.");
                    break;

                case 3:
                    // Check Balance
                    double balance = customer1.getAccount().getAccountBalance();
                    System.out.println("Your account balance: $" + balance);
                    break;

                case 4:
                    // Press Exit
                    System.out.println("Thank you for using " + bank.getBankName() + "!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

        }
    }
}
