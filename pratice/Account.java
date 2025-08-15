package pratice;

public class Account {
    private double balance;
    private double wallet;

    public Account(double balance, double wallet) {
        this.balance = balance;
        this.wallet = wallet;
    }

    public double getBalance() {
        return balance;
    }

    public double getWallet() {
        return wallet;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getDetails() {
        return "Account Details:\n" +
               "Balance: $" + balance + "\n" +
               "Wallet: $" + wallet + "\n" +
               "Total: $" + (balance + wallet);
    }
}
