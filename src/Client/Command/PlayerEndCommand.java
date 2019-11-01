package Client.Command;

import Client.Game.IGame;

import java.io.Serializable;

public class PlayerEndCommand implements ICommand, Serializable {
    int gameId;
    private IGame realGame;
    private String commandText;


    String name;
    public PlayerEndCommand(String name,String commandText) {
this.commandText=commandText;
        this.name = name;
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
        return commandText;
    }

    @Override
    public IGame getRealGame() {
        return realGame;
    }
}
