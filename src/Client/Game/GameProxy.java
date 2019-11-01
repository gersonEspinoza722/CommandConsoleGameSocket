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
        //System.out.println(command.getGameId());
        game.attack(command); //se realiza la accion de atacar
        log.attack(command); //se documenta en el log la accion de atacar
        (game).addLog(log); //se mete en el array
    }

    @Override
    public void chat(ICommand command) {
        game.chat(command); //se realiza la accion de atacar
        log.chat(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
    }

    @Override
    public void reload(ICommand command) {
        game.reload(command); //se realiza la accion de atacar
        log.reload(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
    }

    @Override
    public void pass(ICommand command) {
        game.pass(command); //se realiza la accion de atacar
        log.pass(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
    }

    @Override
    public void comodin(ICommand command) {
        game.comodin(command); //se realiza la accion de atacar
        log.comodin(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
    }

    @Override
    public void end(ICommand command) {
        game.end(command); //se realiza la accion de atacar
        log.end(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
    }

    @Override
    public void info(ICommand command) {
        game.info(command); //se realiza la accion de atacar
        log.info(command); //se documenta en el log la accion de atacar
        (game).addLog(log);
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
