package abstraction.practice;

public class Receipt {
    private Order order;
    private double payment;
    private double change;

    public Receipt(Order order, double payment, double change) {
        this.order = order;
        this.payment = payment;
        this.change = change;
    }

    public void displayReceipt() {
        for (Product item : order.getItems().keySet()) {
            System.out.println("Name : " + item.getName() + " Price : " + item.getPrice() + "Quantity : " + order.getItems().get(item) );
        }

        System.out.println("SubTotal : " + order.getSubTotal());
        System.out.println("Tax : " + order.getTax()) ;
        System.out.println("Discount : " + order.getDiscount()) ;  
        System.out.println("Total Payable : " + order.getTotalPayable());
        System.out.println("Paymen : " + payment);
        System.out.println("Change : " + change);
        System.out.println("thanks for Buying! " );
 }

    public Order getOrder() {
        return order;
    }

    public double getTotalPayable() {
        return order.getTotalPayable();
    } 
}
