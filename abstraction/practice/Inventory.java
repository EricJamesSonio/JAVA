package abstraction.practice;

import java.util.HashMap;

import java.util.*;

public class Inventory {
    private HashMap<Product, Integer> items = new HashMap<>();

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
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }



}
