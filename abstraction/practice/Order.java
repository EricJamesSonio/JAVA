package abstraction.practice;

import java.util.HashMap;

public class Order {
    private Customer customer;
    private HashMap<Product, Integer> items;
    private double subTotal;
    private double taxAmount;
    private double discountAmount;
    private double totalPayable;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = customer.getItemsInCart();
        this.subTotal = computeSubtotal();
        this.taxAmount = computeTaxAmount();
        this.discountAmount = computeDiscount();
        this.totalPayable = computeTotalPayable();
    }

    public double computeSubtotal() {
        double total = 0;
        for (Product item :   items.keySet()) {
            total += item.getPrice();
        }

        return total;
    
    } 

    public double computeTaxAmount() {
        return subTotal * 0.10;
    }

    public double computeDiscount() {
        return subTotal * 0.12;
    }

    public double computeTotalPayable() {
        return (subTotal - discountAmount) + taxAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscount() {
        return discountAmount;
    }

    public double getTax() {
        return taxAmount;
    }

    public double getTotalPayable() {
        return totalPayable;
        }

    public HashMap<Product, Integer> getItems() {
        return items;
    }
}
