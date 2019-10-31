package Client.Game;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Player.Player;
import Client.Resources.Warrior;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Serializable {
    private int identifier;
    private int currentPlayer;
    private int amountPlayers;
    private ArrayList<Player> players;

    public int getAmountPlayers() {
        return amountPlayers;
    }

    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
//LA LISTA DE COMANDOS Y ESTADOS VA CON PROXY Y VA AQUI TALVEZ
    //aqui se pone el log tambien

    public Game(int identifier) {
        this.identifier = identifier;
        this.currentPlayer = 0;
        this.amountPlayers=0;
    }

    public void addFollower(Observer observer) {
        //log anterior
        this.addObserver(observer);
        this.amountPlayers++;
        //log posterior
        setChanged();

        GameNotification followersIncreased = new GameNotification(this.identifier, "New player joined the game" , this);
        notifyObservers(followersIncreased);

    }

    public Player getPlayer(String name){
        Player player;
        player=null;
        for(int i=0; i<players.size();i++){
            if(players.get(i).getName().equals(name)){
                player= players.get(i);
                break;
            }
        }
        return player;

    }
    public void surrender(ICommand command) {}

    public void attack(ICommand command) {

        PlayerAttackCommand attack = (PlayerAttackCommand) command;

        Player playerToAttack = getPlayer(attack.getClientToAttackName());//hay que asegurarse que el id de los clientes sea igual al indice de entrada
        ArrayList<ICharacter> list = playerToAttack.getCharacters().getCharacterList();


        //Guardar estado anterior

        attack.setChars(list);
        attack.execute();

        //arma.func(personaje-a-atacar) con if´s de tpo del mae
        //this.turnoActual++;


        setChanged();

        //Guardar estado posterior

        //PODEMOS HACER UN IF para ver si el juego yá terminó (ver si quedan vivos) y mandar un Notification de final con el Id del ganador.
        //if(...terminó){

       // GameNotification followersIncreased = new GameNotification(this.identifier, "- - Juego terminado - -" , this);

        //}
        notifyObservers(this);

    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }



}
