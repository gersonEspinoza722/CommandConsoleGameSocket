package Client.Command;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;
import Client.Game.Game;
import Client.Game.IGame;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerAttackCommand implements ICommand, Serializable{
    private String commandText; //desde el constructor?
    private int gameId;
    private IGame realGame;
    private ITool weapon;
    private String clientToAttackName;
    private ArrayList<ICharacter> chars;
    private int totalDaño;
    String name;

    private ArrayList<Integer> vidasAntes;
    private ArrayList<Integer> vidasDespues;

    public PlayerAttackCommand(int gameId,String name, String clientToAttackName, ArrayList<ICharacter> chars, ITool weapon) {
        this.gameId = gameId;
        this.name = name;
        this.clientToAttackName = clientToAttackName;
        this.chars = chars;
        this.weapon = weapon;
        this.totalDaño = 0;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        for (int i=0;i<chars.size();i++){
            int vidaAntes = (int) chars.get(i).getCurrentLife();
            vidasAntes.add(vidaAntes);

            IBoardElement character = chars.get(i);
            weapon.interact(character); //meter daños al arraylist

            int vidaDespues = (int) chars.get(i).getCurrentLife();
            vidasDespues.add(vidaDespues);

            totalDaño += (vidaAntes - vidaDespues);
        }

    }
}
