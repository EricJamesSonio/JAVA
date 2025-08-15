package abstraction.practice;


import java.util.*;
public class Cashier {
    private String name;
    private int id;
    private List<Receipt> records;

    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
        this.records = new ArrayList<>();
    }

    public void processOrder(Customer customer, double payment) {
        Order order = new Order(customer);

        if (payment < order.getTotalPayable()) {
            return;
        }

        double change = payment - order.getTotalPayable();
        Receipt receipt = new Receipt(order, payment, change);

        records.add(receipt);
        return;


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Receipt> getRecords() {
        return records;
    }
}
