package Client.Command;

import Client.Game.IGame;

public class PlayerSurrenderCommand implements ICommand {
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
