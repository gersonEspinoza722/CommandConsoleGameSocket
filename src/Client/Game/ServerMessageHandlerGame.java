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
                //Game game = (Game) message.getObjectOfInterest();
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;
            case "ATTACK_MESSAGE": {
                PlayerAttackCommand attack = (PlayerAttackCommand) message.getObjectOfInterest();
                //Pasan cosas de ataque: bajar vidas
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;

        }
    }

}
