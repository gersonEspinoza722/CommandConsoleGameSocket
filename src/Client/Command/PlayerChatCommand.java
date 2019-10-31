package Client.Command;

import java.io.Serializable;

public class PlayerChatCommand implements ICommand, Serializable {
    int gameId;
    String name;
    String text;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PlayerChatCommand(int gameId, String name, String text) {
        this.gameId = gameId;
        this.name = name;
        this.text = text;
    }

    @Override
    public void execute() {

    }

    @Override
    public int getGameId() {
        return 0;
    }
}