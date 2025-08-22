package try;

import java.util.HashMap;

public class Cart {
    private HashMap <StarbucksItem, Integer> items;
    
    public Cart () {
        this.items = new HashMap<>();
    }

    public void addToCart(StarbucksItem item, int quantity) {
        StarbucksItem existing = findItem(item.getId());

        if (existing == null) {
            items.put(item, quantity);
        } 

        int newqtty = items.get(existing) + quantity;

        items.put(existing, newqtty);
    }

    public void updateQuantity(int code, int quantity) {
        StarbucksItem existing = findItem(code);

        if (existing == null) {
            return;
        }
        // New quantity is set, not deducted or add. just replace
        items.put(existing, quantity);
    }

    public StarbucksItem findItem(int code) {
        for (StarbucksItem item : items.keySet()) {
            if (item.getId() == code) {
                return item;
            }
        }
    return null;
    }

    public HashMap<StarbucksItem, Integer> getItems() {
        return items;
    }
}