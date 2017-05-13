package Food;

import java.util.HashMap;

/**
 * Created by jacob on 11/05/2017.
 */
public class SushiDish {

    private String name;
    private String description;
    private Double price;
    private HashMap<Ingredient, Integer> recipe; //Mapping ingredients to quantities
    private Integer restockLevel;

    /**
     * The constructor for the SushiDish class
     * @param name The name of the dish
     * @param description The description of the dish
     * @param price The price of the dish
     * @param recipe A HashMap mapping Ingredients to the required quantity for this recipe
     * @param restockLevel The minimum preferred stock level of this dish
     */
    public SushiDish(String name, String description, Double price, HashMap<Ingredient, Integer> recipe, Integer restockLevel) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.recipe = recipe;
        this.restockLevel = restockLevel;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    /**
     * Returns a clone of this Objects "recipe" instance
     * @return A HashMap<Ingredient, Integer> clone of this Objects recipe
     */
    public HashMap<Ingredient, Integer> getRecipe() {
        return (HashMap<Ingredient, Integer>) recipe.clone();
    }

    public Integer getRestockLevel() {
        return restockLevel;
    }
}
