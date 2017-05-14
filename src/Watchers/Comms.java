package Watchers;

import Food.Ingredient;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by jacob on 14/05/2017.
 */
public class Comms {

    private StockWatcher stockWatcher;
    private LinkedList<Message> messages;

    public Comms(StockWatcher stockWatcher){
        this.stockWatcher = stockWatcher;
    }

    private class Message implements Serializable {

        public HashMap<Ingredient, Integer> cart;
        public Integer CustomerID;

        public Message(HashMap<Ingredient, Integer> cart, Integer customerID) {
            this.cart = cart;
            CustomerID = customerID;
        }
    }

    public void addMessageToQueue(Message message){
        messages.offer(message);
    }


}
