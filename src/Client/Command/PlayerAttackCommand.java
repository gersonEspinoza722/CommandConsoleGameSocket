package Client.Command;

import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;

public class PlayerAttackCommand implements ICommand {
    int clientToAttackId;
    int characterToAttackId;
    ITool weapon;

    public int getClientToAttackId() {
        return clientToAttackId;
    }

    public void setClientToAttackId(int clientToAttackId) {
        this.clientToAttackId = clientToAttackId;
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
