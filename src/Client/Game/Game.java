package Client.Game;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Player.Player;
import Client.Resources.Warrior;
import Server.ServerMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Serializable, IGame {
    private int identifier;
    private int currentPlayer;
    private int amountPlayers;
    private ArrayList<Player> players;
    private ArrayList<Log> logs;
    private String name;

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

    /*Se debe generar el aleatorio de asignacion de armas por tipo cuando se haga la instancia de game (10 "prototipos")*/

    public Game(int identifier,String name) {
        this.identifier = identifier;
        this.currentPlayer = 0;
        this.amountPlayers=0;
        this.players = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.name = name;
    }

    public void addPlayer(Observer observer) {
        //log anterior
        this.addObserver(observer);
        this.amountPlayers++;
        //log posterior
        setChanged();

        GameNotification followersIncreased = new GameNotification(this.name, "NEW_PLAYER" , this);
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

    public void chat(ICommand command) {
        System.out.println("Entró a mandar chat en Game");

        setChanged();
        GameNotification chatNotification = new GameNotification(this.name,"CHAT_MESSAGE_GAME",this);
        notifyObservers(chatNotification);

    }

    public void pass(ICommand command) {
        System.out.println("Entró a pass en Game");

        setChanged();
        GameNotification passNotification = new GameNotification(this.name,"PASS_MESSAGE_GAME",this);
        notifyObservers(passNotification);

    }


    public void surrender(ICommand command) {
        System.out.println("Entró a surrender en Game");

        setChanged();
        GameNotification surrenderNotification = new GameNotification(this.name,"SURRENDER_MESSAGE_GAME",this);
        notifyObservers(surrenderNotification);

    }

    public void info(ICommand command) {
        System.out.println("Entró a pedir información del otro usuario en Game");

        setChanged();
        GameNotification surrenderNotification = new GameNotification(this.name,"SURRENDER_MESSAGE_GAME",this);
        notifyObservers(surrenderNotification);

    }

    public void comodin(ICommand command) {
        System.out.println("Entró a comodin en Game");

        setChanged();
        GameNotification infoNotification = new GameNotification(this.name,"INFO_MESSAGE_GAME",this);
        notifyObservers(infoNotification);

    }

    public void reload(ICommand command) {
        System.out.println("Entró a recargar armas en Game");

        setChanged();
        GameNotification reloadNotification = new GameNotification(this.name,"RELOAD_MESSAGE_GAME",this);
        notifyObservers(reloadNotification);

    }

    public void end(ICommand command) {
        System.out.println("Entró a end en Game");

        setChanged();
        GameNotification surrenderNotification = new GameNotification(this.name,"END_MESSAGE_GAME",this);
        notifyObservers(surrenderNotification);

    }

    public void attack(ICommand command) {

        System.out.println("Entró a attack en Game");
        //PlayerAttackCommand attack = (PlayerAttackCommand) command;
        setChanged();
        GameNotification attackNotification = new GameNotification(this.name,"NEW_ATTACK_MESSAGE",this);
        notifyObservers(attackNotification);

        //FALTA IMPLEMENTAR
        //Player playerToAttack = getPlayer(attack.getClientToAttackName());//hay que asegurarse que el id de los clientes sea igual al indice de entrada
        //ArrayList<ICharacter> list = playerToAttack.getCharacters().getCharacterList();


        //Guardar estado anterior

        //FALTA IMPLEMENTAR
        //attack.setChars(list);
        //attack.execute();

        //arma.func(personaje-a-atacar) con if´s de tpo del mae
        //this.turnoActual++;


        //FALTA IMPEMENTAR
        //setChanged();

        //Guardar estado posterior

        //PODEMOS HACER UN IF para ver si el juego yá terminó (ver si quedan vivos) y mandar un Notification de final con el Id del ganador.
        //if(...terminó){

       // GameNotification followersIncreased = new GameNotification(this.identifier, "- - Juego terminado - -" , this);

        //}

        //FALTA IMPLEMENTAR
        //ServerMessage message = new ServerMessage("SERVER", "TEST", "test response");

        //FALTA IMPLEMENTAR
        //notifyObservers(message);

    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void addLog(Log log){
        logs.add(log);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
