package Client.Player;

import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.Character.CharacterListingFactory;
import BoardElement.Character.ICharacterListing;
import Client.*;
import Client.Game.Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Client implements Serializable {

    private String name;
    private int score;
    private int totalScore;
    private ICharacterListing characters;
    //private ArrayList<Game> followingGame;
    private int charactersQuantity;

    private int ataquesExitosos;
    private int ataquesFracasados;
    private int muertesEnemigas;
    private int victorias;
    private int fracasos;
    private int rendiciones;

    public Player(String hostName, int portNumber, ClientType clientType, String name) {
        super(hostName, portNumber, clientType);
        this.name = name;
        //this.followingGame = new ArrayList<>();
        characters = CharacterListingFactory.getInstance().getCharacterListing(CharacterListingFactory.CHARACTER_ARRAY);
        charactersQuantity = 4;

        //se deben cargar desde archivo externo
        ataquesExitosos = 0;
        ataquesFracasados = 0;
        muertesEnemigas = 0;
        victorias = 0;
        fracasos = 0;
        rendiciones = 0;
    }

    public void setCharactersQuantity(int charactersQuantity) {
        this.charactersQuantity = charactersQuantity;
    }

    public int getAtaquesExitosos() {
        return ataquesExitosos;
    }

    public void addAtaquesExitosos() {
        this.ataquesExitosos++;
    }

    public int getAtaquesFracasados() {
        return ataquesFracasados;
    }

    public void addAtaquesFracasados() {
        this.ataquesFracasados++;
    }

    public int getMuertesEnemigas() {
        return muertesEnemigas;
    }

    public void addMuertesEnemigas() {
        this.muertesEnemigas++;
    }

    public int getVictorias() {
        return victorias;
    }

    public void addVictorias() {
        this.victorias++;
    }

    public int getFracasos() {
        return fracasos;
    }

    public void addFracasos() {
        this.fracasos++;
    }

    public int getRendiciones() {
        return rendiciones;
    }

    public void addRendiciones(int rendiciones) {
        this.rendiciones++;
    }

    public Player(int id) {
        super(id);
        characters = CharacterListingFactory.getInstance().getCharacterListing(CharacterListingFactory.CHARACTER_ARRAY);
        charactersQuantity = 4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ICharacterListing getCharacters() {
        return characters;
    }

    public void setCharacters(ICharacterListing characters) {
        this.characters = characters;
    }

    public void addCharacter(ICharacter character){
        characters.addCharacter(character);
        //System.out.println(character.getName()+" "+character.getTools().getTool(0).getType());
    }

    public int getCharactersQuantity() {
        return charactersQuantity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void addScore(int score){
        totalScore += score;
    }

    /*public ArrayList<Game> getFollowingGame() {
        return followingGame;
    }

    public void setFollowingGame(ArrayList<Game> followingGame) {
        this.followingGame = followingGame;
    }*/
}
