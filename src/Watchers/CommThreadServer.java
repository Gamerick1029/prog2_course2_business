package Watchers;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by jacob on 13/05/2017.
 */
public class CommThreadServer implements Runnable {

    private int connectionNum = 0;
    private ServerSocket serverSocket;
    private Comms parent;

    CommThreadServer(int portNumber, Comms parent){
        this.parent = parent;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Could not bind to port" + portNumber);
            System.exit(-1);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                new CommThread(serverSocket.accept(), connectionNum++, this).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not create connection number " + connectionNum);
            }
        }
    }


}
