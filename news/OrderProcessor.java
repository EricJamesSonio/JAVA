package news;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private List<Receipt> receipts = new ArrayList<>();

    public double computeTotal(Cart cart) {
        double total = 0;
        for (LibraryItem c : cart.getList()) {
            total += c.getPrice() * c.getQuantity();
    }   return total;
    }

    public double discountedPrice(Cart cart, DiscountStrategy discount) {
        double total = computeTotal(cart);
        double discountedPrice = discount.applyDiscount(total);
        return discountedPrice;
    }

    public Receipt processOrder(Cart cart, DiscountStrategy discount, double payment, Customer customer, Cashier cashier) {
        double total = computeTotal(cart);
        double discountedPrice = discountedPrice(cart, discount);
        if (payment < discountedPrice) {
            System.out.println("Payment not Enough! ");
        }

        double change = payment - discountedPrice;
        Receipt receipt = new Receipt(payment, total, discountedPrice, change, customer.getName(), cashier.getName());
        return receipt;
    }

    public String displayReceipts() {
        for (Receipt r : receipts) {
            System.out.println(r.getReceipt());
       }
    }

}

class Receipt {
    double payment;
    double totalPayable;
    double discountedPrice;
    double change;
    String customer;
    String cashier;

    public Receipt(double payment, double totalPayable, double discountedPrice, double change, String customer, String cashier) {
        this.payment = payment;
        this.totalPayable = totalPayable;
        this.discountedPrice = discountedPrice;
        this.change = change;
        this.customer = customer;
        this.cashier = cashier;
    }

    public String getReceipt() {
        return "Payment : " + payment + ", Total Payable : " + totalPayable + ", Discounted Price : " + discountedPrice + ", Change : " + change;
    }


}