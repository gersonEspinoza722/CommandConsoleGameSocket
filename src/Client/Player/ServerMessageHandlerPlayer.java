package Client.Player;

import Client.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

public class ServerMessageHandlerPlayer implements IServerMessageHandler {

    //JFrame fanWindow;

    public ServerMessageHandlerPlayer(JFrame fanWindow) {
        //this.fanWindow = fanWindow;
    }



    @Override
    public void handleServerMessage(Message message) {
        String event = message.getEvent();

        switch(event){
            case "SENT_GAMES_LIST":{
                ArrayList<Observable> artists = (ArrayList<Observable>) message.getObjectOfInterest();
                //((FanWindow) fanWindow).displayArtists(artists);
            }
            break;
            case "TEST":{
                System.out.println("entró a respuesta test");
                //ArrayList<Observable> artists = (ArrayList<Observable>) message.getObjectOfInterest();
            //    ((FanWindow) fanWindow).displayArtists(artists);
            }
            break;

            case "NEW_ARTIST_MESSAGE":{
              //  ArtistPost newPost = (ArtistPost) message.getObjectOfInterest();
                //((FanWindow) fanWindow).displayArtistPost(newPost);
            }
            break;

            case "NEW_GAME":{
                System.out.println("Nuevo Juego");
                //Artist newArtist = (Artist) message.getObjectOfInterest();
                //((FanWindow) fanWindow).addNewArtist(newArtist);
            }
            break;

            case "LIKE_MILESTONE":{
                //ArtistPost likedMessage = (ArtistPost) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, likedMessage.getArtistName() + "'s" + " message: \"" + likedMessage.getText() + " \"" + " got to "  + likedMessage.getLikes() + " likes");

            }
            break;

            case "FOLLOWERS_MILESTONE":{
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
        }
    }

}