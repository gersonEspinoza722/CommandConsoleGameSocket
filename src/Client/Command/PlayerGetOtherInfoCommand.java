package Client.Command;

import java.io.Serializable;

public class PlayerGetOtherInfoCommand implements ICommand, Serializable {
    int gameId;
    String name;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerGetOtherInfoCommand(int gameId, String name) {
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
}
