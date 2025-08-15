package exam;

public class OrderProcessor {
    public Receipt processOrder(User user, double payment, Cashier cashier) {
        Order order = new Order(user);

        if (order.getTotalPayable() > payment) {
            return null;
        }

        double change = payment - order.getTotalPayable();

        Receipt receipt = new Receipt(order, payment, change, cashier);
        cashier.getTransactionHistory().add(receipt);
        user.getOrderHistory().add(receipt);
        return receipt;




    }
}
