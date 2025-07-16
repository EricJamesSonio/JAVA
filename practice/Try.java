package practice;

import java.util.HashMap;;

public class Try {
    public static void main(String[] args) {
        DigitalProduct smart50 = new DigitalProduct("smart50", 1, 50, "50");
        DigitalProduct smart150 = new DigitalProduct("smart150", 2, 150, "150");
        PhysicalProduct milo = new PhysicalProduct("Milo", 2, 200, "small");
        PhysicalProduct milo2 = new PhysicalProduct("Milobig", 3, 400, "Big");

        Inventory inv = new Inventory();

        inv.addItem(milo, 10);
        inv.addItem(milo2, 20);
        inv.addItem(smart150, 30);
        inv.addItem(smart50, 100);

        inv.displayItems();

        Customer eric = new Customer("Eric ", 1);

        Cashier rj = new Cashier("rj", 2);

        eric.addItem(milo2, 2, inv);
        eric.addItem(smart50, 1, inv);

        inv.displayItems();

        eric.displayCart();

        Order order1 = new Order(eric, eric.getCart());

        PWDDiscount pwd = new PWDDiscount();
        Receipt receipt = rj.processOrder(eric, order1, 4000, pwd);

        System.out.println(receipt.getReceipt());


    

        

    }
}

class Product {
    private String name;
    private int code;
    private double price;

    public Product (String name, int code, double price) {
        this.name = name;
        this.code  =code;
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

class DigitalProduct extends Product {
    private String datasize;

    public DigitalProduct(String name, int code, double price, String datasize) {
        super(name, code, price);
        this.datasize = datasize;
    }

    public String getSize() {
        return datasize;
    }

}
 

class PhysicalProduct extends Product {
    private String size;

    public PhysicalProduct(String name, int code, double price, String size) {
        super(name, code, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

}
 
class Inventory {
    private HashMap<Product , Integer> items = new HashMap<>();

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
            items.put(existing, items.get(existing) + quantity);
        } else  {
            items.put(item, quantity);
        }
    }

    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.printf("%s , Quantity : %d",item.getDetails(), items.get(item));
            System.out.println();
        }
    }

    public void updateQuantity(int code, int quantity) {
        Product existing = findItem(code);

        if (existing == null) {
            System.out.println("Item Doesn't Exist! ");
        }

        int newqtty = items.get(existing) + quantity;
        items.put(existing, newqtty);

        if (items.get(existing) <= 0) {
            items.remove(existing);
        }
    }

    public int getQuantity(Product item) {
        return items.get(item);
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

    public void addItem(Product item, int quantity, Inventory inventory) {
        cart.addItem(item, quantity, inventory);
    }

    public void displayCart() {
        cart.displayCart();
    }

    public Cart getCart() {
        return cart;
    }



}

class Cart {
    private HashMap<Product, Integer> cartitems = new HashMap<>();

    public void addItem(Product item, int quantity, Inventory inventory) {
        Product existing = inventory.findItem(item.getCode());

        if (existing == null) {
            System.out.println("Item doensnt exist");
            return;
        }

        int availableqtty = inventory.getQuantity(item);

        if (availableqtty >= quantity) {
            inventory.updateQuantity(existing.getCode(), -quantity);

            Product existingInCart = findItem(item.getCode());
            if (existingInCart != null ) {
                cartitems.put(existingInCart, cartitems.get(existingInCart) + quantity);
            } else {
                cartitems.put(existing, quantity);
            }
        } else {
            System.out.println("Not enoughj quantity!");
            return;
            }
    }

    public Product findItem(int code) {
        for (Product item : cartitems.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public HashMap<Product, Integer> getCartItems() {
        return cartitems;
    }

    public void displayCart() {
        for (Product item : cartitems.keySet()) {
            System.out.printf("%s, Quantity : %d", item.getDetails(), cartitems.get(item));
            System.out.println();
        }
    }


}

class Order {
    private Cart cart;
    private Customer customer;
    private HashMap<Product, Integer> items;
    private double totalamount;

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        this.items = cart.getCartItems();
        this.totalamount = computeTotal();
    }

    public double computeTotal() {
        double total = 0;
        for (Product item :  items.keySet()) {
            total += item.getPrice() * items.get(item); 
        }
        return total;
    }


}


class Cashier {
    private String name;
    private int id;

    public Cashier(String name, int id) {
        this.name= name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Receipt processOrder(Customer customer, Order order,double payment, DiscountStrategy discount) {
        double total = order.computeTotal();
        double totalPayable = discount.applyDiscount(total);

        if (payment >= totalPayable) {
            double change = payment -= totalPayable;
            Receipt r = new Receipt(customer ,total, totalPayable, payment, change);
            return r;
        } else {
            System.out.println("Inusuficinet money");
            return null;
            }
        
    }

}

interface DiscountStrategy {
    public double applyDiscount(double total);
    public String getName() ;

}

class PWDDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.90;
    }

    @Override
    public String getName() {
    
        return "PWD DIscount";
    }

}

class Receipt {
    private double total;
    private double totalPayable;
    private double payment;
    private double change;
    private Customer customer;

    public Receipt (Customer customer,double total, double totalPayable, double payment, double change) {
        this.customer = customer;
        this.total = total;
        this.totalPayable = totalPayable;
        this.payment = payment;
        this.change= change;
        
    } 

    public String getReceipt() {
        return String.format("Customer : %s , Total : %.2f, TotalPayable : %.2f, Payment : %.2f, Change : %.2f", customer.getName(),total, totalPayable, payment, change);
    }
}
