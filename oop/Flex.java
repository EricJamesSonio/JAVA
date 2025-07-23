package oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flex {
    public static void main(String[] args) {
        DigitalProduct smart50 = new DigitalProduct("Smart50", 12, 50.00);
        DigitalProduct smart100 = new DigitalProduct("Smart100", 11, 100.00);
        DigitalProduct smart150 = new DigitalProduct("Smart150", 14, 150.00);

        smart50.getDetails();
        smart100.getDetails();
        smart150.getDetails();

        PWDID pwd = new PWDID();
        SeniorID senior = new SeniorID();

        Customer c1 = new Customer(pwd);

        c1.addItem(smart150, 12);
        c1.addItem(smart50, 10);
        c1.displayItems();

        Cashier cash1 = new Cashier("Zeht", 12);

        cash1.processOrder(c1, 5000);
        cash1.displayOrders();



    }
}

class Product {
    private String name;
    private double price;
    private int code;

    public Product(String name, int code, double price) {
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

    public String getDetails() {
        return "Product Name: " + name +
                "Code : " + code + 
                "Price : " + price;
    }


}


class DigitalProduct extends Product {
    public DigitalProduct(String name, int code, double price) {
        super(name, code, price);
    }
}

class PhysicalProduct extends Product {
    public PhysicalProduct(String name, int code, double price) {
        super(name, code, price);
    }
}


class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        CartItem existing = findItem(product.getCode());

        if (existing != null) {
            int newqtty = existing.getQuantity() + quantity;
            existing.setQuantity(newqtty);
        } else {
            CartItem item =  new CartItem(product, quantity);
            items.add(item);
        }
    }

    public CartItem findItem(int code) {
        for (CartItem item : items) {
            if (item.getProduct().getCode() == code) {
                return item;
            } 
        }
        return null;
    }

    public void displayItems() {
        for (CartItem item : items) {
            System.out.println(item.getProduct().getDetails() + item.getQuantity());
        }
    }

    public List<CartItem> getCart() {
        return items;
    }


}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem (Product product, int quantity) {
        this.product = product;
        this.quantity= quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newqtty) {
        quantity = newqtty;
    }
}

class Customer {
    private IdStrategy id;
    private Cart cart;

    public Customer(IdStrategy id) {
        this.id = id;
        this.cart = new Cart();
    }

    public IdStrategy getID() {
        return id;
    }

    public void addItem(Product product, int quantity) {
        cart.addItem(product, quantity);
    }

    public CartItem findItem(int code) {
        return cart.findItem(code);
    }

    public void displayItems() {
        cart.displayItems();
    }

    public Cart getCart() {
        return cart;
    }
}

class Cashier {
    private String name;
    private int code;
    private List<Order> orders;

    public Cashier (String name, int code) {
        this.name = name;
        this.code = code;
        this.orders = new ArrayList<>();
    }

    public Receipt processOrder(Customer customer, double payment) {
        Customer customer1 = customer;
        Order order = new Order(customer1);
        if (payment < order.getTotalPayable()) {
            System.out.println("Not enough Money");
            return null;
        }

        double appliedDiscount = order.getDiscount().applyDiscount(order.getTotal());
        double change = payment - order.getTotalPayable();

        Receipt receipt = new Receipt (order.getTotal(), order.getTotalPayable(), change, customer,  appliedDiscount);
        System.out.println(receipt.getReceipt());
        return receipt;

    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void displayOrders() {
        for (Order item : orders) {
            System.out.println(item.getDetails());
    }
    }

}

class Order {
    private Customer customer;
    private double total;
    private double totalPayable;
    private DiscountStrategy discount;

    public Order(Customer customer) {
        this.customer = customer;
        this.total = computeTotal();
        this.totalPayable = computeTotalPayable();
        this.discount = getDiscount();
    }

    public double computeTotal() {
        double total = 0;
        for (CartItem item : customer.getCart().getCart()) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

    public double computeTotalPayable() {
        IdStrategy id = customer.getID();
        DiscountStrategy discount = null;
        if (id.getName().equals("PWD")) {
            PWDDiscount pwd = new PWDDiscount();
            discount = pwd;
            } else if (id.getName().equals( "SENIOR")) {
                SeniorDiscount senior = new SeniorDiscount();
                discount = senior;
            } else {
                NoDiscount nodiscount = new NoDiscount();
                discount = nodiscount;
            }
        double total = computeTotal();
    
        double appliedDiscount = discount.applyDiscount(total);
        double totalPayable = total - appliedDiscount;
        return totalPayable;
    }

    public double getTotal() {
        return total;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public DiscountStrategy getDiscount() {
        return discount;
        }

    public String getDetails() {
        return "Total : " + total + 
                "Total Payable : " + totalPayable +
                "Discount :" + discount.getClass();
    }
}

abstract class IdStrategy {
    abstract String getName();
}

class PWDID extends IdStrategy {
    private String name = "PWD";
    @Override
    String getName() {
        return name;
    }
}

class SeniorID extends IdStrategy {
    private String name = "SENIOR";
    @Override
    String getName() {
        return name;
    }
}

interface DiscountStrategy {
    public double applyDiscount(double total);
}

class PWDDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double total) {
        return total * 0.12;
    }
}

class SeniorDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double total) {
        return total * 0.10;
    }
}

class NoDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double total) {
        return total * 0.00;
    }
}

class Receipt {
    private double total;
    private double totalPayable;
    private double change;
    private Customer customer;
    private double appliedDiscount;

    public Receipt (double total, double totalPayable, double change, Customer customer, double appliedDiscount) {
        this.total = total;
        this.totalPayable = totalPayable;
        this.change = change;
        this.customer = customer;
        this.appliedDiscount = appliedDiscount;
    }

    public String getReceipt() {
        return "Total : " + total + 
                "Applied Discount" + appliedDiscount + 
                "Total PAyable : " + totalPayable +
                "Change : " + change + 
                "Customer : " + customer;

    }

}