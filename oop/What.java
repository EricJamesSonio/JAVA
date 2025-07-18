package oop;

import java.util.HashMap;
import java.util.List;


public class What {
    public static void main(String[] args) {
        LibraryItem milo = new LibraryItem("Milo", 12, 120.20);
        Library lib = new Library("CMI", 12);
        lib.addItem(milo, 12);
        lib.displayItems();
        Customer c1 = new Customer();
        c1.addItem(milo, 11,lib);
        c1.displayItems();
        lib.displayItems();
        PWDDiscount pwd = new PWDDiscount();
        Cashier eric= new Cashier("Eric", 12);
        eric.processOrder(c1, 1300, pwd);
    }
    
}

class LibraryItem {
    private String name;
    private int code;
    private double price;
    
    public LibraryItem(String name, int code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCode() {
        return code;
    }
    
    public double getPrice() {
        return price;
    }
}

class Library {
    private String name;
    private int id;
    private HashMap<LibraryItem, Integer> items;
    
    public Library (String name, int id) {
        this.name = name;
        this.id = id;
        this.items = new HashMap<>();
    }
    
    public void addItem(LibraryItem item, int quantity) {
        items.put(item, quantity);
    }
    
    public void updateQuantity(int code, int quantity) {
        for (LibraryItem item : items.keySet()) {
            if (item.getCode() == code) {
                int newqtty = items.get(item) + quantity;
                items.put(item, newqtty);
            }
        }
        
    }
    
    public void displayItems() {
        for (LibraryItem item : items.keySet()) {
            System.out.println("Name : " + item.getName() + ", Price : " +  item.getPrice() + " Quantity : " +items.get(item));
        }
    }
    
    
    
}

class Customer {
    private HashMap<LibraryItem, Integer> items = new HashMap<>();
    
    public void addItem(LibraryItem item, int quantity, Library inv) {
        items.put(item, quantity);
        inv.updateQuantity(item.getCode(), -quantity);
    }
    
    public void displayItems() {
        for (LibraryItem item : items.keySet()) {
            System.out.println("Name " + item.getName() + "Quantity" + items.get(item));
        }
    }
    
    public HashMap<LibraryItem, Integer> getCart() {
        return items;
    }
    
}



class Order {
    private HashMap<LibraryItem, Integer> items; 
    private Customer customer;
    private double total;
    
    public Order (Customer customer) {
        this.customer = customer;
        this.items = customer.getCart();
        this.total = computeTotal();
    }
    
    public double computeTotal() {
        double total = 0;
        for (LibraryItem item : items.keySet()) {
            total += item.getPrice() * items.get(item);
        }
        
        return total;
    }
    
    
    
}

class Cashier {
    private String name;
    private int id;
    
    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public void processOrder(Customer customer, double payment, DiscountStategy discount) {
        Order orders = new Order(customer);
        double total = orders.computeTotal();
        double totalPayable = discount.applyDiscount(total);
        
        if (payment >= totalPayable) {
            double change = payment - totalPayable;
            System.out.println("Successfull Process order! Total : " + totalPayable + ", Change : " + change );
        } else {
            System.out.println("No enough money");
        }
    }
    
    
}

interface DiscountStategy {
    public double applyDiscount (double total);
}

class PWDDiscount implements DiscountStategy {
    @Override
    public double applyDiscount (double total) {
        return total * 0.90;
    }
}