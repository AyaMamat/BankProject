package laba.solvd.bankHierarchy;

import laba.solvd.bankHierarchy.bankingcore.ATM;
import laba.solvd.bankHierarchy.bankingcore.Bank;
import laba.solvd.bankHierarchy.bankingcore.Branch;

import laba.solvd.bankHierarchy.exceptions.CustomerAlreadyExistsException;
import laba.solvd.bankHierarchy.exceptions.DuplicateAtmException;
import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.exceptions.InvalidCustomerException;
import laba.solvd.bankHierarchy.financial.Account;
import laba.solvd.bankHierarchy.financial.Card;
import laba.solvd.bankHierarchy.people.Customer;
import laba.solvd.bankHierarchy.people.Employee;
import laba.solvd.bankHierarchy.people.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {

        // Creating a bank
        Bank bank = new Bank("Capital One");
        LOGGER.info(bank);

        // Creating branches
        Branch branch1 = new Branch("2134 N KnollWood Ave", 2);
        Branch branch2 = new Branch("7898 W Delphia Ave", 3);

        //Creating an ATM
        ATM atm1 = new ATM(239874322);
        ATM atm2 = new ATM(987654432);

        //Adding atms to the branch
        try {
            branch1.addAtms(atm1);
            branch1.addAtms(atm2);
        } catch (DuplicateAtmException e) {
            LOGGER.info("Error adding ATM: " + e.getMessage());
        }

        // Adding branches to the bank
        for (Branch branch : Arrays.asList(branch1, branch2)) {
            bank.addBranch(branch);
        }

        // Creating an employee
        Employee employee = new Employee("Aya Mamat", "2134 N Knollwood Ave", "123-456-7890", new Position("Manager", 50000.0));
        employee.setEmployeeId(1356888);
        LOGGER.info(employee);

        // Adding the employee to the bank
        bank.addEmployee(employee);

        // Employee contact info being updated
        employee.updateContactInfo("Angelina Jolie", "2368 W Agile Street", "555-555-5555");
        employee.setEmployeeId(135698765);
        LOGGER.info(employee);

        // Creating an account and card
        Account customerAccount1 = new Account("1234567890", 1000.0);
        Card customerCard1 = new Card("1234-5678-9012-3456", "12/25");
        Account customerAccount2 = new Account("1234567890", 1000.0);
        Card customerCard2 = new Card("1234-5678-9012-3456", "12/25");

        // Creating customers using the shared account and card
        Customer customer1 = new Customer("Alice Wonder", "1234 NE Talman Ave", "987-654-3210", customerAccount1, customerCard1);
        Customer customer2 = new Customer("Bob Smith", "5785 NE Talman Ave", "987-654-3210", customerAccount2, customerCard2);
        LOGGER.info(customer1);
        LOGGER.info(customer2);

        try {
            bank.addCustomer(customer1);
        } catch (CustomerAlreadyExistsException e) {
            LOGGER.info("Error adding customer: " + e.getMessage());
        }
        Card card = new Card("2345678987654", "12/25");
        double paymentAmount = 100.0; // Set the payment amount as needed

        try {
            card.makePayment(customer1, paymentAmount);
            LOGGER.info("Payment successful.");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidCustomerException e) {
            LOGGER.info("Error: " + e.getMessage());
        }

        LOGGER.info("=====================================================");

        LOGGER.info("Welcome to " + bank.getBankName() + "!");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                LOGGER.info("Select an option:");
                LOGGER.info("1. Deposit");
                LOGGER.info("2. Withdraw");
                LOGGER.info("3. Check Balance");
                LOGGER.info("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Deposit
                        LOGGER.info("Enter the deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm1.deposit(customer1, depositAmount);
                        LOGGER.info("Deposit successful.");
                        break;

                    case 2:
                        // Withdrawal
                        LOGGER.info("Enter the withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        try {
                            atm1.withdrawCash(customer1, withdrawalAmount);
                            LOGGER.info("Withdrawal successful.");
                        } catch (InsufficientFundsException e) {
                            LOGGER.info("Error withdrawing cash: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Check Balance
                        double balance = customer1.getAccount().getAccountBalance();
                        LOGGER.info("Your account balance: $" + balance);
                        break;

                    case 4:
                        // Press Exit
                        LOGGER.info("Thank you for using " + bank.getBankName() + "!");
                        scanner.close();
                        System.exit(0);

                    default:
                        LOGGER.info("Invalid choice. Please select a valid option.");
                }

            }
        } catch (Exception e) {
            LOGGER.info("An error occurred: " + e.getMessage());
        }
    }
}
