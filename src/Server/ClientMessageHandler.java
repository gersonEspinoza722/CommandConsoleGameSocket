package Server;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.IBoardElement;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Command.PlayerSurrenderCommand;
import Client.Game.Game;
import Client.Game.GameMessage;
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
            case "PLAYER_REGISTRATION": {
                PlayerMessage playerMessage = (PlayerMessage) message;
                int clientID = playerMessage.getClientID();

                GameServer gameServer = (GameServer) server; //GameServer es SocialNetServer
                ServerThread currentServerThread = server.getClients().get(clientID);
                gameServer.addNewPlayer(clientID,currentServerThread);

                ServerThread currentThread = server.getClients().get(clientID);
                ArrayList<Observable> games = server.getObservableResources();
                Message observablesMessage = new ServerMessage("SERVER", "SENT_GAMES_LIST", games);
                try {
                  currentThread.getWriter().writeObject(observablesMessage);
                } catch (IOException ex) {
                  Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;
            


            case "ENTER_GAME": {
                PlayerMessage playerMessage = (PlayerMessage) message;
                int clientID = playerMessage.getClientID();
                ServerThread currentThread = server.getClients().get(clientID);

                String followedGame = (String) message.getObjectOfInterest();
                Game realGame = getGame(followedGame, server);
                realGame.addPlayer(currentThread); // Use this to validate and notify

                GameServer gameServer = (GameServer) server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());

                Message newFollowerMessage = new ServerMessage("SERVER", "NEW_PLAYER", realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(newFollowerMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;

            case "ATTACK_MESSAGE": {
                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerAttackCommand)playerMessage.getObjectOfInterest()).getName();

                Game realGame = getGame(gameName,server);
                realGame.attack((ICommand) playerMessage.getObjectOfInterest());
                System.out.println("Soy el juego:"+realGame.getName());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                System.out.println("Soy el thread:¨"+gameThread.getID());

                Message newAttackMesagge = new ServerMessage("SERVER","NEW_ATTACK_MESSAGE",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(newAttackMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                //ICommand attack = (PlayerAttackCommand) playerMessage.getObjectOfInterest();


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
                //PlayerMessage playerMessage = (PlayerMessage) message;
                //int clientID = playerMessage.getClientID();
                //ICommand surrender = (PlayerSurrenderCommand) playerMessage.getObjectOfInterest();
                //Game realGame = (Game) getGame(surrender.getGameId(), server);
                //realGame.surrender(surrender);

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
    public void handleGameMessage(Message message, Server server) {
        String event = message.getEvent();
        switch (event) {
            case "GAME_REGISTRATION": {
                GameMessage gameMessage = (GameMessage) message;
                int gameClientID = gameMessage.getGameID();
                ArrayList<Observable> games = server.getObservableResources();
                // Create an Artist based on the information I just received
                String gameName = (String) message.getObjectOfInterest();
                int gameId = games.size();
                Game newGame = new Game(gameId,gameName);

                GameServer gameServer = (GameServer) server;
                ServerThread currentServerThread = server.getClients().get(gameClientID);
                gameServer.addNewGame(newGame,gameName,currentServerThread);

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
    public Game getGame(String gameName, Server server) {
        ArrayList<Observable> games = server.getObservableResources();
        Game result = null;
        for (Observable game : games) {//games era artists
            Game currentGame = (Game) game; //current game era current artist
            if (currentGame.getName().equals(gameName)) { //getIdentifier() era getName()
                result =  currentGame;
            }
        }
        return result;
    }
    @Override
    public void handleClientMessage(Message message, Server server) {
        System.out.println(message.getEvent());
        if (message.sentBy().equals("PLAYER")) { //PLAYER era FAN
            handlePlayerMessage(message, server);
        } else {
            handleGameMessage(message, server);
        }
    }

}