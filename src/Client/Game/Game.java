package Client.Game;

import BoardElement.Character.ICharacter;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Serializable {
    private String identifier;
    private int currentPlayer;
    private int amountPlayers;
    private ArrayList<Player> players;
    //LA LISTA DE COMANDOS Y ESTADOS VA CON PROXY Y VA AQUI TALVEZ
    //aqui se pone el log tambien

    public Game(String identifier) {
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

    public void attack(ICommand command) {

        PlayerAttackCommand attack = (PlayerAttackCommand) command;

        Player playerToAttack = players.get(attack.getClientToAttackId());//hay que asegurarse que el id de los clientes sea igual al indice de entrada
        ICharacter character = playerToAttack.getCharacters().getCharacter(attack.getCharacterToAttackId());

        //Guardar estado anterior

        command.execute(character);

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }



}
