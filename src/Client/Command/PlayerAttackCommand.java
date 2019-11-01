package Client.Command;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;
import Client.Game.Game;
import Client.Game.IGame;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerAttackCommand implements ICommand, Serializable{

    private IGame realGame;
    private String commandText;
    private ITool weapon;
    private String weaponName;
    private String warriorName;
    private String clientToAttackName;
    private ICharacterListing characters; //personajes a atacar
    private int totalDaño;
    private String gameName; //nombre del juego
    private boolean errorFlag;

    private ArrayList<Integer> vidasAntes;
    private ArrayList<Integer> vidasDespues;

    public PlayerAttackCommand(String gameName, String commandText, String weaponName, String warriorName){//, String weaponName, String warriorName) { //a partir de commandText se puede obtener weapon y warrior? si si, eliminar dos ultimos campos del constructor
        this.gameName = gameName;

        this.totalDaño = 0;
        this.commandText = commandText;
        this.weaponName = weaponName;
        this.warriorName = warriorName;
        this.vidasAntes = new ArrayList<>();
        this.vidasDespues = new ArrayList<>();
        this.errorFlag = false;

    }

    public ICharacterListing getCharacters() {
        return characters;
    }

    public void setCharacters(ICharacterListing characters) {
        this.characters = characters;
    }

    public String getClientToAttackName() {
        return clientToAttackName;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public void setRealGame(IGame realGame) {
        this.realGame = realGame;
        System.out.println(((Game)realGame).getName());
        weapon = realGame.getWeapon(weaponName, warriorName);
    }



    @Override
    public IGame getRealGame() {
        return realGame;
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

    public ArrayList<Integer> getVidasAntes() {
        return vidasAntes;
    }

    public ArrayList<Integer> getVidasDespues() {
        return vidasDespues;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getTotalDaño() {
        return totalDaño;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public String getWarriorName() {
        return warriorName;
    }

    @Override
    
    public void execute() {
        for (int i = 0; i< characters.getSize(); i++){
            int vidaAntes = (int) characters.getCharacter(i).getCurrentLife();
            vidasAntes.add(vidaAntes);
            System.out.println(vidasAntes.toString());

            IBoardElement character = characters.getCharacter(i);
            //System.out.println(((ICharacter) character).getName());
            if(errorFlag == true)
                weapon.setNoAttackFlag(true);

            weapon.interact(character); //meter daños al arraylist

            int vidaDespues = (int) characters.getCharacter(i).getCurrentLife();
            vidasDespues.add(vidaDespues);
            //System.out.println(vidasDespues.toString());

            totalDaño += (vidaAntes - vidaDespues);
        }
        if(errorFlag == false)
            weapon.decUse(1);
        else
            weapon.setNoAttackFlag(false);
        errorFlag = false;
    }

    @Override
    public int getGameId() {
        return 0;
    }

    @Override
    public String getCommandText() {
        return commandText;
    }
}
