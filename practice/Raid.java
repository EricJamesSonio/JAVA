import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Id p1 = new Id(true, false);
        
        Customer c1 = new Customer(p1);
        
        Product milo = new Product ("Milo", 1, 20);
        Library lib = new Library("CMI");
        lib.addItem(milo, 10);
        lib.displayItems();
        c1.addToCart(milo, 2, lib);
        c1.displayItems();
        
        Cashier rj = new Cashier("Rj", 2);
        Receipt receipt1 = rj.processOrder(c1, 120);
        System.out.println(receipt1.getReceipt());
            
    }
    
}

// Product 

class Product {
    private String name;
    private int code;
    private double price;
    
    public Product (String name, int code, double price) {
        this.name =name;
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

// Library

class Library {
    private HashMap<Product, Integer> items;
    private String name;
    
    public Library (String name) {
        this.name = name;
        this.items = new HashMap<>();
    }
    
    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
    
    public void addItem(Product item, int quantity) {
        Product existing = findItem(item.getCode());
        
        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            items.put(item, quantity);
        }
    }
    
    public void updateQuantity(Product item, int quantity) {
        Product existing = findItem(item.getCode());
        
        if (existing == null) {
            System.out.println("Item Doesn't exist");
            return;
        }
        
        int newqtty = items.get(existing) + quantity;
        
        if (newqtty <= 0) {
            items.remove(existing);
        } else {
            items.put(existing, newqtty);
        }
    }
    
    public HashMap<Product, Integer> getItems() {
        return items;
    }
    
    
    
    public void displayItems() {
        System.out.println("Item in Library");
        for (Product item : items.keySet()) {
            System.out.println(item.getName() + items.get(item));
        }
    }
}

// Customer

class Customer {
    private Id personalId;
    public Cart cart;
    
    public Customer (Id personalId) {
        this.personalId = personalId;
        this.cart = new Cart();
    }
    
    public void addToCart(Product item, int quantity, Library lib) {
        cart.addToCart(item, quantity, lib);
    }
    
    public void displayItems() {
        cart.displayItems();
    }
    
    public Product findItem(int code) {
        return cart.findItem(code);
    }
    
    public Id getId() {
        return personalId;
    }
}
// Cart

class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();
    
    public Product findItem(int code) {
        for (Product item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
    
    public void addToCart(Product item, int quantity, Library lib) {
        Product existing = lib.findItem(item.getCode());
        
        if (existing == null) {
            return;
        }
        
        int availableqtty = lib.getItems().get(existing);
        
        if (availableqtty < quantity) {
            System.out.println("Not enougn quantity");
            return;
        }
        
        Product existingInCart = findItem(item.getCode());
        
        if (existingInCart != null) {
            int newqtty = items.get(existingInCart) + quantity;
            items.put(existingInCart, quantity);
            lib.updateQuantity(existingInCart, -quantity);
        } else {
            items.put(existing, quantity);
            lib.updateQuantity(existing, -quantity);
        }
    }
    
    public HashMap<Product, Integer> getItems() {
        return items;
    }
    
    public void displayItems() {
        System.out.println("< ----- Cart --------->");
        for (Product item : items.keySet()) {
            System.out.println(item.getName() + items.get(item));
        }
    }
}

// Cashier


class Cashier {
    private String name;
    private int id;
    
    public Cashier (String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public Receipt processOrder(Customer customer, double payment) {
        Order order1 = new Order(customer);
        
        double subtotal = order1.getSubtotal();
        double discounted = order1.getDiscount();
        double tax = order1.getTax();
        double totalPayable = order1.getTotalPayable();
        
        if (payment < totalPayable) {
            System.out.println("Not enough payment");
            return null;
        }
    
        double change = payment - totalPayable;
        
        Receipt r1 = new Receipt(customer, subtotal, discounted,tax, totalPayable, change) ;
        return r1;
    }
    
    
}

// ORder
class Order {
    private Customer customer;
    private double subtotal;
    private double tax;
    private double discountedAmount;
    private double totalPayable;
    private HashMap<Product , Integer> items;
    
    public Order(Customer customer) {
        this.customer = customer;
        this.items = customer.cart.getItems();
        this.subtotal = computeTotal();
        this.discountedAmount = discountApplication();
        this.tax = appliedTax();
        this.totalPayable = totalPayableAmount();
    }
    
    public double computeTotal() {
        double total = 0;
        for (Product item : items.keySet()) {
            total += item.getPrice() * items.get(item);
        }
        return total;
    }
    
    public double discountApplication () {
        if (customer.getId().isPWD()) {
            PWDDiscount pwd = new PWDDiscount();
            double discounted = pwd.applyDiscount(subtotal);
            return discounted;
        } else if (customer.getId().isSenior()) {
            SeniorDiscount senior = new SeniorDiscount();
            double discounted = senior.applyDiscount(subtotal);
            return discounted;
        } else {
            return 0.0;
        }
    }
    
    public double appliedTax() {
        double tax = subtotal * 0.12;
        return tax;
    }
    
    public double totalPayableAmount() {
        double totalpayamount = tax + subtotal - discountedAmount;
        return totalpayamount;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double getDiscount() {
        return discountedAmount;
    }
    
    public double getTax() {
        return tax;
    }
    
    public double getTotalPayable() {
        return totalPayable;
    }
    

    
    
    
    
}

// Receipt 

class Receipt {
    Customer customer;
    double subtotal;
    double discountPrice;
    double tax;
    double totalPayable;
    double change;
    
    public Receipt(Customer customer, double subtotal,double discountPrice, double tax, double totalPayable, double change) {
        this.customer =customer;
        this.subtotal = subtotal;
        this.tax = tax;
        this.discountPrice = discountPrice;
        this.totalPayable = totalPayable;
        this.change = change;
    }
    
    public String getReceipt () {
        return "Customer : " + customer + 
                "Subtotal : " + subtotal +
                "Discount : " + discountPrice +
                "Tax : " + tax+
                "Total Payable : " + totalPayable +
                "Change : " + change + "" ;
    
    };
    
    
    
}


// Discount Strategies

interface DiscountStrategy {
    public double applyDiscount(double total);
    public String getName();
}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount (double total) {
        return total * 0.12;
    }
    
    @Override
    public String getName() {
        return "PWD DISCOUNT 12 %";
    }
}

class SeniorDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount (double total) {
        return total * 10;
    }
    
    @Override
    public String getName() {
        return "Senior Discount 10 %";
    }
    
    
}



class Id {
    private boolean isSenior;
    private boolean isPWD;
    
    public Id (boolean isSenior, boolean isPWD) {
        this.isSenior = isSenior;
        this.isPWD = isPWD;
    }
    
    public boolean isSenior() {
        return isSenior;
    }
    
    public boolean isPWD () {
        return isPWD;
    }
}