package Client.Command;

import Client.Game.IGame;

import java.io.Serializable;

public class PlayerChatCommand implements ICommand, Serializable {

    String name;
    String text;
    String commandText;
    private IGame realGame;



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

    public PlayerChatCommand(String name,String commandText ,String text) {
this.commandText=commandText;
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

    @Override
    public String getCommandText() {
        return text;
    }

    @Override
    public IGame getRealGame() {
        return realGame;
    }
}