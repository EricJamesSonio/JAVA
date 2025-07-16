package practice;

import java.util.HashMap;

public class Practice {
    public static void main (String[] args) {
        Product b1 = new Product("Bearbrand", 1, 10);
        Inventory inv = new Inventory();
        Customer c1 = new Customer();

        inv.addItem(b1, 10);

        inv.displayItems();

        c1.addItem(b1, 2, inv);

        Order order = new Order(c1, c1.getCartMe());

        Cashier c2 = new Cashier("Rj ", 2);

        PWDDiscount p1 = new PWDDiscount();
        Receipt r = c2.processOrder(c1, order, 1000, p1);

        System.out.println(r.getReceipt());
        

        
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


    public void addItem(Product itemm, int quantity) {
        items.put(itemm, quantity);
    }

    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.println(item.getDetails() + items.get(item));
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

    public void updateQuantity(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            System.out.println("item doesn't exist");
        }
    }

}

class Customer {
    private Cart cart = new Cart();

    public void addItem(Product item, int quantity, Inventory inventory) {
        cart.addItem(item, quantity, inventory);
    }

    public HashMap<Product, Integer> getCart() {
        return cart.getCart();
    }

    public Cart getCartMe() {
        return cart;
    }







}

class Cart {
    private HashMap<Product, Integer> cartitems = new HashMap<>();

    public void addItem(Product item, int quantity, Inventory inventory) {
        inventory.updateQuantity(item, quantity);
        cartitems.put(item, quantity);
    }

    public void displayCart() {
        for (Product item : cartitems.keySet()) {
            System.out.println(item.getDetails() + cartitems.get(item));
        }
    }

    public HashMap<Product, Integer> getCart() {
        return cartitems;
    }

}

class Cashier {
    private String name;
    private int id;

    public Cashier(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getDetails() {
        return String.format("Name : %s, Id : %d", name, id);
    }

    public Receipt processOrder(Customer customer, Order order, double payment, DiscountStrategy discount) {
        double total = order.computeTotal();
        double totalPayable = discount.applyDiscount(total);
        if (payment >= totalPayable) {
            double change = payment - totalPayable;
            Receipt receipt = new Receipt(total, payment, totalPayable, change);
            return receipt;
        } else {
            return null;
        }
    }

}

class Order {
    private Customer customer;
    private Cart cart;
    private HashMap<Product, Integer> orderitems = new HashMap<>();
    private double totalamount;

    public Order (Customer customer, Cart cart) {
        this.customer = customer;
        this.cart =cart;
        this.orderitems = cart.getCart();
        this.totalamount = computeTotal();
    }

    public double computeTotal() {
        double total = 0;
        for (Product item : orderitems.keySet()) {
            total += orderitems.get(item);
        }
        return total;
    }
}

interface DiscountStrategy {
    public double applyDiscount(double total);
    public String getName();
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }
    @Override
    public String getName() {
        return "PWD DISCOUNT";
    }

}

class Receipt {
    private double total;
    private double payment;
    private double totalPayable;
    private double change;

    public Receipt (double total, double payment, double totalPayable, double change) {
        this.total = total;
        this.payment= payment;
        this.totalPayable = totalPayable;
        this.change = change;
    }

    public String getReceipt() {
        return String.format("Total : %.2f, Payment : %.2f, Total Payable : %.2f, Change : %.2f", total, payment, totalPayable, change);
    }

}