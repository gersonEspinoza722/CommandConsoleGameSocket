package Client.Resources;

import Client.ClientType;
import Client.Command.*;
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

        //GameClient gameClient = new GameClient("localhost",9090, ClientType.OBSERVABLE,"GAME");
        IServerMessageHandler serverMessageHandlerGame = new ServerMessageHandlerGame(null);
        //gameClient.setServerMessageHandler(serverMessageHandlerGame);
       // gameClient.run();

        //Message registrationMessageGame = new GameMessage("GAME","GAME_REGISTRATION","GAME");
        //gameClient.sendMessage(registrationMessageGame);

        Player player = new Player("localhost",9090, ClientType.OBSERVER,"PLAYER");
        IServerMessageHandler serverMessageHandler = new ServerMessageHandlerPlayer(null);
        player.setServerMessageHandler(serverMessageHandler);
        player.run();

        System.out.println(player.getId());

        Message registrationMessage = new PlayerMessage("PLAYER","PLAYER_REGISTRATION",player.getName(),player.getId());
        player.sendMessage(registrationMessage);

        Message subscribeMessage = new PlayerMessage("PLAYER","ENTER_GAME","GAME",player.getId());
        player.sendMessage(subscribeMessage);

        PlayerAttackCommand command = new PlayerAttackCommand(0, "GAME","PLAYER", "attack warriorName weaponName");
        Message attackMessage = new PlayerMessage("PLAYER" , "ATTACK_MESSAGE",command,player.getId());
        player.sendMessage(attackMessage);

        PlayerSurrenderCommand command2 = new PlayerSurrenderCommand(0, "GAME");
        Message surrenderMessage = new PlayerMessage("PLAYER" , "SURRENDER_MESSAGE",command2,player.getId());
        player.sendMessage(surrenderMessage);

        PlayerEndCommand command3 = new PlayerEndCommand(0, "GAME");
        Message endMessage = new PlayerMessage("PLAYER" , "END_MESSAGE",command3,player.getId());
        player.sendMessage(endMessage);

        PlayerReloadCommand command4 = new PlayerReloadCommand(0, "GAME");
        Message reloadMessage = new PlayerMessage("PLAYER" , "RELOAD_MESSAGE",command4,player.getId());
        player.sendMessage(reloadMessage);

        PlayerComodinCommand command5 = new PlayerComodinCommand(0, "GAME");
        Message comodinMessage = new PlayerMessage("PLAYER" , "COMODIN_MESSAGE",command5,player.getId());
        player.sendMessage(comodinMessage);

        PlayerGetOtherInfoCommand command6 = new PlayerGetOtherInfoCommand(0, "GAME");
        Message getOtherInfoMessage = new PlayerMessage("PLAYER" , "INFO_MESSAGE",command6,player.getId());
        player.sendMessage(getOtherInfoMessage);

        PlayerPassCommand command7 = new PlayerPassCommand(0, "GAME");
        Message passMessage = new PlayerMessage("PLAYER" , "PASS_MESSAGE",command7,player.getId());
        player.sendMessage(passMessage);

        PlayerChatCommand command8 = new PlayerChatCommand(0, "GAME","Mensaje para chat");
        Message chatMessage = new PlayerMessage("PLAYER" , "CHAT_MESSAGE",command8,player.getId());
        player.sendMessage(chatMessage);

    }
}
