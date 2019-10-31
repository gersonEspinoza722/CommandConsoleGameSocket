package Server;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.IBoardElement;
import Client.Command.*;
import Client.Game.Game;
import Client.Game.GameProxy;
import Client.Game.Log;
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
                    //estaba current serve, daba nulo
                    currentThread.getWriter().writeObject(observablesMessage);
                } catch (IOException ex) {
                  Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;


            case "ENTER_GAME": {
                PlayerMessage playerMessage = (PlayerMessage) message;
                int clientID = playerMessage.getClientID();
               // System.out.println("Soy el cliente:" +clientID);
                ServerThread currentThread = server.getClients().get(clientID);
                String followedGame = (String) message.getObjectOfInterest();
                Game realGame = getGame(followedGame, server);
                //System.out.println("Quiero entrar al juego:"+realGame.getName());
                realGame.addPlayer(currentThread); // Use this to validate and notify

                GameServer gameServer = (GameServer) server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());

                Message newFollowerMessage = new ServerMessage("SERVER", "NEW_PLAYER", realGame);
                try {
                    //estos current server eran gameServer
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
                //System.out.println("Soy el juego:"+realGame.getName());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
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

                GameProxy gameProxy = new GameProxy(attack);
                gameProxy.attack(attack);

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
                String gameName = ((PlayerSurrenderCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.surrender((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message surrenderMesagge = new ServerMessage("SERVER","SURRENDER_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(surrenderMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "END_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerEndCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.end((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message endMesagge = new ServerMessage("SERVER","END_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(endMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "RELOAD_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerReloadCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.reload((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message reloadMesagge = new ServerMessage("SERVER","RELOAD_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(reloadMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "COMODIN_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerComodinCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.comodin((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message reloadMesagge = new ServerMessage("SERVER","COMODIN_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(reloadMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "INFO_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerGetOtherInfoCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.info((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message infoMesagge = new ServerMessage("SERVER","INFO_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(infoMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "PASS_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerPassCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.pass((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message passMesagge = new ServerMessage("SERVER","PASS_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(passMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "CHAT_MESSAGE": {

                PlayerMessage playerMessage = (PlayerMessage) message;
                String gameName = ((PlayerChatCommand)playerMessage.getObjectOfInterest()).getName();
                Game realGame = getGame(gameName,server);
                realGame.chat((ICommand) playerMessage.getObjectOfInterest());

                GameServer gameServer = (GameServer)  server;
                ServerThread gameThread = gameServer.getGames().get(realGame.getName());
                //System.out.println("Soy el thread:¨"+gameThread.getID());
                Message chatMesagge = new ServerMessage("SERVER","CHAT_MESSAGE_FROM_SERVER",realGame);
                try {
                    gameThread.getWriter().reset();
                    gameThread.getWriter().writeObject(chatMesagge);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
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

            case "SURRENDER_MESSAGE_GAME": {
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
            //System.out.println(currentGame.getName());
            if (currentGame.getName().equals(gameName)) { //getIdentifier() era getName()
                result =  currentGame;
             //   System.out.println("encontro el juego por nombre:" + result.getName());
            }
            //System.out.println("NO encontro el juego por nombre");
            //result =(Game) games.get(0);//PARA PROBAR
        }
        return result;
    }
    @Override
    public void handleClientMessage(Message message, Server server) {
        //System.out.println(message.getEvent());
        if (message.sentBy().equals("PLAYER")) { //PLAYER era FAN
            handlePlayerMessage(message, server);
        } else {
            handleGameMessage(message, server);
        }
    }

}