package Client.Game;

import BoardElement.Tools.ITool;
import Client.Command.ICommand;

import java.io.Serializable;
import java.util.ArrayList;

public interface IGame extends Serializable {
    void surrender(ICommand command);
    void attack(ICommand command);
    void addLog(Log log);
    ITool getWeapon(String weaponName, String warriorName);
}
