package Client.Command;

import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;

public class PlayerAttackCommand implements ICommand {
    int gameId;
    String clientToAttackName;
    int characterToAttackId;

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

    public int getCharacterToAttackId() {
        return characterToAttackId;
    }

    public void setCharacterToAttackId(int characterToAttackId) {
        this.characterToAttackId = characterToAttackId;
    }

    public ITool getWeapon() {
        return weapon;
    }

    public void setWeapon(ITool weapon) {
        this.weapon = weapon;
    }



    @Override
    public void execute(Object interest) {
        IBoardElement character = (IBoardElement) interest;
        weapon.interact(character);
    }
}
