package Client.Resources;

import Client.ClientType;
import Client.Command.PlayerAttackCommand;
import Client.Game.*;
import Client.IServerMessageHandler;
import Client.Message;
import Client.Player.Player;
import Client.Player.PlayerMessage;
import Client.Player.ServerMessageHandlerPlayer;
import Server.ClientMessageHandler;
import Server.GameServer;
import Server.ServerThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class PlayerTest {

    public static void main (String [ ] args) {

        GameClient gameClient = new GameClient("localhost",9090, ClientType.OBSERVABLE,"GAME");
        IServerMessageHandler serverMessageHandlerGame = new ServerMessageHandlerGame(null);
        gameClient.setServerMessageHandler(serverMessageHandlerGame);
        gameClient.run();

        Message registrationMessageGame = new GameMessage("GAME","GAME_REGISTRATION","TestGame");
        gameClient.sendMessage(registrationMessageGame);

        Player player = new Player("localhost",9090, ClientType.OBSERVER,"PLAYER");
        IServerMessageHandler serverMessageHandler = new ServerMessageHandlerPlayer(null);
        player.setServerMessageHandler(serverMessageHandler);
        player.run();

        Message registrationMessage = new PlayerMessage("PLAYER","PLAYER_REGISTRATION",player.getName(),player.getId());
        player.sendMessage(registrationMessage);

        //Message subscribeMessage = new PlayerMessage("PLAYER","ENTER_GAME",gameClient.getName(),player.getId());
        //player.sendMessage(subscribeMessage);




        //PlayerAttackCommand command = new PlayerAttackCommand(0, "GAME","PLAYER", null, null);
        //Message attackMessage = new PlayerMessage("PLAYER" , "ATTACK_MESSAGE",command,player.getId());
        //player.sendMessage(attackMessage);
    }
}
