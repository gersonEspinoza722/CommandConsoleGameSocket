package Client.Player;

import Client.*;
import Client.Game.Game;

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
            case "NEW_ARTIST_MESSAGE":{
              //  ArtistPost newPost = (ArtistPost) message.getObjectOfInterest();
                //((FanWindow) fanWindow).displayArtistPost(newPost);
            }
            break;

            case "NEW_GAME":{
                System.out.println("Nuevo Game");
                //Artist newArtist = (Artist) message.getObjectOfInterest();
                //((FanWindow) fanWindow).addNewArtist(newArtist);
            }
            break;
            case "NEW_ATTACK_MESSAGE":{
                System.out.println("Nuevo ataque");


                //ArtistPost likedMessage = (ArtistPost) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, likedMessage.getArtistName() + "'s" + " message: \"" + likedMessage.getText() + " \"" + " got to "  + likedMessage.getLikes() + " likes");
            }
            break;
            case "SURRENDER_MESSAGE_GAME":{
                System.out.println("Se rindió");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //ArtistPost likedMessage = (ArtistPost) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, likedMessage.getArtistName() + "'s" + " message: \"" + likedMessage.getText() + " \"" + " got to "  + likedMessage.getLikes() + " likes");
            }
            break;

            case "FOLLOWERS_MILESTONE":{
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            case "END_MESSAGE_GAME":{
                System.out.println("Se finaliza juego empate");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
            case "RELOAD_MESSAGE_GAME":{
                System.out.println("Se recarga armas");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
            case "COMODIN_MESSAGE_GAME":{
                System.out.println("Se usa el comodin");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
            case "INFO_MESSAGE_GAME":{
                System.out.println("Pide info del otro");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
            case "PASS_MESSAGE_GAME":{
                System.out.println("Pasa turno");
                System.out.println(((Game)message.getObjectOfInterest()).toString());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
            case "CHAT_MESSAGE_GAME":{
                System.out.println("Manda chat:");
                System.out.println(message.getObjectOfInterest());
                //Artist followedArtist = (Artist) message.getObjectOfInterest();
                //JOptionPane.showMessageDialog(fanWindow, followedArtist.getGameName() + " got to " +  followedArtist.getFollowers() + " followers");
            }
            break;
        }
    }

}