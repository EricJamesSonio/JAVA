package practice;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        //
    }
}

abstract class Product {
    private String name;
    private int code;
    private double price;

    public Product (String name, int code, double price) {
        this.name= name;
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
        return String.format("Name : %s , Code : %d , Price : %.2f", name, code, price);
    }
}

class Inventory {
    private HashMap<Product, Integer> stock = new HashMap<>();

    public void addItem(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null) { 
            stock.put(existing, stock.get(existing) + quantity);
        } else {
            stock.put(item, stock.getOrDefault(item, 0) + quantity);
        }
    }

    public Product findItem(int code) {
        for (Product item : stock.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null; 
    }

    public void removeItem(int code) {
        Product existing = findItem(code);

        if (existing != null) {
            stock.remove(existing);
            System.out.println("Remove Product : " + existing.getName());
        } else {
            System.out.println("Item Doesn't exist");
        }
    }

    public void updateQuantity(int code, int quantity) {
        Product existing = findItem(code);

        if (existing != null) {
            int currentqtty = stock.get(existing);
            stock.put(existing, currentqtty - quantity);
        } else {
            System.out.println("Item Doesn't exist!");
        }
    }

    public void displayItems() {
        for (Product item : stock.keySet()) {
            System.out.println(item.getDetails());
        }
    }

    public int getQuantity(Product item) {
        return stock.getOrDefault(item, 0);
    }
}

class Customer {
    private boolean isSenior;
    private boolean isPWD;
    private Cart cart;

    public Customer (boolean isSenior, boolean isPWD) {
        this.isSenior = isSenior;
        this.isPWD = isPWD;
        this.cart = new Cart();
    }

    public void addToCart(Product item, int quantity, Inventory inventory) {
        cart.addToCart(item, quantity, inventory);
    }

    public boolean getisSenior() {
        return isSenior;
    }

    public boolean getisPWD() {
        return isPWD;
    }
}

class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();

    public void addToCart(Product item, int quantity, Inventory inventory) {
        Product existing = inventory.findItem(item.getCode());

        if (existing == null) {
            System.out.println("Item Doesn't exist in inventory");
            return;
        }

        int availableqtty = inventory.getQuantity(existing);

        if (availableqtty >= quantity) {
            inventory.updateQuantity(existing.getCode(), quantity);

            Product existingincart = findItem(existing.getCode());

            if (existingincart != null) {
                items.put(existingincart, items.get(existingincart) + quantity) ;
            } else {
                items.put(item, quantity);
            }

            System.out.println("Added to cart : " + item.getName() + " Quantity : " + quantity );
        } else {
            System.out.println("Not enough Stock");
        }
    }

    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public void changeQuantity(Product item, int quantity, Inventory inventory) {
        Product existing = findItem(item.getCode());

        if (existing == null) {
            System.out.println("Item Doesn't Exist");
            return;
        }

        int qtty = items.get(item);
        int newqtty = qtty - quantity;

        if (newqtty > 0) {
            inventory.updateQuantity(existing.getCode(), newqtty);

        }
    }



    public HashMap<Product, Integer> getItems() {
        return items;
    }
}