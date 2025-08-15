package abstraction.practice;

import java.util.*;

public class Customer {
    private Cart cart;
    private double wallet;

    public void cashIn(double amount) {
        if (amount > 0 ) {
            wallet += amount;
        }
    }

    public void withDraw(double amount) {
        if (amount > wallet) {
            return;
        } else {
            wallet -= amount;
            System.out.println("Amount Withdraw : " + amount);
        }
    }

    public double getWallet() {
        return wallet;
    }

    public void pay(double amount) {
        if (amount > wallet) {
            return;
        } else {
            wallet -= amount;
            System.out.println("Amount paid : " + amount);
        }
    }

    public void addToCart(Product item, int quantity) {
        cart.addToCart(item, quantity);
    }

    public HashMap<Product, Integer> getItemsInCart() {
        return cart.getItems();
    }
}