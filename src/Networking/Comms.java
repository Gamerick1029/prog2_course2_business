package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jacob on 15/05/2017.
 */
public class Comms extends Thread {

    private HashMap<Integer, Byte[]> passwords;   //Maps ID to password hash
    private ServerSocket serverSocket;
    private Boolean running = true;
    private ArrayList<Thread> connections;

    public Comms(int port, HashMap<Integer, Byte[]> passwords){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't bind application to port " + port);
            System.exit(-1);
        }

        connections = new ArrayList<>();
        this.passwords = passwords;
    }

    @Override
    public void run(){
        while (running){
            try {
                CommsThread CT = new CommsThread(this, serverSocket.accept());
                if (CT.isSetup()) {
                    ;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to listen on port " + serverSocket.getLocalPort());
            }
        }

        //TODO Shutdown Threads, save User Profiles
    }

    public void setRunning(Boolean b){
        running = b;
    }

}
