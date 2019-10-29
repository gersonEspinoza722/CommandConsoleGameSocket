package Client;

import java.io.Serializable;

public class GameNotification extends Message implements Serializable {

    public GameNotification(String sentBy, String event, Object objectOfInterest) {
        super(sentBy, event, objectOfInterest);
    }

}