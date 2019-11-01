package Client.Game;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.Tools.ITool;
import BoardElement.Tools.IToolListing;
import Client.Command.ICommand;
import Client.Command.PlayerAttackCommand;
import Client.Player.Player;
import Client.Resources.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Serializable, IGame {
    private int identifier;
    private int currentPlayer;
    private int amountPlayers;
    private ArrayList<Player> players; //ICharacterListing
    private ArrayList<Log> logs; //ILog listing?
    private String name;
    private int turno;
    private GameStatus status;

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
        this.amountPlayers=2;
        this.players = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.name = name;
        this.turno = -1; //hacer logica para asignar turnos
        this.status = GameStatus.STARTED;
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

    public Player getOtherPlayer(){
        return players.get(turno%amountPlayers+1);
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

        PlayerAttackCommand attack = (PlayerAttackCommand) command; //nuevo
        GameNotification attackNotification = new GameNotification(this.name,"NEW_ATTACK_MESSAGE",this);

        Player playerToAttack = getOtherPlayer();
        ICharacterListing list = playerToAttack.getCharacters();

        attack.setCharacters(list);
        attack.execute();

        if(isOver(getOtherPlayer())> 0){
            finishGame();
        }

        //aumentar turno
        aumentarTurno();

        //----------------------------logica observers
        setChanged();
        notifyObservers(attackNotification);

    }

    private int isOver(Player attackedPlayer){
        //si el jugador atacado tiene 0 characters vivos, termina, devuelve id del perdedor y si no, devueve -1;
        ICharacterListing playerCharacters = attackedPlayer.getCharacters();
        Boolean gameOverFlag = true; //se inicializa true, si se encuentra character vivo, se setea a false
        for(int i = 0; i<playerCharacters.getSize(); i++){
            ICharacter character = playerCharacters.getCharacter(i);
            if(character.getCurrentLife() > 0){
                gameOverFlag = false;
                break;
            }
        }
        int attackedPlayerId = -1;
        if(gameOverFlag == true)
            attackedPlayerId = attackedPlayer.getId();
        return attackedPlayerId;
    }

    private void aumentarTurno(){
        turno = (turno+1)%amountPlayers;
    }

    private void finishGame(){
        status = GameStatus.WON;

        //MENSAJE DE GANAR //terminar juego (command) y notificar (mensaje ganar, hacer nuevo caso) necesita que le meta un player de atributo
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void addLog(Log log){
        logs.add(log);
    }

    @Override
    public ITool getWeapon(String weponName, String warriorName) {// retorna el arma del character del turno activo requerida
        for (int i = 0; i<players.get(turno%amountPlayers).getCharacters().getSize(); i++){
            ICharacter character = players.get(turno%amountPlayers).getCharacters().getCharacter(i);
            if(character.getName().equals(warriorName)){
                for (int j = 0; j<character.getTools().getSize(); j++){
                    ITool weapon = (Weapon) character.getTools().getTool(j);
                    if(weapon.getName().equals(weponName))
                        return weapon;
                }
            }
        }
        return null;
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
