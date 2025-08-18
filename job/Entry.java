import java.util.*;
import java.time.LocalDateTime;

public class Entry {
    public static void main(String[] args) {

    }
}

class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private Id id;

    public User(String firstName, String middleName, String lastName, Id id) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Id getId() {
        return id;
    }

    public String toString() {
        return "Name : " + firstName + " " + middleName + " " + lastName;
    }
}

class Id {
    private boolean isPWD;
    private boolean isSenior;
    private boolean isValid;

    public Id (boolean isPWD, boolean isSenior, boolean isValid) {
        this.isPWD =isPWD;
        this.isSenior = isSenior;
        this.isValid = isValid;
    }

    public boolean getIsPWD() {
        return isPWD;
    }

    public boolean getIsSenior() {
        return isSenior;
    }

    public boolean getIsValid() {
        return isValid;
    }

}

class Account {
    private User user;
    private double wallet;
    private int id;

    public Account (User user, int id) {
        this.user = user;
        this.id = id;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }

        wallet += amount;
    }

    public double withdraw(double amount, double chargefee) {
        if (amount <= 0) {
            return 0.0;
        }

        if (amount > wallet) {
            return 0.0;
        }

        double newAmount = amount * chargefee;
        wallet -= newAmount;
        return amount;
    }


    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public double getWallet() {
        return wallet;
    }

    public String toString() {
        return "Account ID: " + id + ", Owner: " + user + ", Balance: " + wallet;
}


}

class Bank {
    private List<Account> accounts;
    private double totalBankMoney;
    private double totalInterest;
    private static int accountId;
    private List<Transaction> transactions;
    private double bankCapital;
    private double rate;
    private double loanrate;
    private double chargefee;

    public Bank () {
        this.accounts = new ArrayList<>();
        this.totalBankMoney = 0;
        this.totalInterest = 0;
        this.transactions = new ArrayList<>();
        this.rate = 0.2;
        this.loanrate = 0.5;
        this.bankCapital = 0;
        this.chargefee = 0.3;
    }

    public void registerUser(User user) {
        if (user.getId().getIsValid() == false) {
            return;
        }

        int id = accountId++;

        Account account = new Account(user, id);
        accounts.add(account);
        System.out.println("Succesfully Added new Account with Id : " + id);

    } 

    public void depositAccount(Account account, double amount) {
        Account existing = findAccount(account.getId());

        if (existing == null ) {
            return;
        }

        if (amount <= 0) {
            return;
        }

        existing.deposit(amount);
        totalBankMoney = computeTotalBankMoney();
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(account, amount, "Deposit", date);
        transactions.add(transaction);
    }

    public void withdrawAccount(Account account, double amount) {
        Account existing = findAccount(account.getId());


        if (existing == null ) {
            return;
        }

        if (amount <= 0) {
            return;
        }
        double withChargeFee = amount * chargefee;
        existing.withdraw(amount, withChargeFee);
        totalInterest += withChargeFee;
        totalBankMoney = computeTotalBankMoney() + chargefee;
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(account, amount, "WithDraw", date);
        transactions.add(transaction);
    }

    public void applyInterestBasedOnDeposit() {

        for (Transaction t : transactions) {
            if (t.getType().equals("Deposit") && t.getAmount() >= 1000) {
                double interest = t.getAmount() * rate;
                if (bankCapital < interest) {
                    continue;
                }
                LocalDateTime date = LocalDateTime.now();
                Transaction transaction = new Transaction(t.getAccount(), interest, "Interest", date);
                t.getAccount().deposit(interest);
                bankCapital -= interest;
                totalInterest += interest;
                transactions.add(transaction);
            } 
        } 
    }

    public void applyInterestBasedOnWallet() {
        for (Account acc : accounts) {
            if (acc.getWallet() >= 1000) {
                double interest = acc.getWallet() * rate;
                if (bankCapital < interest) {
                    continue;
                }
                acc.deposit(interest);
                bankCapital -= interest;
                totalInterest += interest;
                LocalDateTime date = LocalDateTime.now();
                Transaction transaction = new Transaction(acc, interest, "Transaction", date);
                transactions.add(transaction);
            }
        }
    }

    public void lendMoney(Account account, double amount ) {
        Account existing = findAccount(account.getId());

        if (existing == null && totalBankMoney < amount) {
            return;
            }

        double interest = amount * loanrate;
        account.deposit(amount);
        totalBankMoney = computeTotalBankMoney() + interest;
    }

    public double computeTotalBankMoney() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getWallet();
        }
        return total;
        
    } 

    public Account findAccount(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                return acc;
            }
        }
        return null;
    }

    public void displayTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void displayAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }



    public List<Account> getAccounts () {
        return accounts;
    }

    public double getTotalBankMoney() {
        return totalBankMoney;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public double getBankCapital() {
        return bankCapital;
    }

    public double getRate() {
        return rate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

class Transaction {
    private String type;
    private double amount;
    private Account account;
    private LocalDateTime date;

    public Transaction (Account account, double amount, String type, LocalDateTime date) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String toString() {
        return "Account Id : " + account.getId() +
                "Amount : " + amount + 
                "Type : " + type +
                "Date : " + date; 
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}
