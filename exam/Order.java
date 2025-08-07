package exam;

import java.util.HashMap;

public class Order {
    private User user;
    private double subTotal;
    private double taxAmount;
    private double discountAmount;
    private double totalPayable;
    private HashMap<Product, Integer>items;

    public Order (User user) {
        this.user = user;
        this.items = user.getCart().getItems();
        this.subTotal = computeSubtotal();
        this.taxAmount = computeTax();
        this.discountAmount = computeDiscount();
        this.totalPayable = computeTotalPayable();
        

    }

    public double computeSubtotal() {
        double total = 0;

        for (Product item : user.getCart().getItems().keySet()) {
            total += item.getPrice() * user.getCart().getItems().get(item);
        }

        return total;
    }

    public double computeTax() {
        return subTotal * 0.8;
    }

    public double computeDiscount() {
        if (user.getId().getIsPWD() == true) {
            PWDDiscount pwd = new PWDDiscount();
            return pwd.applyDiscount(subTotal);
        } else if (user.getId().getIsSenior() == true) {
            SeniorDiscount senior = new SeniorDiscount();
            return senior.applyDiscount(subTotal);
        } else {
            return 0.0;
        }
    }

    public double computeTotalPayable() {
        return (subTotal - discountAmount) + taxAmount;
        }

    public User getUser() {
        return user;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return taxAmount;
    }

    public double getDiscount() {
        return discountAmount;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }
}
