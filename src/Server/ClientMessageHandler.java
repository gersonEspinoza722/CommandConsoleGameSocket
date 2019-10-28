package Server;

import Client.Message;

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

    public void handleFanMessage(Message message, Server server) {
        String event = message.getEvent();

        switch (event) {
            case "REGISTRATION": {
                FanMessage fanMessage = (FanMessage) message;

                String fanName = (String) fanMessage.getObjectOfInterest();
                int clientID = fanMessage.getClientID();

                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread currentServerThread = server.getClients().get(clientID);
                networkServer.addNewFan(clientID,currentServerThread);

                ServerThread currentThread = server.getClients().get(clientID);
                ArrayList<Observable> artists = server.getObservableResources();
                Message observablesMessage = new ServerMessage("SERVER", "SENT_ARTIST_LIST", artists);
                try {
                    currentThread.getWriter().writeObject(observablesMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;

            case "FOLLOW_ARTIST": {
                FanMessage fanMessage = (FanMessage) message;
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
            break;

            case "LIKE_MESSAGE": {
                FanMessage fanMessage = (FanMessage) message;
                int clientID = fanMessage.getClientID();
                ArtistPost likedPost = (ArtistPost) fanMessage.getObjectOfInterest();
                Artist realArtist = getArtist(likedPost.getArtistName(), server);
                realArtist.addLikeToPost(likedPost.getId());

                ArtistPost realPost = realArtist.getPostByID(likedPost.getId());
                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread artistThread = networkServer.getArtistClients().get(realArtist.getName());
                Message likedMessage = new ServerMessage("SERVER", "LIKED_MESSAGE", realPost);
                try {
                    artistThread.getWriter().reset();
                    artistThread.getWriter().writeObject(likedMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "DISLIKE_MESSAGE": {
                FanMessage fanMessage = (FanMessage) message;
                int clientID = fanMessage.getClientID();
                ArtistPost unlikedPost = (ArtistPost) fanMessage.getObjectOfInterest();
                Artist realArtist = getArtist(unlikedPost.getArtistName(), server);
                realArtist.addDislikeToPost(unlikedPost.getId());

                ArtistPost realPost = realArtist.getPostByID(unlikedPost.getId());
                SocialNetworkServer networkServer = (SocialNetworkServer) server;
                ServerThread artistThread = networkServer.getArtistClients().get(realArtist.getName());
                Message unlikedMessage = new ServerMessage("SERVER", "UNLIKED_MESSAGE", realPost);
                try {
                    artistThread.getWriter().reset();
                    artistThread.getWriter().writeObject(unlikedMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;

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
        }
    }
    public void handleArtistMessage(Message message, Server server) {
        String event = message.getEvent();
        switch (event) {
            case "REGISTRATION": {
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

            }
            break;

            case "NEW_MESSAGE": {
                ArtistPost newPost = (ArtistPost) message.getObjectOfInterest();
                Artist artist = getArtist(newPost.getArtistName(), server);
                artist.addPost(newPost);
            }
            break;

            case "LEAVE": {
            }
            break;
        }

    }
    public Observable getGame(String gameIdentifier, Server server) {
        ArrayList<Observable> artists = server.getObservableResources();
        Observable result = null;
        for (Observable game : games) {//games era artists
            Observable currentGame = (Game) game; //current game era current artist
            if (currentGame.getIdentifier().equals(gameIdentifier)) { //getIdentifier() era getName()
                result = currentGame;
            }
        }
        return result;
    }
    @Override
    public void handleClientMessage(Message message, Server server) {
        System.out.println("New client");
        if (message.sentBy().equals("PLAYER")) { //PLAYER era FAN
            handlePlayerMessage(message, server);
        } else {
            //handleArtistMessage(message, server);
        }
    }

}