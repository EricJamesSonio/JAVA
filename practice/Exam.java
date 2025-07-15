package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Exam {
    public static void main (String[] args) {
        Product b1 = new Product("Milo", 1, 100);
        System.out.println(b1.getDetails());

        Customer eric = new Customer("Eric", 1);
        Cashier rj = new Cashier("Rj", 2);

        PWDDiscount pwd = new PWDDiscount();

        Inventory inv = new Inventory();

        inv.addItem(b1, 10);

        eric.addToCart(b1, 5, inv);

        Order order1 = new Order(eric, eric.getCartItems() );

        rj.processOrder(eric, 10000, order1, pwd);

        rj.displayItems();

        inv.displayItems();
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
        return String.format("Name : %s, Code : %d, Price : %.2f", name, code, price);
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

    public void addItem (Product item, int quantity) {
        Product existing = findItem(item.getCode()) ;

        if (existing != null) {
            items.put(existing , items.get(existing) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void removeItem(int code) {
        Product existing = findItem(code);

        if (existing != null ) {
            items.remove(existing);
        } else {
            System.out.println("Item Doesn't exist");
        }
    }

    public void updateQuantity(int code, int quantity) {
        Product existing = findItem(code);

        if (existing != null) {
            int currentqtty = items.get(existing) + quantity;
            items.put(existing, currentqtty);
        } else {
            System.out.println("Item Doesn't Exist");
        }
    }
 
    public int getQuantity(Product item) {
        return items.getOrDefault(item, 0);
    }

    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.printf("%s, Quantity : %d",item.getDetails(), items.get(item));
        }
    }

}

class Customer {
    private String name;
    private int id;
    private Cart cart;

    public Customer (String name, int id) {
        this.name = name;
        this.id = id;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return String.format("Name : %s, Id : %d", name, id);
    }

    public void addToCart(Product item, int quantity, Inventory inventory) {
        cart.addToCart(item, quantity, inventory);
    }

    public void removeIoCart(int code, Inventory inventory) {
        cart.removeIoCart(code, inventory);
    }

    public void displayCart() {
        cart.displayCart();
    }

    public void changeQuantity(int code, int quantity , Inventory inventory) {
        cart.changeQuantity(code, quantity, inventory);
    }

    public HashMap<Product, Integer> getCartItems() {
        return cart.getCartItems();
    }

    
}

class Cart {
    private HashMap<Product , Integer> items = new HashMap<>();

    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public void addToCart(Product item, int quantity, Inventory inventory) {
        Product existing = inventory.findItem(item.getCode());

        if (existing == null) {
            System.out.println("Item doesn't exist in inventory");
            return;
        }

        int availableqtty = inventory.getQuantity(item);

        if (availableqtty >= quantity) {
            inventory.updateQuantity(existing.getCode(), -quantity);

            Product existingInCart = findItem(existing.getCode());

            if (existingInCart != null ) {
                items.put(existingInCart, items.get(existingInCart) + quantity);
            } else {
                items.put(existing, quantity);
            }


        }



    }

    public void removeIoCart(int code, Inventory inventory) {
        Product existingInCart = findItem(code);

        if (existingInCart != null ) {
            items.remove(existingInCart);
            inventory.addItem(existingInCart, items.get(existingInCart));
        } else {
            System.out.println("Item Doesn't Exist! ");
        }
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Empty Cart! ");
        } else {
            for (Product item : items.keySet()) {
                System.out.printf("%s | Quantity: %d\n", item.getDetails(), items.get(item));
            }
        }
    }

    public void changeQuantity(int code, int quantity , Inventory inventory) {
        Product existingInCart = findItem(code);

        if (existingInCart != null) {
            int newqtty = items.get(existingInCart) + quantity;
            inventory.updateQuantity(existingInCart.getCode(), quantity);
            items.put(existingInCart, newqtty);
            

            if (items.get(existingInCart) <= 0) {
                items.remove(existingInCart);
            }
        } else {
            System.out.println("Item Doesn't Exist! ");
        }
    }

    public HashMap<Product, Integer> getCartItems() {
        return items;
    }

}

class Cashier {
    private String name;
    private int id;
    private List<Receipt> records = new ArrayList<>();

    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
        }
    public String getName() {
        return name;
    }

    public int id() {
        return id;
    }

    public Receipt processOrder(Customer customer, double payment,Order order, DiscountStrategy discount) {
        double totalPayable = order.getTotal();
        double discountedPrice = discount.applyDiscount(totalPayable);
        
        if (payment < discountedPrice) {
            System.out.println("Not enough Payment amount! ");
            return null;
        } 

        double change = payment - discountedPrice; 
        Receipt receipt = new Receipt(totalPayable, discountedPrice, payment, change);
        records.add(receipt);
        return receipt;
    }

    public void displayItems() {
        for (Receipt r : records) {
            r.getReceipt();
        }
    }

    public List<Receipt> getRecords() {
        return records;
    }


}

class Receipt {
    private double totalPayable;
    private double discountedPrice;
    private double payment;
    private double change;
    
    public Receipt (double totalPayable, double discountedPrice, double payment, double change) {
        this.totalPayable = totalPayable;
        this.discountedPrice = discountedPrice;
        this.payment = payment;
        this.change = change;
    }

    public void getReceipt() {
        System.out.printf("Total Payable : %.2f, Discounted Price : %.2f , Payment : %.2f, Change : %.2f", totalPayable, discountedPrice, payment, change);
    }
} 

class SalesReport {
    private List<Receipt> sales = new ArrayList<>();

    public void addToRecord(Receipt receipt) {
        sales.add(receipt);
    }

    public void displayReport() {
        for (Receipt r : sales) {
            r.getReceipt();
        }
    }


}

class Order {
    private Customer customer;
    private HashMap<Product , Integer> items;
    private double totalAmount;

    public Order (Customer customer, HashMap<Product, Integer> cartitems ) {
        this.customer = customer;
        this.items = new HashMap<>(cartitems);
        this.totalAmount = computeTotal();
    }

    public String getCustomer() {
        return customer.getName();
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public double computeTotal() {
        double total = 0;
        for (Product item : items.keySet() ) {
            total += item.getPrice() * items.get(item);
        }
        return total;
    }

    public double getTotal() {
        return totalAmount;
    }



}

interface DiscountStrategy {
    public double applyDiscount (double total);
    public String getName();
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {

        return total * 0.90;
    }

    @Override
    public String getName() {
        return "PWD Discount";
    }
}

class SeniorDisount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {

        return total * 0.88;
    }

    @Override
    public String getName() {
        return "Senior Discount";
    }
}

