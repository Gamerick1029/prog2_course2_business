package Food;

/**
 * Created by jacob on 11/05/2017.
 */
public class Ingredient {

    public enum Unit{
        GRAMS, MILLILETRES
    }

    private String name;
    private Unit unit;
    private String supplier;
    private Integer restockLevel;

    /**
     * Constructor for the Ingredient class
     * @param name Name of the ingredient to add
     * @param unit The unit of measurement. (Takes the Public Enum 'Unit' from this class)
     * @param supplier The name of the supplier from which to resupply the ingredient
     * @param restockLevel
     */
    public Ingredient(String name, Unit unit, String supplier, Integer restockLevel){
        this.name = name;
        this.unit = unit;
        this.supplier = supplier;
        this.restockLevel = restockLevel;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getSupplier() {
        return supplier;
    }

    public Integer getRestockLevel() {
        return restockLevel;
    }
}
