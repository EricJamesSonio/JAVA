package news;


public class Cashier {
    private String name;
    private int id;
    private OrderProcessor processor;
    
    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
        this.processor = new OrderProcessor();
    }

    public Receipt processOrder(Cart cart, DiscountStrategy discount, double payment, Customer customer, Cashier cashier) {
        return processor.processOrder(cart, discount, payment, customer, this);
    }

    public void displayReceipts() {
        processor.displayReceipts();
    }

    public String getName () {
        return name;
    }

    public int getId() {
        return id;
    }

}