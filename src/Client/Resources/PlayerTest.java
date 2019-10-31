package Client.Resources;

import Client.Command.PlayerAttackCommand;
import Client.Game.DamageTable;
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

        Player player = new Player("localhost",9090, null);
        IServerMessageHandler serverMessageHandler = new ServerMessageHandlerPlayer(null);
        player.setServerMessageHandler(serverMessageHandler);
        player.run();

        Message registrationMessage = new PlayerMessage("PLAYER","REGISTRATION","Test",0);
        player.sendMessage(registrationMessage);

        PlayerAttackCommand command = new PlayerAttackCommand(0, "Player 1", null, null);
        PlayerMessage message = new PlayerMessage("PLAYER" , "ATTACK_MESSAGE", command);



        player.sendMessage(message);
    }
}
