package exam;

import java.util.HashMap;

public class Inventory {
    private HashMap<Product, Integer> items = new HashMap<>();
    private int threshold;
    private Viewer viewer = new Viewer(this.getItems());

    public void addItem(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = quantity += items.get(existing);
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

    public void removeItem(Product item) {
        Product existing = findItem(item.getCode());

        if (existing != null) {
            items.remove(existing);
        } else {
            System.out.println("Item doesnt exist");
        }
    }

    public void updateQuantity(Product item, int quantity) {
        Product existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = quantity += items.get(existing);
            items.put(existing, newqtty);
        }

        else {
            return;
        }
    } 

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public void setThreshold(int newthreshold) {
        threshold = newthreshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public void displayItems() {
        viewer.displayItems();
    }

}

