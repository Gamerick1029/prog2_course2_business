package Watchers;

import java.io.*;
import java.net.Socket;

/**
 * Created by jacob on 13/05/2017.
 */
class CommThread extends Thread {

    private BufferedReader in;
    private BufferedWriter out;
    private int connectionNum;
    private Comms parent;

    public CommThread(Socket socket, int connectionNum, Comms parent) throws IOException {
        this.connectionNum = connectionNum;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.parent = parent;
    }

    public void run(){
        while(true){
            //TODO: Resume from here
        }
    }

}
