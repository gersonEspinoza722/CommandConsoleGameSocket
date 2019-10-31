package Client;

import Client.Command.PlayerAttackCommand;
import Client.Game.DamageTable;
import Client.Player.Player;
import Client.Player.PlayerMessage;
import Client.Resources.Skill;
import Server.ClientMessageHandler;
import Server.GameServer;
import Server.ServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class TestMain {
    public static void main (String [ ] args) {
        Random random = new Random(System.currentTimeMillis());
        DamageTable generator = new DamageTable(Skill.ACID, random);
        System.out.println(generator.toString());


        ClientMessageHandler clientMesaggeHandler = new ClientMessageHandler(null);
        GameServer server = new GameServer(9090, clientMesaggeHandler,null);

        server.run();



        Player player = new Player("localhost",9080, null);
        Socket socket;

        socket=null;
        try {

            socket = new Socket("localhost", 9090);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ServerThread serverThread = new ServerThread(socket,1, server);
        server.addNewPlayer(0,serverThread);

        player.run();


        PlayerAttackCommand command = new PlayerAttackCommand(1, "Player 1", null, null);
        PlayerMessage message = new PlayerMessage("client" , "ATTACK_MESSAGE", command);



        player.sendMessage(message);
    }
}
