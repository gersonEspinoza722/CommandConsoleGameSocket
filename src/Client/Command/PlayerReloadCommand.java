package Client.Command;

import java.io.Serializable;

public class PlayerReloadCommand implements ICommand, Serializable {
    int gameId;
    String name;


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