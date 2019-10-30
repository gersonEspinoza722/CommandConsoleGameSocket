package Client.Player;

import Client.*;

public class PlayerMessage extends Message {
    int clientId;

    public PlayerMessage(String sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

    public PlayerMessage(String sentBy, String event, Object objectOfInterest, String name) {
        super(sentBy, event, objectOfInterest);

    }

    public int getClientID() {
        return clientId;
    }

    public void setClientID(String clientID) {
        this.clientId = clientId;
    }

}
