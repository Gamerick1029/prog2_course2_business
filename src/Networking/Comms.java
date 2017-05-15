package Networking;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by jacob on 15/05/2017.
 */
public class Comms extends Thread {

    private HashMap<Integer, Byte[]> passwords;   //Maps ID to password hash
    private HashMap<Integer, Socket> connections; //Maps ID to socket

    public Comms(){

    }

}
