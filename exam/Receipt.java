package exam;

public class Receipt {
    private Order order;
    private double payment;
    private double change;
    private Viewer viewer;
    private Cashier cashier;

    public Receipt (Order order, double payment , double change, Cashier cashier) {
        this.order = order;
        this.payment = payment;
        this.viewer = new Viewer(order.getItems());
        this.cashier = cashier;
        this.change = change;
    }

    public void displayReceipt() {
        viewer.displayItems();

        System.out.println("Subtotal : " + order.getSubTotal());
        System.out.println("Discount Amount : " + order.getDiscount());
        System.out.println("Tax Amount : " + order.getTax());
        System.out.println("Total Payable : " + order.getTotalPayable());
        System.out.println("Payment : " + payment);
        System.out.println("Change : " + change);
        System.out.println("Processed by : " + cashier.getName() + " Id : " + cashier.getId());

    }
}
