package Client.Game;

import Client.Command.ICommand;

public class GameProxy implements IGame {

    private ICommand command;
    private IGame game;
    private Log log;

    public GameProxy(ICommand command) {
        this.game = command.getRealGame();
        this.command = command;
        this.log = new Log(command);
    }

    @Override
    public void surrender(ICommand command) {

    }

    @Override
    public void attack(ICommand command) {
        log.attack(command);
        ((Game) game).addLog(log);
    }
}
