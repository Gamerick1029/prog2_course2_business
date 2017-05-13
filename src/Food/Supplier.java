package Food;

import java.util.ArrayList;

/**
 * Created by jacob on 11/05/2017.
 */
public class Supplier {

    private String name;
    private ArrayList<Ingredient> inventory;
    private Integer distance;                   //Distance to the Sushi place in km

    /**
     * Constructor for the Supplier class
     * @param name Name of the supplier
     * @param distance The distance from the supplier to the restaurant in km
     */
    public Supplier(String name, Integer distance) {
        this.name = name;
        this.distance = distance;
        inventory = new ArrayList<Ingredient>();
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public ArrayList<Ingredient> getInventory() {
        return inventory;
    }

    /**
     * Adds a new ingredient to the Suppliers inventory
     * @param ingredient Ingredient Object to add
     * @return
     */
    public Boolean addIngredientToInventory(Ingredient ingredient){
        if (inventory.contains(ingredient)) {
            return false;
        } else {
            inventory.add(ingredient);
            return true;
        }
    }

    /**
     * Remove an ingredient from the Suppliers inventory. Consider using the overloaded method that takes the ingredient name as a String.
     * @param ingredient The ingredient object to remove
     */
    public void removeIngredientFromInventory(Ingredient ingredient){
        inventory.remove(ingredient);
    }

    /**
     * Removes an ingredient from the Suppliers inventory
     * @param ingredientName The name of the ingredient to remove
     */
    public void removeIngredientFromInventory(String ingredientName){
        for (Ingredient ingredient:inventory){
            if (ingredient.getName().equals(ingredientName)) inventory.remove(ingredient);
        }
    }
}
