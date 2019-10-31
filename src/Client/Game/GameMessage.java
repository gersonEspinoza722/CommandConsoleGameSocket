package Client.Game;

import Client.Message;

public class GameMessage extends Message {
    private int gameID;

    public GameMessage(String sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
