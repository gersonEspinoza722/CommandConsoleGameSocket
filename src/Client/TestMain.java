package Client;

import Client.Command.PlayerAttackCommand;
import Client.Game.DamageTable;
import Client.Player.Player;
import Client.Player.PlayerMessage;
import Client.Resources.Skill;
import Server.ClientMessageHandler;
import Server.GameServer;
import Server.NotificationHandler;
import Server.ServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class TestMain {
    public static void main (String [ ] args) {
        //Random random = new Random(System.currentTimeMillis());
        //DamageTable generator = new DamageTable(Skill.ACID, random);
        //System.out.println(generator.toString());


        ClientMessageHandler clientMesaggeHandler = new ClientMessageHandler(null);
        NotificationHandler notificationHandler = new NotificationHandler();
        GameServer server = new GameServer(9090, clientMesaggeHandler,notificationHandler);
        ArrayList<Observable> games = new ArrayList<>();
        server.setObservableResources(games);
        server.run();
    }
}
