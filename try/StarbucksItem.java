package try;

public class StarbucksItem {
    private String name;
    private int id;
    private double price;
    private String description;


    public StarbucksItem(String name, int id, double price, String description)  {
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

class Drinks extends StarbucksItem {
    public Drinks (String name, int id, double price, String description) {
        super(name, id, price, description);
    }
}

class Food extends StarbucksItem {
    public Food(String name, int id, double price, String description ) {
        super(name, id, price, description);
    }
}