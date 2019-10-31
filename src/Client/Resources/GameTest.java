package Client.Resources;

import Client.ClientType;
import Client.Game.GameClient;
import Client.Game.GameMessage;
import Client.Game.ServerMessageHandlerGame;
import Client.IServerMessageHandler;
import Client.Message;

public class GameTest {
    public static void main (String [ ] args) {

        GameClient gameClient = new GameClient("localhost", 9090, ClientType.OBSERVABLE, "GAME");
        IServerMessageHandler serverMessageHandlerGame = new ServerMessageHandlerGame(null);
        gameClient.setServerMessageHandler(serverMessageHandlerGame);
        gameClient.run();

        Message registrationMessageGame = new GameMessage("GAME", "GAME_REGISTRATION", "GAME");
        gameClient.sendMessage(registrationMessageGame);
    }
}
