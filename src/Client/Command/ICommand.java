package Client.Command;

import BoardElement.IBoardElement;

import java.util.ArrayList;

public interface ICommand {
    void execute();
    int getGameId();
}
