package Client.Game;

import Client.*;

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
