package Server;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.IBoardElement;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Command.PlayerSurrenderCommand;
import Client.Game.Game;
import Client.Message;
import Client.Player.PlayerMessage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientMessageHandler implements IClientMessageHandler{

    JFrame serverLogWindow;

    public ClientMessageHandler(JFrame serverLogWindow) {
        this.serverLogWindow = serverLogWindow;
    }

    public void handlePlayerMessage(Message message, Server server) {
        String event = message.getEvent();

        switch (event) {
            case "REGISTRATION": {
                PlayerMessage playerMessage = (PlayerMessage) message;

                String playerName = (String) playerMessage.getObjectOfInterest();
                int clientID = playerMessage.getClientID();

                GameServer gameServer = (GameServer) server; //GameServer es SocialNetServer
                ServerThread currentServerThread = server.getClients().get(clientID);
                gameServer.addNewPlayer(clientID,currentServerThread);

                //ServerThread currentThread = server.getClients().get(clientID);
                //ArrayList<Observable> games = server.getObservableResources();
                //Message observablesMessage = new ServerMessage("SERVER", "SENT_GAMES_LIST", games);
                //try {
                  //  currentThread.getWriter().writeObject(observablesMessage);
                //} catch (IOException ex) {
                  //  Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                //}

            }
            break;

                    /*
            case "ENTER_GAME": { // era FOLLOW_ARTIST
                PlayerMessage playerMessage = (FanMessage) message;
                int clientID = fanMessage.getClientID();
                ServerThread currentThread = server.getClients().get(clientID);

                Artist followedArtist = (Artist) message.getObjectOfInterest();
                Artist realArtist = getArtist(followedArtist.getName(), server);
                realArtist.addFollower(currentThread); // Use this to validate and notify

                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread artistThread = networkServer.getArtistClients().get(realArtist.getName());

                Message newFollowerMessage = new ServerMessage("SERVER", "NEW_FOLLOWER", realArtist);
                try {
                    artistThread.getWriter().reset();
                    artistThread.getWriter().writeObject(newFollowerMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;*/

            case "ATTACK_MESSAGE": {

                System.out.println("Entró a ataque");

                PlayerMessage playerMessage = (PlayerMessage) message;


                ICommand attack = (PlayerAttackCommand) playerMessage.getObjectOfInterest();




                //Game realGame = (Game) getGame(attack.getGameId(), server);
                //ICharacterListing targets = realGame.getPlayer(((PlayerAttackCommand)attack).getClientToAttackName()).getCharacters();
                //ArrayList<ICharacter> targetsList = targets.getCharacterList();


                //realGame.attack(attack);







/*NO BORRAR, TALVEZ SIRVE, T A L V E Z
                GameServer gameServer = (GameServer) server;
                ServerThread gameThread = gameServer.getGames().get(attack.getGameId());
                Message attackMessage = new ServerMessage("SERVER", "ATTACK_MESSAGE", realGame);//MAdraríamos al cliente Game el juego completo
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(attackMessage); //Mandé el juego completo para ponerlo talvez en una pantalla de Game
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
            break;

            case "SURRENDER_MESSAGE": {
                PlayerMessage playerMessage = (PlayerMessage) message;
                int clientID = playerMessage.getClientID();
                ICommand surrender = (PlayerSurrenderCommand) playerMessage.getObjectOfInterest();
                Game realGame = (Game) getGame(surrender.getGameId(), server);
                realGame.surrender(surrender);

                //NO BORRAR, TALVEZ SIRVE, T A L V E Z
                //ArtistPost unlikedPost = (ArtistPost) fanMessage.getObjectOfInterest();
                //Artist realArtist = getArtist(unlikedPost.getArtistName(), server);
                //realArtist.addDislikeToPost(unlikedPost.getId());

                //ArtistPost realPost = realArtist.getPostByID(unlikedPost.getId());
                //SocialNetworkServer networkServer = (SocialNetworkServer) server;
                //ServerThread artistThread = networkServer.getArtistClients().get(realArtist.getName());
                //Message unlikedMessage = new ServerMessage("SERVER", "UNLIKED_MESSAGE", realPost);
                //try {
                  //  artistThread.getWriter().reset();
                    //artistThread.getWriter().writeObject(unlikedMessage);
                //} catch (IOException ex) {
                  //  Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                //}

            }
            break;
/*
            case "UNFOLLOW_ARTIST": {
                FanMessage fanMessage = (FanMessage) message;
                int clientID = fanMessage.getClientID();
                ServerThread currentThread = server.getClients().get(clientID);

                Artist unfollowedArtist = (Artist) message.getObjectOfInterest();
                Artist realArtist = getArtist(unfollowedArtist.getName(), server);
                realArtist.removeFollower(currentThread);

                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread artistThread = networkServer.getArtistClients().get(realArtist.getName());

                Message newFollowerMessage = new ServerMessage("SERVER", "NEW_UNFOLLOWER", realArtist);
                try {
                    artistThread.getWriter().reset();
                    artistThread.getWriter().writeObject(newFollowerMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

             */
        }
    }
    public void handleArtistMessage(Message message, Server server) {
        String event = message.getEvent();
        switch (event) {
            case "REGISTRATION": {
                /*
                ArtistMessage artistMessage = (ArtistMessage) message;
                int artistClientID = artistMessage.getArtistID();
                ArrayList<Observable> artists = server.getObservableResources();
                String artistName = (String) message.getObjectOfInterest();
                // Create an Artist based on the information I just received
                int artistID = artists.size();
                Artist newArtist = new Artist(artistID, artistName);

                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread currentServerThread = server.getClients().get(artistClientID);
                networkServer.addNewArtist(newArtist , artistName , currentServerThread);
¨*/
            }
            break;

            case "NEW_MESSAGE": {
                /*
                ArtistPost newPost = (ArtistPost) message.getObjectOfInterest();
                Artist artist = getArtist(newPost.getArtistName(), server);
                artist.addPost(newPost);

                 */
            }
            break;

            case "LEAVE": {
            }
            break;
        }

    }
    public Observable getGame(int gameIdentifier, Server server) {
        ArrayList<Observable> games = server.getObservableResources();
        Observable result = null;
        for (Observable game : games) {//games era artists
            Observable currentGame = (Game) game; //current game era current artist
            if (((Game)currentGame).getIdentifier()==gameIdentifier) { //getIdentifier() era getName()
                result =  currentGame;
            }

        }
        return result;
        //return null;//QUITAR ESO
    }
    @Override
    public void handleClientMessage(Message message, Server server) {
        System.out.println(message.getEvent());
        if (message.sentBy().equals("PLAYER")) { //PLAYER era FAN
            handlePlayerMessage(message, server);
        } else {
            handleArtistMessage(message, server);
        }
    }

}