package Workers;

import Food.SushiDish;
import Watchers.StockWatcher;

import java.util.Random;

/**
 * Created by jacob on 11/05/2017.
 */
public class KitchenStaff implements Runnable {

    private StockWatcher stockWatcher;
    private SushiDish currentDish;
    private Boolean working;

    public KitchenStaff(StockWatcher stockWatcher) {
        this.stockWatcher = stockWatcher;
        working = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(true){
            currentDish = stockWatcher.getDishRestockIfAvailable();
            if (currentDish != null){
                working = true;
                try {
                    Thread.sleep(20 + (1000 * random.nextInt(40))); //Wait 20 + (some number between 0 and 39) seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stockWatcher.increaseDishStock(currentDish, 1);
                working = false;
            }
        }
    }

}
