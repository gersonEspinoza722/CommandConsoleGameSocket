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
                System.out.println("Un nuevo jugador");
                //Game game = (Game) message.getObjectOfInterest();
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;
            case "NEW_ATTACK_MESSAGE": {
                //PlayerAttackCommand attack = (PlayerAttackCommand) message.getObjectOfInterest();
                System.out.println("Un jugador ataco");
                //Pasan cosas de ataque: bajar vidas
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;
            case "SURRENDER_MESSAGE_FROM_SERVER": {
                //PlayerAttackCommand attack = (PlayerAttackCommand) message.getObjectOfInterest();
                System.out.println("Un jugador se rindió");
                //Pasan cosas de ataque: bajar vidas
                //((ArtistWindow) artistWindow).setFollowersLabelText("" + artist.getFollowers());
            }
            break;
            case "END_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador terminó el juego empate");
            }
            break;
            case "RELOAD_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador recargó armas");
            }
            break;

            case "COMODIN_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador usó comodin");
            }
            break;

            case "INFO_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador pidió información");
            }
            break;
            case "PASS_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador saltó turno");
            }
            break;
            case "CHAT_MESSAGE_FROM_SERVER":{
                System.out.println("Un jugador mandó chat");
            }
            break;


        }
    }

}
