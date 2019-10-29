package Client.Game;

import Client.Message;

public class GameMessage extends Message {
    private int gameID;

    public GameMessage(String sentBy, String event, Object objectOfInterest, int gameID) {
        super(sentBy, event, objectOfInterest);
        this.gameID=gameID;
    }
}
