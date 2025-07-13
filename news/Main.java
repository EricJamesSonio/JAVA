package news;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        Customer c1 = new Customer("Eric james", 1, 200);
        c1.cashIn(100);
        c1.showBalance();

        Cashier cs1 = new Cashier("Miles", 2);
        System.out.println(cs1.getName());

    }
}

abstract class LibraryItem {
    private String name;
    private int code;
    private double price;
    private int quantity;

    public LibraryItem (String name, int code, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetails() {
        return String.format("%s %d %2.f %d", name, code, price, quantity);
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

    public void setQuantity(int newqtty) {
        this.quantity = newqtty;
    } 


}

class Book extends LibraryItem {
    String lesson;

    public Book (String name, int code, double price, int quantity, String lesson) {
        super(name, code, price, quantity);
        this.lesson = lesson;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Lesson : " + lesson;
    }


}

class Magazine extends LibraryItem {
    String type;

    public Magazine (String name, int code, double price, int quantity, String type) {
        super(name, code, price, quantity);
        this.type = type;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ",Type : " + type;
    }


}

class Inventory {
    private List<LibraryItem> items;
    private InventoryViewer viewer;

    public Inventory() {
        this.items = new ArrayList<>();
        this.viewer = new InventoryViewer(this);
    }

    public void addItem(LibraryItem item) {
        LibraryItem existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = existing.getQuantity() + item.getQuantity();
            existing.setQuantity(newqtty);
        } else {
            items.add(item);
        }
    }

    public void removeItem(LibraryItem item) {
        LibraryItem existing = findItem(item.getCode());

        if (existing != null) {
            items.remove(existing);
        } else {
            System.out.println("Item Doesn't Exist");
        }
    }

    public LibraryItem findItem(int code) {
        for (LibraryItem item : items) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public void displayItems() {
        viewer.displayItems();
    }

}

class InventoryViewer {
    private Inventory inventory;

    public InventoryViewer (Inventory inventory) {
        this.inventory = inventory;
    }

    public void displayItems() {
        if (inventory.getItems().isEmpty()) {
            System.out.println("Empty inventory");
        }
        for (LibraryItem item : inventory.getItems()) {
            System.out.println(item.getDetails());
        }
    }
}

class Store {
    private String name;
    private Inventory inventory;

    public Store(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public void addItem(LibraryItem item) {
        inventory.addItem(item);
    }

    public void findItem(int code) {
        inventory.findItem(code);
    }

    public void removeItem(LibraryItem item) {
        inventory.removeItem(item);
    }

    public void nameStore() {
        System.out.println("Name : " + name);
    }

    public void displayItems() {
        inventory.displayItems();
    }



}

interface DiscountStrategy {
    public double applyDiscount(double total) ;
    public String getName();
    
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

class Cart {
    private List<LibraryItem> cartitems;

    public Cart () {
        this.cartitems = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        LibraryItem existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = existing.getQuantity() + item.getQuantity();
            existing.setQuantity(newqtty);

        } else {
            cartitems.add(item);
        }
    }

    public void removeItem(LibraryItem item) {
        LibraryItem existing = findItem(item.getCode());

        if (existing != null) {
            cartitems.remove(existing);
        } else {
            System.out.println("Item Doens't Exist!");
        }
    }

    public LibraryItem findItem(int code) {
        for (LibraryItem item : cartitems) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public List<LibraryItem>  getList() {
        return cartitems;
    }

    public void showCart() {
        for (LibraryItem item : cartitems) {
            System.out.println(item.getDetails());
        }
    }

 
}
