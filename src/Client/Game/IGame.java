package Client.Game;

import Client.Command.ICommand;

import java.util.ArrayList;

public interface IGame {
    void surrender(ICommand command);
    void attack(ICommand command);
    void addLog(Log log);
}
