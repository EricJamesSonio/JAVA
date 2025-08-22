package try;

public class SubCategory {
    private String name;
    public SubCategory (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class EggSandwich extends SubCategory {
    public EggSandwich (String name) {
        super(name);
    }
}

class MilkTea extends SubCategory {
    public MilkTea (String name) {
        super(name);
    }
}
