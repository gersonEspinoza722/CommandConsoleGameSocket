package Server;

import Client.Game.Game;
import Client.Game.GameNotification;
import Client.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationHandler implements INotificationHandler{

    @Override
    public Message handleObservableNotification(ObjectOutputStream clientWriter, Object obj) {
        //ANTES HABÏA UN CASO DE GamePost

        if (obj instanceof Game){
            Game newGame = (Game)obj;
            Message newGameMessage = new ServerMessage("SERVER", "NEW_GAME", newGame);

            try {
                clientWriter.writeObject(newGameMessage);
            }

            catch (IOException ex) {
                Logger.getLogger(NotificationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (obj instanceof GameNotification) {
            GameNotification notification = (GameNotification) obj;

            try {
                clientWriter.reset();
                clientWriter.writeObject(notification);

                if (notification.getObjectOfInterest() instanceof Game){
                    Game game = (Game) notification.getObjectOfInterest();
                }

            } catch (IOException ex) {
                Logger.getLogger(NotificationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

}