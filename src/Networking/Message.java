package Networking;

import java.io.Serializable;

/**
 * Created by jacob on 16/05/2017.
 */
public class Message implements Serializable {

    public enum MessageType{
        LOGIN, CREATEUSER, UPDATEINTERFACE, ORDER
    }

    public MessageType messageType;
    public String messageContent;

    public Message(MessageType messageType, String messageContent){
        this.messageType = messageType;
        this.messageContent = messageContent;
    }

}
