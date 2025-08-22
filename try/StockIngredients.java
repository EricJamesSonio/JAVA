package try;

import java.util.HashMap;

public class StockIngredients {
    private HashMap <Ingredient, Integer> items;
    
    public StockIngredients() {
        this.items = new HashMap<>();
    }

    public Ingredient findItem(int code) {
        for (Ingredient item : items.keySet()) {
            if (item.getId() == code) {
                return item;
        } 
        }
        return null;
        }

    public void addStock(Ingredient item, int quantity) {
        Ingredient existing = findItem(item.getId());

        if (existing == null) {
            items.put(item, quantity);
        }

        int newqtty = items.get(existing) + quantity;
        items.put(existing, newqtty);
    }

    public void removeStock(int code) {
        Ingredient existing = findItem(code);

        if (existing == null) {
            return;
        }

        items.remove(existing);
    }
 
    public void updateStock(int code, int quantity) {
        Ingredient existing = findItem(code);

        if (existing == null) {
            return;
        }

        int newqtty = items.get(existing) + quantity;

        if (newqtty <= 0) {
            items.remove(existing);
        }

        items.put(existing, newqtty);
    }

    public HashMap<Ingredient, Integer> getItems() {
        return items;
    }
}
