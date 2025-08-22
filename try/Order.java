package try;

public class Order {
    private Account account;
    private double subTotal;
    private double discountAmount;
    private double totalPayable;

    public Order (Account account) {
        this.account = account;
        this.subTotal = computeSubtotal();
        this.discountAmount = computeDiscount();
        this.totalPayable = computeTotalPayable();
    }

    public double computeSubtotal() {
        double total = 0;
        for (StarbucksItem item : account.getCart()) {
            total += item.getPrice();
        }
        return total;
    }

    public double computeDiscount() {
        return subTotal * 0.10;
    }

    public double computeTotalPayable() {
        return subTotal - discountAmount;
    }
}
