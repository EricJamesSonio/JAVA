package try;

import java.util.HashMap;

public class StockItems {
    private HashMap <StarbucksItem, Integer> items;
    
    public StockItems() {
        this.items = new HashMap<>();
    }

    public StarbucksItem findItem(int code) {
        for (StarbucksItem item : items.keySet()) {
            if (item.getId() == code) {
                return item;
        } 
        }
        return null;
        }

    public void addStock(StarbucksItem item, int quantity) {
        StarbucksItem existing = findItem(item.getId());

        if (existing == null) {
            items.put(item, quantity);
        }

        int newqtty = items.get(existing) + quantity;
        items.put(existing, newqtty);
    }

    public void removeStock(int code) {
        StarbucksItem existing = findItem(code);

        if (existing == null) {
            return;
        }

        items.remove(existing);
    }
 
    public void updateStock(int code, int quantity) {
        StarbucksItem existing = findItem(code);

        if (existing == null) {
            return;
        }

        int newqtty = items.get(existing) + quantity;

        if (newqtty <= 0) {
            items.remove(existing);
        }

        items.put(existing, newqtty);
    }

    public HashMap<StarbucksItem, Integer> getItems() {
        return items;
    }
}
