package Client.Game;

import Client.*;

import java.io.Serializable;

public class GameNotification extends Message implements Serializable {

    public GameNotification(int sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

}