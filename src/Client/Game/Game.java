package Client.Game;

import Client.Message;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Serializable {
    private String identifier;
    private int currentPlayer;
    private int amountPlayers;
    //LA LISTA DE COMANDOS Y ESTADOS VA CON PROXY Y VA AQUI TALVEZ

    public Game(String identifier) {
        this.identifier = identifier;
        this.currentPlayer = 0;
        this.amountPlayers=0;
    }

    public void addFollower(Observer observer) {
        this.addObserver(observer);
        this.amountPlayers++;
        setChanged();

        GameNotification followersIncreased = new GameNotification(this.identifier, "New player joined the game" , this);
        notifyObservers(followersIncreased);

    }

    public void attack(ICommand command) {

        //PlayerAttack attack = (PlayerAttack) command

        //Sacamos cosas valiosas de attack: a quien afecta?, cuanto qué arma? (para ver pos tipo de personaje cuanto quitar que lo trae el arma)
        //Encontramos al jugador a atacar
        //Encontramos el personaje a atacar
        //Encontramos el arma

        //Guardar estado anterior

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
