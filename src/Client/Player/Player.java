package Client.Player;

import BoardElement.Character.ICharacterListing;
import Client.*;

import java.util.Observable;

public class Player extends Client {

    private String name;
    private Observable subscribedGame;
    private int score;
    private int totalScore;

    public Player(String hostName, int portNumber, ClientType clientType) {
        super(hostName, portNumber, clientType);
    }
}
