package Client.Command;

import Client.Game.IGame;

import java.io.Serializable;

public class PlayerPassCommand implements ICommand, Serializable {
    int gameId;
    String name;
    private IGame realGame;
    private String commandText;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerPassCommand(int gameId, String name) {
        this.gameId = gameId;
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
        return commandText;
    }

    @Override
    public IGame getRealGame() {
        return realGame;
    }
}
