package Server;

import Client.ClientThread;
import Client.Game.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameServer extends Server{
    private HashMap<String,ServerThread> games;

    public GameServer(int portnumber, IClientMessageHandler clientMessageHandler, INotificationHandler notificationHandler) {
        super(portnumber, clientMessageHandler, notificationHandler);
        this.clients = new HashMap<>();
        this.observableResources = new ArrayList<>();
        this.games = new HashMap<>();
    }


    public HashMap<String, ServerThread> getGames() {
        return games;
    }

    public void addNewPlayer(int clientID, ServerThread thread){
        this.clients.put(clientID, thread);
    }

    public void addNewGame(Game game, String gameName,ServerThread gameThread){
        this.observableResources.add(game);
        //System.out.println("Lo metió en observables");
       for (ServerThread currentServerThread : clients.values()){
            currentServerThread.update(game, game);
        }
        this.games.put(gameName, gameThread);
    }
    public void setArtistClients(HashMap<String, ServerThread> games) {
        this.games = games;
    }





    public void manageClientSubscription(String username, int artistID, String action) {
        ServerThread client = clients.get(username);
        Game game = (Game) observableResources.get(artistID);

        if (action.equals("PLAY")) { //era SUBSCRIBE
            //game.addFollower(client);
        } else {
            //game.removeFollower(client);
        }
    }

}
