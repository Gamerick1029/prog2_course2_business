package Watchers;

import Food.Ingredient;
import Food.SushiDish;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jacob on 11/05/2017.
 */
public class StockWatcher {

    private HashMap<Ingredient, Integer> ingredientInventory;
    private HashMap<SushiDish, Integer> sushiDishInventory;
    private HashSet<Ingredient> ingredientsToRestock;
    private HashSet<Ingredient> ingredientsBeingRestocked;
    private HashSet<SushiDish> dishesToRestock;
    private HashSet<SushiDish> dishesBeingRestocked;

    private Object ingredientLock = new Object();
    private Object ingredientStockLock = new Object();
    private Object dishLock = new Object();
    private Object dishStockLock = new Object();


    /**
     * The constructor for the StockWatcher class. Throws an error if either parameter is empty
     * @param ingredientInventory The list of ingredients that the restaurant keeps in stock. Cannot be empty
     * @param sushiDishInventory The list of Dishes the restaurant makes. Cannot be empty
     */
    public StockWatcher(HashMap<Ingredient, Integer> ingredientInventory, HashMap<SushiDish, Integer> sushiDishInventory) throws Throwable {
        if (ingredientInventory.isEmpty()) throw new Throwable("Ingredient list was empty at runtime");
        if (sushiDishInventory.isEmpty()) throw new Throwable("Dish list was empty at runtime");

        this.ingredientInventory = ingredientInventory;
        this.sushiDishInventory = sushiDishInventory;

        ingredientsToRestock = new HashSet<>();
        ingredientsBeingRestocked = new HashSet<>();
        dishesToRestock = new HashSet<>();
        dishesBeingRestocked = new HashSet<>();
    }

    /**
     * Checks a list of ingredients and pushes out a restock request for any ingredients below the restock level if such a request is not already being handled
     * @param ingredients The list of ingredients to check
     */
    public void checkIngredientStock (List<Ingredient> ingredients){
        for (Ingredient ingredient:ingredients) {
            if (!ingredientsBeingRestocked.contains(ingredient)) {
                if (ingredientInventory.get(ingredient) < ingredient.getRestockLevel()) {
                    ingredientsToRestock.add(ingredient);
                }
            }
        }
    }

    /**
     * Checks an ingredient and pushes out a restock request if below the restock level if such a request is not already being handled
     * @param ingredient The ingredient to check
     */
    public void checkIngredientStock (Ingredient ingredient){
        if(!ingredientsBeingRestocked.contains(ingredient)) {
            if (ingredientInventory.get(ingredient) < ingredient.getRestockLevel()) {
                ingredientsToRestock.add(ingredient);
            }
        }
    }

    /**
     * Checks a list of SushiDishes and pushes out a restock request for any dishes below the restock level if such a request is not already being handled
     * @param dishes The list of dishes to check
     */
    public void checkDishStock (List<SushiDish> dishes){
        for (SushiDish dish:dishes) {
            if (!dishesBeingRestocked.contains(dish)) {
                if (sushiDishInventory.get(dish) < dish.getRestockLevel()) {
                    dishesToRestock.add(dish);
                }
            }
        }
    }

    /**
     * Checks a SushiDish and pushes out a restock request if below the restock level if such a request is not already being handled
     * @param sushiDish The dish to check
     */
    public void checkDishStock (SushiDish sushiDish){
        if (!dishesBeingRestocked.contains(sushiDish)) {
            if (sushiDishInventory.get(sushiDish) < sushiDish.getRestockLevel()) {
                dishesToRestock.add(sushiDish);
            }
        }
    }

    /**
     * Gets the next Dish that has been marked for restock, if it exists. Returns null if all dishes are well stocked
     * @return The Dish to restock
     */
    public SushiDish getDishRestockIfAvailable(){
        synchronized (dishLock){
            for (SushiDish sushiDish:dishesToRestock){
                if (dishCanBeMade(sushiDish)){
                    dishesToRestock.remove(sushiDish);
                    dishesBeingRestocked.add(sushiDish);
                   for (Ingredient ingredient:sushiDish.getRecipe().keySet()){
                        depleteIngredientStock(ingredient, sushiDish.getRecipe().get(ingredient));
                   }
                return sushiDish;
                }
            }
            return null;
        //TODO Optimise synchronization. In other words, find a way to not synchronize the entire method
        }
    }

    /**
     * Gets the next Ingredient that has been marked for restock, if it exists. Returns null if all ingredients are well stocked
     * @return The ingredient to restock
     */
    public Ingredient getIngredientRestockIfAvailable(){
        synchronized (ingredientLock) {
            if (!ingredientsToRestock.isEmpty()) {
                Ingredient ingredient = ingredientsToRestock.iterator().next();
                ingredientsToRestock.remove(ingredient);
                ingredientsBeingRestocked.add(ingredient);
                return ingredient;
            }
            return null;
        }
    }

    /**
     * Depletes the stock of the supplied Ingredient by the supplied integer amount
     * @param ingredient
     * @param n
     */
    public void depleteIngredientStock(Ingredient ingredient, int n){
        synchronized (ingredientStockLock) {
            int newStock = ingredientInventory.get(ingredient) - n;
            ingredientInventory.put(ingredient, newStock);
        }
    }

    /**
     * Increases the stock of the supplied Ingredient by the specified amount, then removes said Ingredient from the restocking list
     * @param ingredient
     * @param n
     */
    public void increaseIngredientStock(Ingredient ingredient, int n){
        synchronized (ingredientStockLock) {
            int newStock = ingredientInventory.get(ingredient) + n;
            ingredientInventory.put(ingredient, newStock);
        }
        ingredientsBeingRestocked.remove(ingredient);
    }


    /**
     * Depletes the stock of the supplied Dish by the supplied integer amount
     * @param sushiDish
     * @param n
     */
    public void depleteDishStock(SushiDish sushiDish, int n){
        synchronized (dishStockLock) {
            int newStock = sushiDishInventory.get(sushiDish) - n;
            sushiDishInventory.put(sushiDish, newStock);
        }
    }

    /**
     * Increases the stock of the supplied Dish by the specified amount, then removes said Dish from the restocking list
     * @param sushiDish
     * @param n
     */
    public void increaseDishStock(SushiDish sushiDish, int n){
        synchronized (dishStockLock) {
            int newStock = sushiDishInventory.get(sushiDish) + n;
            sushiDishInventory.put(sushiDish, newStock);
        }
        dishesBeingRestocked.remove(sushiDish);
    }

    /**
     * Checks to see if the supplied SushiDish can be made with the ingredients currently in stock
     * @param sushiDish The dish to check
     * @return True iff there exists enough ingredients to make the dish
     */
    private Boolean dishCanBeMade(SushiDish sushiDish){
        HashMap<Ingredient, Integer> recipe = sushiDish.getRecipe();
        for (Ingredient ingredient: recipe.keySet()){
            if (recipe.get(ingredient) < ingredientInventory.get(ingredient)) return false;
        }
        return true;
    }



}
