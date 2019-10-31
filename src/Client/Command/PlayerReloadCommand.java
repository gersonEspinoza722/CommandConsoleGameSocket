package Client.Command;

import Client.Game.IGame;

import java.io.Serializable;

public class PlayerReloadCommand implements ICommand, Serializable {
    int gameId;
    String name;
    private IGame realGame;
    private String commandText;


    public PlayerReloadCommand(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;

    }

    @Override
    public void execute() {

    }

    @Override
    public int getGameId() {
        return gameId;
    }

    @Override
    public String getCommandText() {
        return commandText;
    }

    @Override
    public IGame getRealGame() {
        return realGame;
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
}