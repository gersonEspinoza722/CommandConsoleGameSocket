package Client.Game;

import BoardElement.Tools.ITool;
import Client.Command.ICommand;

import java.io.Serializable;
import java.util.ArrayList;

public interface IGame extends Serializable {
    void surrender(ICommand command);
    void attack(ICommand command);
    void chat(ICommand command);
    void reload(ICommand command);
    void pass(ICommand command);
    void comodin(ICommand command);
    void end(ICommand command);
    void info(ICommand command);
    void addLog(Log log);
    ITool getWeapon(String weaponName, String warriorName);
}
