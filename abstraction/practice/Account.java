package abstraction.practice;

public class Account {
    private Customer customer;
    private double wallet;

    public void deposit(double amount) {
        if (amount > 0) {
            wallet += amount;
        } else {
        return;
        }
    }

    public double getWallet() {
        return wallet;
    }
}
