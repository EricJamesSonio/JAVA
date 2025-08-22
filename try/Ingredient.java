package try;

public class Ingredient {
    public String name;
    public double volume;
    public String unit;
    private int id;
    
    public Ingredient (String name, double volume, String unit, int id) {
        this.name = name;
        this.volume = volume;
        this.unit = unit;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public String getUnits() {
        return unit;
    }

    public int getId() {
        return id;
    }
}

class Egg extends Ingredient {
    public Egg(String name, double volume, String unit, int id) {
        super(name, volume, unit, id);
    }
}

class Milk extends Ingredient {
    public Milk (String name, double volume, String unit, int id) {
        super(name, volume, unit, id);
    }
}
