package Networking;

import java.io.*;
import java.net.Socket;

/**
 * Created by jacob on 16/05/2017.
 */
public class CommsThread extends Thread {

    private BufferedWriter outStream;
    private BufferedReader inStream;
    private Comms comms;
    private Boolean setup = true;

    public CommsThread(Comms comms, Socket socket) {
        try {
            outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error in client bind thread");
            setup = false;
        }
        this.comms = comms;
    }

    public Boolean isSetup(){
        return setup;
    }

    @Override
    public void run(){
        while(true){

        }
    }
}
