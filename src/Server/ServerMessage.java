package Server;

import Client.Message;

public class ServerMessage extends Message {

    public ServerMessage(String sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

}