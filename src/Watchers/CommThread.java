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

    public CommThread(Socket socket, int connectionNum) throws IOException {
        this.connectionNum = connectionNum;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void run(){
        while(true){
            //TODO: Resume from here
        }
    }

}
