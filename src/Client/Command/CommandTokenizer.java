package Client.Command;

import Client.Message;
import Client.Player.PlayerMessage;

public class CommandTokenizer {
    private static CommandTokenizer singleton=null;

    private CommandTokenizer() {
    }

    public static CommandTokenizer getInstance() {
        if(singleton == null){
            singleton = new CommandTokenizer();
        }
        return singleton;
    }

    public ICommand analyzeCommand(String commandString){
        ICommand realCommand;
        realCommand = null;
        String[] tokens = commandString.split(" ");

        if(tokens[0].equals("ATTACK")){
            PlayerAttackCommand command = new PlayerAttackCommand(tokens[1],commandString, tokens[3],tokens[2]);
            realCommand=command;
        }





        return realCommand;
    }
}
