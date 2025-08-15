package pratice;

import java.util.*;

public class Bank {
    private List<Account> accounts;
    private double totalBankMoney;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.totalBankMoney = totalBankMoney;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void displayAccounts() {
        for (Account account : accounts) {
            System.out.println(account.getDetails());
        }
    }

    public double getTotalBankMoney() {
        return totalBankMoney;
    }

    public void setTotalBankMoney(double totalBankMoney) {
        this.totalBankMoney = totalBankMoney;
    }
}
