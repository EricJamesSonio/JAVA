package store;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        // Etc.
    }
}

// ------------------------------ MenuITem ---------------------------------- //

abstract class MenuItem {
    private String name;
    private int code;
    private double price;
    private int quantity;
    private String description;

    public MenuItem (String name, int code, double price, int quantity, String description) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public int getCode () {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newquantity) {
        this.quantity = newquantity;
    }

    public String getDetails() {
        return String.format("%s : %d : %.2f : %d : %s ", name, code, price, quantity, description);
    }
}

class MainCourse extends MenuItem {
    private String size;
    public MainCourse(String name, int code, double price, int quantity, String description, String size) {
        super(name, code, price, quantity, description);
        this.size = size;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "Size : " + size;
    }

    public String getSize() {
        return size;
    }
}

class SideDishes extends MenuItem {
    private String type;

    public SideDishes (String name, int code, double price, int quantity, String description, String type) {
        super(name, code, price, quantity, description);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "Type : " + type;
    }
}

class Dessert extends MenuItem {
    private String extra;

    public Dessert (String name, int code, double price, int quantity, String extra) {
        super(name, code, price, quantity, extra);
        this.extra = extra;
    }
    public String getExtra() {
        return extra;
    }

    @Override
    public String getDetails() {
        return super.getDetails();
    }

}

// ------------------------------ Inventory ---------------------------------- //

class Inventory {
    private List<MenuItem> items = new ArrayList<>();
    InventoryViewer viewer = new InventoryViewer(this);

    public MenuItem findItem(int code) {
        for (MenuItem item : items) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public void addItem(MenuItem item) {
        MenuItem existing = findItem(item.getCode()) ;

        if (existing != null) {
            int updatedqtty = existing.getQuantity() + item.getQuantity();
            existing.setQuantity(updatedqtty);
        } else {
            items.add(item);
        }
     }

    public void removeItem(MenuItem item) {
        MenuItem existing = findItem(item.getCode());

        if (existing != null ){ 
            items.remove(existing);
        } else {
            System.out.println("Item Doesn't exist! ");
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void displayItems() {
        viewer.displayItems();
    }



}

class InventoryViewer {
    private Inventory inventory;

    public InventoryViewer(Inventory inventory) {
        this.inventory = inventory;
    }

    public void displayItems() {
        if (inventory.getItems().isEmpty()) {
            System.out.println("Inventory Doesn't have Items!");
            return;
        } 
        for (MenuItem item : inventory.getItems()) {
            System.out.println(item.getDetails());
        }
    }
}

// ------------------------------ Store ---------------------------------- //

class Store {
    private String name;
    private int id;
    private Inventory inventory;

    public Store(String name, int id) {
        this.name = name;
        this.id = id;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addITem(MenuItem item) {
        inventory.addItem(item);
    }

    public void removeItem(MenuItem item) {
        inventory.removeItem(item);;
    }

    public MenuItem findItem(int code) {
        return inventory.findItem(code);
    }

    public void displayItem() {
        inventory.viewer.displayItems();
    }
}

// ------------------------------ Customer ---------------------------------- //

class Customer {
    private String name;
    private int id;
    private double balance;
    private List<Receipt> receipts;

    public Customer (String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.receipts = new ArrayList<>();
    }

    public String getName () {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void displayOrderhistory() {
        for (Receipt r : receipts) {
            System.out.println(r.getReceipt());
        }
    }

    public void cashIn(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void pay(double totalPayable) {
        if (balance >= totalPayable) {
            balance -= totalPayable;
        }
    }
}


// ------------------------------ Order Item ---------------------------------- //

class Order {
    private List<OrderItem> orders;

    public Order() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(OrderItem item) {
        OrderItem existing = findOrder(item.getItem().getCode());

        if (existing != null) {
            int updatedqtty = existing.getItem().getQuantity() + item.getItem().getQuantity();
            existing.getItem().setQuantity(updatedqtty);
        } else {
            orders.add(item);
        }
    }

    public OrderItem findOrder(int code) {
        for (OrderItem item : orders) {
            if (item.getItem().getCode() == code) {
                return item;
            }
        }
        return null;
    }   

    public void removeItem(int code) {
        OrderItem existing = findOrder(code);

        if (existing != null ) {
            orders.remove(existing);
        } else {
            System.out.println("Order Doesn't Exist! ") ;
            return;
        }
    }

    public void changeQuantity(int code, int qtty) {
        OrderItem existing = findOrder(code);

        if (existing == null) {
            System.out.println("Order Doesn't exist! ");
            return;
        } 

        int updatedqtty = existing.getItem().getQuantity() + qtty;
        existing.getItem().setQuantity(updatedqtty);

        if (existing.getItem().getQuantity() <= 0) {
            removeItem(existing.getItem().getCode());
        }

    }

    public void displayOrders() {
        for (OrderItem item : orders) {
            System.out.println(item.getDetails());
        }
    }

    public double computeTotal() {
        double total = 0;
        for (OrderItem item : orders) {
            total += item.getItem().getPrice();
        }
        return total;
    }

    public List<OrderItem> getOrder() {
        return orders;
    }
}

class OrderItem {
    private MenuItem item;

    public OrderItem(MenuItem item) {
        this.item = item;
        // Time of Order Created!
    }

    public String getDetails() {
        return String.format("Name : %s , Price : %.2f , Quantity : %d", item.getName(), item.getPrice(), item.getQuantity());
    }

    public MenuItem getItem() {
        return item;
    }


}

// ------------------------------ Cart ---------------------------------- //

class Cart {
    private Order order;

    public Cart(Order order) {
        this.order = order;
    }

    public double computeTotal() {
        return order.computeTotal();
    }

    public Order getOrder() {
        return order;
    }
}


// ------------------------------ Order Processor ---------------------------------- //

class OrderProcessor {

    public double computeTotal(Cart cart) {
        return cart.getOrder().computeTotal();
    }

    public double applyDiscount(Cart cart, DiscountStrategy discount) {
        double total = computeTotal(cart);
        double discountedPrice = discount.applyDiscount(total);
        return discountedPrice;
    }

    public Receipt processOrder(Cart cart, double payment, DiscountStrategy discount, Customer customer, Cashier cashier) {
        double total = computeTotal(cart);
        double discountedPrice = applyDiscount(cart, discount);
        if (payment < discountedPrice) {
            System.out.println("Not enough Money! ");
            return null;
        }

        

        double change = payment - discountedPrice;
        Receipt receipt = new Receipt(payment, total, discountedPrice, change, customer.getName(), cashier.getName(), cart.getOrder().getOrder());
        return receipt;
    }

    

}

// ------------------------------ Discount Strategy ---------------------------------- //

interface DiscountStrategy {
    double applyDiscount(double total);
    String getName();
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }

    @Override
    public String getName() {
        return "PWD DISCOUNT (10%)";
    }
}

// ------------------------------ Receipt ---------------------------------- //

class Receipt {
    String customer;
    String cashier;
    List<OrderItem> orderitems;
    double total;
    double discountedPrice;
    double change;
    double payment;

    public Receipt(double payment, double total, double discountedPrice, double change, String customer, String cashier, List<OrderItem> orderitems) {
        this.payment = payment;
        this.total = total;
        this.discountedPrice = discountedPrice;
        this.change = change;
        this.customer = customer;
        this.cashier = cashier;
        this.orderitems = new ArrayList<>();
    }

    public String getReceipt() {
        return "Receipt";
    }

}

// ------------------------------ Cashier ---------------------------------- //


class Cashier {
    private String name;
    private int id;
    private OrderProcessor processor;

    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
        this.processor = new OrderProcessor();
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    public Receipt processOrder(Cart cart, double payment, DiscountStrategy discount, Customer customer) {
        return processor.processOrder(cart,payment,discount, customer, this);
    }

}
