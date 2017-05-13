package Watchers;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by jacob on 13/05/2017.
 */
public class Comms {

    private int connectionNum = 0;
    private ServerSocket serverSocket;

    public Comms(int portNumber){
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Could not bind to port" + portNumber);
            System.exit(-1);
        }

        while (true) {
            try {
                new CommThread(serverSocket.accept(), connectionNum).start();
                connectionNum++;
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not create connection number" + connectionNum);
            }
        }

    }

}
