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
        game.attack(command); //se realiza la accion de atacar
        log.attack(command); //se documenta en el log la accion de atacar
        ((Game) game).addLog(log); //se mete en el array
    }

    @Override
    public void addLog(Log log) {
        this.log = log;
    }
}
