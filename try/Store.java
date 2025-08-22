package try;

import java.util.HashMap;

public class Store {
    public StockIngredients ingredients;
    public StockItems items;

    public Store () {
        this.ingredients = new StockIngredients();
        this.items = new StockItems();
    }
    public HashMap<Ingredient, Integer> getIngredientStock() {
        return ingredients.getItems();
    }

    public HashMap<StarbucksItem, Integer> getItemStock() {
        return items.getItems();
    }
}
