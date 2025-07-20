package oop;

import java.util.HashMap;

public class Code {
    public static void main(String[] args) {
    }
}

class Product {
    private String name;
    private double price;
    private int code;
    private String brand;

    public Product (String name, int code, double price, String brand) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.brand = brand;    
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

    public String getBrand() {
        return brand;
    }



    public String getDetails() {
        return String.format("Name : %s , Code : %d, Price : %.2f", name, code, price);
    }

}

class Food extends Product {
    public Food(String name, int code, double price, String brand) {
        super(name, code, price, brand);
    }
}

class IceCream extends Food {
    public IceCream(String name, int code, double price, String brand) {
        super(name, code, price, brand);
    }
}
 

class Accessory extends Product {
    public Accessory(String name, int code, double price, String brand) {
        super(name, code, price, brand);
    }
}
 

class Hygiene extends Product {
    public Hygiene(String name, int code, double price, String brand) {
        super(name, code, price, brand);
    }
}

class DigitalProduct extends Product {
    public DigitalProduct(String name, int code, double price, String brand) {
        super(name, code, price, brand);
    }
}

class Inventory {
    private HashMap<Product , Integer> items = new HashMap<>();

    public void addItem(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            items.put(item, quantity);
        }
    }

    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if(item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public void updateQuantity(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null ) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            System.out.println("Item doesn't exist");
        }
    }

    public HashMap<Product, Integer> getItems () {
        return items;
    }

    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.println(item.getDetails());
        }
    }
}

class SevenEleven {
    private String name;
    private int id;
    private Inventory inv;

    public SevenEleven(String name, int id) {
        this.name = name;
        this.id = id;
        this.inv = new Inventory();
    }

    public void addItem(Product item, int quantity) {
        inv.addItem(item, quantity);
    }

    public Product findItem(int code) {
        return inv.findItem(code);
    }

    public void updateQuantity(Product item, int quantity) {
        inv.updateQuantity(item, quantity);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return String.format("Name STore : %s, Id : %d", name, id);
    }

}

class Customer {
    private HashMap<Product, Integer> items;
    private Cart cart;

    public Customer() {
        this.cart = new Cart();
        this.items = new HashMap<>();
    }

    public HashMap<Product, Integer> getCart() {
        return items;
    }

    public void addToCart(Product item, int quantity, Inventory inv) {
        cart.addToCart(item, quantity, inv);
    }


}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();

    public void addToCart(Product item, int quantity, Inventory inv) {
        Product existing = inv.findItem(item.getCode());

        if (existing == null) {
            System.out.println("ITem Doesn't exist! ");
            return;
        }

        int availableqtty = inv.getItems().get(existing);

        if (availableqtty >= quantity) {
            items.put(existing, quantity);
            inv.updateQuantity(item, -quantity);
        } else {
            System.out.println("Not enough quantity! ");
        }
    }

    public HashMap<Product, Integer> getCartItems() {
        return items;
    }

}

class Order {
    private HashMap<Product, Integer> items;
    private double total;
    private Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = customer.getCart();
        this.total = conmputeTotal();
    }

    public double conmputeTotal() {
        double total = 0;
        for (Product item : items.keySet()) {
            total += item.getPrice() * items.get(item);
            }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public HashMap<Product , Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }


}

interface DiscountStategy {
    public double applyDiscount(double total);
}

class PWDDiscount implements DiscountStategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }
}

 
 