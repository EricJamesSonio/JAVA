package exam;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer>items = new HashMap<>();
    private Viewer viewer = new Viewer(this.getItems());

    public void addToCart(Product item , int quantity , Inventory inv) {
        Product existingInInventory = inv.findItem(item.getCode());

        if (existingInInventory == null) {
            return;
        }

        int availableqtty = inv.getItems().get(existingInInventory);

        if (availableqtty < quantity) {
            return;
        }

        Product existingInCart = findItem(existingInInventory.getCode());

        if (existingInCart != null) {
            int newqtty = quantity += items.get(existingInCart);
            items.put(existingInCart, newqtty);
            inv.updateQuantity(item, -quantity);
        } else {
            items.put(item, quantity);
            inv.updateQuantity(item, -quantity);
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

    public void displayItems() {
        viewer.displayItems();
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }
}


