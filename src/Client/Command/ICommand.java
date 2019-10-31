package Client.Command;

import BoardElement.IBoardElement;
import Client.Game.Game;
import Client.Game.IGame;

import java.util.ArrayList;

public interface ICommand {
    void execute();
    int getGameId();
    String getCommandText();
    IGame getRealGame();
}
