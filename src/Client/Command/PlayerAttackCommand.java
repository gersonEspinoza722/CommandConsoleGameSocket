package Client.Command;

import BoardElement.Character.ICharacterListing;
import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;
import Client.Game.IGame;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerAttackCommand implements ICommand, Serializable{
    private int gameId;
    private IGame realGame;
    private String commandText;
    private ITool weapon;
    private String weaponName;
    private String warriorName;
    private String clientToAttackName;
    private ICharacterListing characters; //personajes a atacar
    private int totalDaño;
    private String gameName; //nombre del juego

    private ArrayList<Integer> vidasAntes;
    private ArrayList<Integer> vidasDespues;

    public PlayerAttackCommand(int gameId, String gameName, String clientToAttackName, String commandText, String weaponName, String warriorName) { //a partir de commandText se puede obtener weapon y warrior? si si, eliminar dos ultimos campos del constructor
        this.gameId = gameId;
        this.gameName = gameName;
        this.clientToAttackName = clientToAttackName;
        this.totalDaño = 0;
        this.commandText = commandText;
        this.weaponName = weaponName;
        this.warriorName = warriorName;
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


    @Override
    public int getGameId() {
        return gameId;
    }

    @Override
    public IGame getRealGame() {
        return realGame;
    }

    public void setRealGame(IGame realGame) {
        this.realGame = realGame;
        weapon = realGame.getWeapon(weaponName, warriorName);
    }

    @Override
    public String getCommandText() {
        //falta implementar

        return null;
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

            IBoardElement character = characters.getCharacter(i);
            weapon.interact(character); //meter daños al arraylist

            int vidaDespues = (int) characters.getCharacter(i).getCurrentLife();
            vidasDespues.add(vidaDespues);

            totalDaño += (vidaAntes - vidaDespues);
        }

    }
}
