package Client.Command;

import Client.Game.IGame;

import java.io.Serializable;

public class PlayerSurrenderCommand  implements ICommand, Serializable {
    int gameId;
    String name;
    String commandText;


    public PlayerSurrenderCommand(String name, String commandText) {

        this.name = name;
        this.commandText = commandText;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute() {

    }

    @Override
    public int getGameId() {
        return 0;
    }

    @Override
    public String getCommandText() {
        return null;
    }

    @Override
    public IGame getRealGame() {
        return null;
    }
}
