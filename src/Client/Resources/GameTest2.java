package Client.Resources;

import Client.ClientType;
import Client.Game.GameClient;
import Client.Game.GameMessage;
import Client.Game.ServerMessageHandlerGame;
import Client.IServerMessageHandler;
import Client.Message;

public class GameTest2<serverMessageHandlerGame, registrationMessageGame> {
    public static void main (String [ ] args) {

        GameClient gameClient = new GameClient("localhost", 9090, ClientType.OBSERVABLE, "GAME2");
        IServerMessageHandler serverMessageHandlerGame = new ServerMessageHandlerGame(null);
        gameClient.setServerMessageHandler(serverMessageHandlerGame);
        gameClient.run();

        Message registrationMessageGame = new GameMessage("GAME", "GAME_REGISTRATION", "GAME2");
        gameClient.sendMessage(registrationMessageGame);
    }
}
