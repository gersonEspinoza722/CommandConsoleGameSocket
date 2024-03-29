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
        if(tokens[0].equals("SURRENDER")){
            PlayerSurrenderCommand command = new PlayerSurrenderCommand(tokens[1],commandString);
            realCommand=command;
        }
        if(tokens[0].equals("END")){
            PlayerEndCommand command = new PlayerEndCommand(tokens[1],commandString);
            realCommand=command;
        }
        if(tokens[0].equals("PASS")){
            PlayerPassCommand command = new PlayerPassCommand(tokens[1],commandString);
            realCommand=command;
        }
        if(tokens[0].equals("CHAT")){
            PlayerChatCommand command = new PlayerChatCommand(tokens[1],commandString,tokens[2]);
            realCommand=command;
        }
        if(tokens[0].equals("RELOAD")){
            PlayerChatCommand command = new PlayerChatCommand(tokens[1],commandString,tokens[2]);
            realCommand=command;
        }






        return realCommand;
    }
}
