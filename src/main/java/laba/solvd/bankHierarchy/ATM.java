package laba.solvd.bankHierarchy;

public class ATM {
    private long atmCode;

    public ATM(long atmCode) {
        this.atmCode = atmCode;
    }

    public long getAtmCode() {
        return atmCode;
    }

    public void setAtmCode(long atmCode) {
        this.atmCode = atmCode;
    }
    public void checkBalance(Customer customer) {
        if (customer.getCard() != null && customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            System.out.println("ATM " + atmCode + " - Account Balance for " + customer.getName() + ": $" + accountBalance);
        } else {
            System.out.println("ATM " + atmCode + " - Unable to check balance. Invalid customer or card information.");
        }
    }
    public void withdrawCash(Customer customer, double amount) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                customer.getAccount().setAccountBalance(accountBalance);
                System.out.println(customer.getName() + " has withdrawn $" + amount + " from the atm " + atmCode);
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }
    public void deposit(Customer customer, double amount) {
        if (customer.getAccount() != null) {
            double accountBalance = customer.getAccount().getAccountBalance();
            accountBalance += amount;
            customer.getAccount().setAccountBalance(accountBalance);
            System.out.println(customer.getName() + " has deposited $" + amount + " into the atm " + atmCode);
        }
    }
}
