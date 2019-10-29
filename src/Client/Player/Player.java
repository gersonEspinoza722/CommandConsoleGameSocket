package Client.Player;

import Client.*;

import java.util.Observable;

public class Player extends Client {

    private String name;
    private Observable subscribedGame;

    public Player(String hostName, int portNumber, ClientType clientType) {
        super(hostName, portNumber, clientType);
    }
}
