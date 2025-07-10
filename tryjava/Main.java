package tryjava;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        //
    }
}

abstract class Product {
    String name;
    int code;
    double price;
    int quantity;

    public Product (String name, int code, double price, int quantity) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newquantity) {
        this.quantity = newquantity;
    }

    public String getDetails() {
        return String.format("%s (%d) : %2.f x %d , EXP %s", name, code, price, quantity);
    }

} 

class DigitalProduct extends Product {
    String expdate;

    public DigitalProduct (String name, int code, double price, int quantity , String expdate) {
        super(name, code, price, quantity);
        this.expdate = expdate;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Exp Date : " + expdate;
    }
 
}

class PhysicalProduct extends Product {
    String size;

    public PhysicalProduct (String name, int code, double price, int quantity , String size) {
        super(name, code, price, quantity);
        this.size = size;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Size : " + size;
    }
}

class Inventory {
    private List<Product> products = new ArrayList<>();
    private InventoryViewer viewer;

    public Inventory () {
        this.viewer = new InventoryViewer(this);
    }
    public Product findItem(int code) {
        for (Product product : products) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
    }

    public void addItem(Product product) {
        Product existing = findItem(product.getCode());

        if (existing != null) {
            int updatedqtty = existing.getQuantity() + product.getQuantity();
            existing.setQuantity(updatedqtty);
        } else {
            products.add(product);
        }
    }

    public void removeItem(Product product) {
        Product existing = findItem(product.getCode());

        if (existing != null) {
            products.remove(existing);
        } else {
            System.out.println("Item Doesn't Exist! ");
        }
    }

    public void increaseQuantity(Product product, int quantity) {
        Product existing = findItem(product.getCode());
        if (existing != null ) {
            int updatedqtty = existing.getQuantity() + quantity;
            existing.setQuantity(updatedqtty);
        } else {
            System.out.println("Item Doesn't Exist");
        }
    }

    public void decreaseQuantity(Product product, int quantity) {
        Product existing = findItem(product.getCode());

        if (existing != null) {
            int updatedqtty = existing.getQuantity() - quantity;
            existing.setQuantity(updatedqtty);
        } else {
            System.out.println("Item Doesn't Exist");
        }
    }

    public List<Product> getItems() {
        return products;
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
            System.out.println("Inventory List is Empty");
            return;
        }
        for (Product product : inventory.getItems()) {
            System.out.println(product.getDetails());
        }

    }
}

class Store {
    String name;
    String location;
    private Inventory inventory;

    public Store (String name, String location, Inventory inventory) {
        this.name = name;
        this.location = location;
        this.inventory = inventory;
    }

    void addItem(Product product) {
        inventory.addItem(product);
    }

    void removeItem(Product product) {
        inventory.removeItem(product);
    }

    void displayItems() {
        inventory.displayItems();
    }

    void decreaseQuantity(Product product, int quantity) {
        inventory.decreaseQuantity(product, quantity);
    }

    void increaseQuantity(Product product, int quantity) {
        inventory.increaseQuantity(product, quantity);
    }

    public String getDetails() {
        return String.format("%s %s", name, location);
    }
}

interface DiscountStrategy {
    double applyDiscount(double total);
    String getName();
    
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.10;
    }

    @Override
    public String getName() {
        return "PWD DISCOUNT (10%)";
    }
}

class SeniorDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.12;
    }

    @Override
    public String getName() {
        return "SENIOR DISCOUNT (12%)";
    }
}

class NODiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total ;
    }

    @Override
    public String getName() {
        return "NO DISCOUNT (0%)";
    }
}