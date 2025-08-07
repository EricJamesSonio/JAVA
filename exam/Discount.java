package exam;

public interface Discount {
    public double applyDiscount(double total);
    public String getName();
}

class PWDDiscount implements Discount {
    public double applyDiscount(double total) {
        return total * 0.12;
    }

    public String getName() {
        return "PWD DISCOUNT 12%";
    }
}

class SeniorDiscount implements Discount {
    public double applyDiscount(double total) {
        return total * 0.12;
    }

    public String getName() {
        return "SENIOR DISCOUNT 12%";
    }
}


