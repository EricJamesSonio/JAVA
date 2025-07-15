package store2;

import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main (String[] args) {
        
    }
}


class Product {
    private String name;
    private int code;
    private double price;
    private int quantity;

    public Product(String name, int code, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
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

    public int quantity () {
        return quantity;
    }

    public String getDetails() {
        return String.format("Name : %s, Code : %d, Price : %.2f, Quantity : %d", name, code, price, quantity);
    }
}

class DigitalProduct extends Product {
    public DigitalProduct(String name, int code, double price, int quantity) {
        super(name, code, price, quantity);
    }
}

class PhysicalProduct extends Product {
    public PhysicalProduct (String name, int code, double price , int quantity) {
        super(name, code, price, quantity);
    }
}

class Inventory {
    private List<Product> items;

    public Inventory () {
        this.items = new ArrayList<>();
    }

    public Product findItem(int code) {
        for (Product item : items) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}