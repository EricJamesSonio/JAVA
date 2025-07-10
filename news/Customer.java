package news;

public class Customer {
    private String name;
    private int id;
    private double balance;

    public Customer (String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public void cashIn(double cash) {
        this.balance += cash;
    }

    public void pay(double amount) {
        this.balance -= amount;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void showBalance() {
        System.out.println("Customer : " + name + "Balance : "  + balance );
    }
}

