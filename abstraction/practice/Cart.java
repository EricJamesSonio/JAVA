package abstraction.practice;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> items = new HashMap<>();

    public void addToCart(Product item, int quantity) {
        items.put(item, quantity);
    }

    public HashMap<Product, Integer>getItems() {
        return items;
    }

    

}
