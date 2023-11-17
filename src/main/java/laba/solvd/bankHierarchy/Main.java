package laba.solvd.bankHierarchy;

import laba.solvd.bankHierarchy.bankingcore.*;
import laba.solvd.bankHierarchy.exceptions.CustomerAlreadyExistsException;
import laba.solvd.bankHierarchy.exceptions.DuplicateAtmException;
import laba.solvd.bankHierarchy.exceptions.InsufficientFundsException;
import laba.solvd.bankHierarchy.exceptions.InvalidCustomerException;
import laba.solvd.bankHierarchy.financial.Account;
import laba.solvd.bankHierarchy.financial.Card;
import laba.solvd.bankHierarchy.people.Customer;
import laba.solvd.bankHierarchy.people.Employee;
import laba.solvd.bankHierarchy.people.Position;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        // Creating a bank
        Bank bank = new Bank("Capital One");
        logger.info(bank);

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
            logger.info("Error adding ATM: " + e.getMessage());
        }

        // Adding branches to the bank
        bank.addBranch(branch1);
        bank.addBranch(branch2);

        // Creating an employee
        Employee employee = new Employee("Aya Mamat", "2134 N Knollwood Ave", "123-456-7890", new Position("Manager", 50000.0));
        employee.setEmployeeId(1356888);
        logger.info(employee);

        // Adding the employee to the bank
        bank.addEmployee(employee);

        // Employee contact info being updated
        employee.updateContactInfo("Angelina Jolie", "2368 W Agile Street", "555-555-5555");
        employee.setEmployeeId(135698765);
        logger.info(employee);

        // Creating an account and card
        Account customerAccount1 = new Account("1234567890", 1000.0);
        Card customerCard1 = new Card("1234-5678-9012-3456", "12/25");
        Account customerAccount2 = new Account("1234567890", 1000.0);
        Card customerCard2 = new Card("1234-5678-9012-3456", "12/25");

        // Creating customers using the shared account and card
        Customer customer1 = new Customer("Alice Wonder", "1234 NE Talman Ave", "987-654-3210", customerAccount1, customerCard1);
        Customer customer2 = new Customer("Bob Smith", "5785 NE Talman Ave", "987-654-3210", customerAccount2, customerCard2);
        logger.info(customer1);
        logger.info(customer2);

        try {
            bank.addCustomer(customer1);
        } catch (CustomerAlreadyExistsException e) {
            logger.info("Error adding customer: " + e.getMessage());
        }
        Card card = new Card("2345678987654", "12/25");
        double paymentAmount = 100.0; // Set the payment amount as needed

        try {
            card.makePayment(customer1, paymentAmount);
            logger.info("Payment successful.");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidCustomerException e) {
            logger.info("Error: " + e.getMessage());
        }

        logger.info("=====================================================");

        logger.info("Welcome to " + bank.getBankName() + "!");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                logger.info("Select an option:");
                logger.info("1. Deposit");
                logger.info("2. Withdraw");
                logger.info("3. Check Balance");
                logger.info("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Deposit
                        logger.info("Enter the deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm1.deposit(customer1, depositAmount);
                        logger.info("Deposit successful.");
                        break;

                    case 2:
                        // Withdrawal
                        logger.info("Enter the withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        try {
                            atm1.withdrawCash(customer1, withdrawalAmount);
                            logger.info("Withdrawal successful.");
                        } catch (InsufficientFundsException e) {
                            logger.info("Error withdrawing cash: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Check Balance
                        double balance = customer1.getAccount().getAccountBalance();
                        logger.info("Your account balance: $" + balance);
                        break;

                    case 4:
                        // Press Exit
                        logger.info("Thank you for using " + bank.getBankName() + "!");
                        scanner.close();
                        System.exit(0);

                    default:
                        logger.info("Invalid choice. Please select a valid option.");
                }

            }
        } catch (Exception e) {
            logger.info("An error occurred: " + e.getMessage());
        }
    }
}
