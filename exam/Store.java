package exam;

public class Store {
    private Inventory inventory;
    private String name;
    private int id;
    private SalesReport salesReport;

    public Store(String name, int id) {
        this.name = name;
        this.id = id;
        this.inventory = new Inventory();
        this.salesReport = new SalesReport();
    }

    public void addItem(Product item, int quantity) {
        inventory.addItem(item, quantity);
    }

    public void updateQuantity(Product item, int quantity) {
        inventory.updateQuantity(item, quantity);
    }

    public void removeItem(Product item) {
        inventory.removeItem(item);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public SalesReport getSalesReport() {
        return salesReport;
    }
}
