package Client.Player;

import Client.*;

public class PlayerMessage extends Message {
    private int clientID;
    public PlayerMessage(String sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

    public PlayerMessage(String sentBy, String event, Object objectOfInterest, int clientID) {
        super(sentBy, event, objectOfInterest);
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

}
