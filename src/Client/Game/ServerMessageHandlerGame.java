package Client.Game;

import Client.Command.PlayerAttackCommand;
import Client.IServerMessageHandler;
import Client.Message;

import javax.swing.*;

public class ServerMessageHandlerGame implements IServerMessageHandler {

    //JFrame artistWindow;

    public ServerMessageHandlerGame(JFrame artistWindow) {
        //this.artistWindow = artistWindow;
    }

    @Override
    public void handleServerMessage(Message message) {
        String event = message.getEvent();

        switch (event) {
            case "NEW_PLAYER": {
                System.out.println("Nuevo jugador");
                //Game game = (Game) message.getObjectOfInterest();
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;
            case "NEW_ATTACK_MESSAGE": {
                PlayerAttackCommand attack = (PlayerAttackCommand) message.getObjectOfInterest();
                System.out.println("Un jugador ataco");
                //Pasan cosas de ataque: bajar vidas
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }

        }
    }

}
