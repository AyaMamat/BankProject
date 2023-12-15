package com.laba.solvd.bankhierarchy;

import com.laba.solvd.bankhierarchy.bankingcore.ATM;
import com.laba.solvd.bankhierarchy.bankingcore.Bank;
import com.laba.solvd.bankhierarchy.bankingcore.Branch;
import com.laba.solvd.bankhierarchy.enums.AccountType;
import com.laba.solvd.bankhierarchy.enums.CardType;
import com.laba.solvd.bankhierarchy.enums.JobTitle;
import com.laba.solvd.bankhierarchy.enums.TransactionType;
import com.laba.solvd.bankhierarchy.exceptions.CustomerAlreadyExistsException;
import com.laba.solvd.bankhierarchy.exceptions.DuplicateAtmException;
import com.laba.solvd.bankhierarchy.exceptions.InsufficientFundsException;
import com.laba.solvd.bankhierarchy.exceptions.InvalidCustomerException;
import com.laba.solvd.bankhierarchy.financial.Account;
import com.laba.solvd.bankhierarchy.financial.Card;
import com.laba.solvd.bankhierarchy.financial.Transaction;
import com.laba.solvd.bankhierarchy.people.Customer;
import com.laba.solvd.bankhierarchy.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Bank bank = new Bank("Chase");
        bank.printInfo();

        Branch branchNKnollWoodDr = new Branch("2134 N KnollWood Ave", 2);
        Branch branchWDelphiaAve = new Branch("7898 W Delphia Ave", 3);

        ATM leftATMNKnollWoodDr = new ATM(239874322);
        ATM rightATMNKnollWoodDr = new ATM(987654432);

        try {
            branchNKnollWoodDr.addAtms(leftATMNKnollWoodDr);
            branchWDelphiaAve.addAtms(rightATMNKnollWoodDr);
        } catch (DuplicateAtmException e) {
            LOGGER.info("Error adding ATM: " + e.getMessage());
        }

        for (Branch branch : Arrays.asList(branchNKnollWoodDr, branchWDelphiaAve)) {
            bank.addBranch(branch);
        }

        Employee employee = new Employee("Aya Mamat", "2134 N KnollWood Ave", "123-456-7890", JobTitle.MANAGER);
        employee.setEmployeeId(1356888);
        bank.addEmployee(employee);
        employee.printInfo();

        employee.updateContactInfo("Angelina Jolie", "2368 W Agile Street", "555-555-5555");
        employee.setEmployeeId(135698765);
        employee.printInfo();

        Customer customerAlice = new Customer("Alice Wonder", "1234 NE Talman Ave", "987-654-3210");
        Customer customerBob = new Customer("Bob Smith", "5785 NE Talman Ave", "987-654-3210");
        customerBob.run();
        customerAlice.printInfo();
        customerBob.printInfo();


        Account bobAccount = new Account("1234567890", 1000.0, AccountType.CHECKING);
        Account aliceAccount = new Account("1234567890", 1000.0, AccountType.SAVINGS);
        bobAccount.start();

        Card bobCard = new Card("1234-5678-9012-3456", "12/25", CardType.DEBIT);
        Card aliceCard = new Card("1234-5678-9012-3456", "12/25", CardType.CREDIT);

        List<Customer> customers = new ArrayList<>();
        customers.add(customerAlice);
        customers.add(customerBob);

        customerAlice.addAccount(aliceAccount);
        customerBob.addAccount(bobAccount);

        List<Account> accounts = new ArrayList<>();
        accounts.add(bobAccount);
        accounts.add(aliceAccount);

        bobAccount.addCard(bobCard);
        aliceAccount.addCard(aliceCard);

        List<Card> cardList = new ArrayList<>();
        cardList.add(bobCard);
        cardList.add(aliceCard);

        try {
            for (Customer customer : customers) {
                bank.addCustomer(customer);
            }

        } catch (CustomerAlreadyExistsException e) {
            e.printStackTrace();
        }

        Set<Customer> filteredCustomers = bank.filterCustomer(AccountType.SAVINGS);
        LOGGER.info("Filtered customers");
        filteredCustomers.forEach(Customer::printInfo);

        Card card = new Card("2345678987654", "12/25", CardType.DEBIT);
        double paymentAmount = 100.0;

        try {
            card.makePayment(customerAlice, paymentAmount);
            LOGGER.info("Payment successful.");
        } catch (InsufficientFundsException e) {
            LOGGER.error("Error: " + e.getMessage());
        } catch (InvalidCustomerException e) {
            LOGGER.error("Error: " + e.getMessage());
        }

        Transaction depositTransaction = new Transaction(TransactionType.DEPOSIT, 1000.0, LocalDate.parse("2023-12-05"));
        LOGGER.info("Transaction type: " + depositTransaction.getTransactionType() + " Amount: " + depositTransaction.getAmount() + " Transaction date: " + depositTransaction.getTransactionDate());

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
                        LOGGER.info("Enter the deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        rightATMNKnollWoodDr.deposit(customerBob, depositAmount);
                        LOGGER.info("Deposit successful.");
                        break;

                    case 2:
                        LOGGER.info("Enter the withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        try {
                            leftATMNKnollWoodDr.withdrawCash(customerBob, withdrawalAmount);
                            LOGGER.info("Withdrawal successful.");
                        } catch (InsufficientFundsException e) {
                            LOGGER.info("Error withdrawing cash: " + e.getMessage());
                        }
                        break;

                    case 3:
                        double balance = customerBob.getAccount().getAccountBalance();
                        LOGGER.info("Your account balance: $" + balance);
                        break;

                    case 4:
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