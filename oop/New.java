package oop;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class New
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		Product p1 = new Product("Milo", 12, 20.20);
		Inventory inv = new Inventory();
		inv.addItem(p1, 20);
		inv.displayItems();
		Customer eric = new Customer();
		eric.addItem(p1, 12,inv);
		Cashier rj = new Cashier("Rj", 12);
		PWDDiscount pwd = new PWDDiscount();
		Order order1 = new Order(eric.getCart());
		rj.processOrder(eric, 1200, pwd,order1);
		rj.displayItems();
	}
}


class Product {
    private String name;
    private int code;
    private double price;
    
    public Product (String name, int code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }
    
    public String getDetails() {
        return String.format("Name : %s, Code : %d, Price : %.2f", name, code, price);
    }
    
    public int getCode() {
        return code;
    }
    
    
}

class Inventory {
    private HashMap<Product, Integer> items = new HashMap<>();
    
    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
    
    public void addItem(Product item, int quantity) {
        Product existing = findItem(item.getCode());
        
        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            items.put(item, quantity);
        }
    }
    
    public HashMap<Product, Integer> getItems() {
        return items;
    }
    
    public void updateQuantity(Product item, int quantity) {
        Product existing = findItem(item.getCode());
        
        if (existing == null) {
            System.out.println("Item Doesn't Exist");
            return;
        }
        
        int newqtty = items.get(existing) + quantity;
        items.put(existing, newqtty);
    }
    
    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.println(item.getDetails());
        }
    }
}

class Customer {
    private HashMap<Product, Integer> orders;
    public Customer () {
        this.orders = new HashMap<>();
    }
    
    public void addItem(Product item, int quantity, Inventory inventory) {
        Product existing = inventory.findItem(item.getCode());
        
        if (existing == null) {
            System.out.println("Item Doesn't exist! ");
            return ;
        }
        
        orders.put(existing, quantity);
        
        inventory.updateQuantity(item, -quantity);
    }
    
    
    public HashMap<Product, Integer> getCart() {
        return orders;
    }
    
}


class Cashier {
    private String name;
    private int id;
    private List <Order> orders;
    
    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
        this.orders = new ArrayList<>();
    }
    
    public void processOrder(Customer customer, double payment, DiscountStrategy discount, Order order ) {
        double total = order.computeTotal();
        double totalPayable = discount.applyDiscount(total);
        if (payment >= totalPayable) {
            double change = payment - totalPayable;
            System.out.println("Successfull Order! From : Customer, Item :");
            orders.add(order);
        } else {
            System.out.println("Not enough! ");
            return;
        }
    }
    
    public void displayItems() {
        for (Order item : orders) {
            item.displayItems();
        }
    }
    
    
}

interface DiscountStrategy {
    public double applyDiscount(double total);
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount (double total) {
        return total * 0.90;
    }
}

class Order {
    private HashMap<Product, Integer> orderitems;
    private double total;
    
    public Order (HashMap<Product, Integer> orderitems) {
        this.orderitems = new HashMap<>(orderitems);
        this.total = computeTotal();
    }
    
    public double computeTotal() {
        double total = 0;
        for (Product item : orderitems.keySet()) {
            total += orderitems.get(item) * orderitems.get(item);
        }
        return total;
    }
    
    public void displayItems() {
        for (Product item : orderitems.keySet()) {
            System.out.println(item.getDetails());
        }
    }
}







