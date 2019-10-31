package Client.Game;

import Client.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient extends Client {

    private String name;

    public GameClient(String hostName, int portNumber, ClientType clientType,String name) {
        super(hostName, portNumber, clientType);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(Message message) {
        try {
            this.writer.writeObject(message);
        }
        catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


