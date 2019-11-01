package Client.Game;

import BoardElement.Tools.ITool;
import Client.Command.ICommand;

import java.util.ArrayList;

public interface IGame {
    void surrender(ICommand command);
    void attack(ICommand command);
    void addLog(Log log);
    ITool getWeapon(String weaponName, String warriorName);
}
