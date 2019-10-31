package Client.Command;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerAttackCommand implements ICommand, Serializable {
    int gameId;
    String clientToAttackName;
    ArrayList<ICharacter> chars;

    public PlayerAttackCommand(int gameId, String clientToAttackName, ArrayList<ICharacter> chars, ITool weapon) {
        this.gameId = gameId;
        this.clientToAttackName = clientToAttackName;
        this.chars = chars;
        this.weapon = weapon;
    }

    public ArrayList<ICharacter> getChars() {
        return chars;
    }

    public void setChars(ArrayList<ICharacter> chars) {
        this.chars = chars;
    }

    public String getClientToAttackName() {
        return clientToAttackName;
    }

    ITool weapon;


@Override
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }



    public String clientToAttackName() {
        return clientToAttackName;
    }

    public void setClientToAttackName(String clientToAttackName) {
        this.clientToAttackName = clientToAttackName;
    }


    public ITool getWeapon() {
        return weapon;
    }

    public void setWeapon(ITool weapon) {
        this.weapon = weapon;
    }



    @Override
    public void execute() {
        for (int i=0;i<chars.size();i++){
            IBoardElement character = (IBoardElement) chars.get(i);
            weapon.interact(character);
        }

    }
}
