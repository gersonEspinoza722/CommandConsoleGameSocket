package Client.Game;

import BoardElement.Tools.ITool;
import Client.Command.ICommand;

import java.io.Serializable;

public class GameProxy implements IGame, Serializable {

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
        (game).addLog(log); //se mete en el array
    }

    @Override
    public void addLog(Log log) {
        this.log = log;
    }

    @Override
    public ITool getWeapon(String weaponName, String warriorName) {
        return command.getRealGame().getWeapon(weaponName, warriorName);
    }
}
