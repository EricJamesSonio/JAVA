package exam;

public class Product {
    private String name;
    private int code;
    private double price;

    public Product (String name, int code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }
}

class PhysicalProduct extends Product {
    public PhysicalProduct(String name, int code, double price) {
        super(name, code, price);
    }
}

class DigitalProduct extends Product {
    public DigitalProduct(String name, int code, double price) {
        super(name, code, price);
    }
}





