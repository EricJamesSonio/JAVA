package exam;

import java.util.HashMap;


public class Viewer {
    private HashMap<Product, Integer> items;

    public Viewer(HashMap<Product, Integer>items) {
        this.items = items;
    }

    public void displayItems() {
        for (Product item : items.keySet()) {
            System.out.println("Name : " + item.getName() + " Price : " + item.getPrice() + " Quantity : " + items.get(item));
        }
    }
}

