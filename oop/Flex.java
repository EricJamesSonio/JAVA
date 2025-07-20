package oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flex {
    public static void main(String[] args) {

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
        this.id = null;
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

    public Cashier (String name, int code) {
        this.name = name;
        this.code = code;
    }

    public processOrder() {
        
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

}

class Order {
    private Customer customer;
    private double total;
    private double totalPayable;

    public Order(Customer customer) {
        this.customer = customer;
        this.total = computeTotal();
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
        DiscountStategy discount = null;
        if (id.getName() == "PWD") {
            PWDDiscount pwd = new PWDDiscount();
            discount = pwd;
            } else if (id.getName() == "SENIOR") {
                SeniorDiscount senior = new SeniorDiscount();
                discount = senior;
            } else {

            }
        double total = computeTotal();

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

class PWDDiscount implements DiscountStategy{
    @Override
    public double applyDiscount(double total) {
        return total * 0.88;
    }
}

class SeniorDiscount implements DiscountStategy{
    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }
}

class NoDiscount implements DiscountStategy{
    @Override
    public double applyDiscount(double total) {
        return total ;
    }
}

