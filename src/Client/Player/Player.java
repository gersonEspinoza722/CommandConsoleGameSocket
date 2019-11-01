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

    public Player(String hostName, int portNumber, ClientType clientType, String name) {
        super(hostName, portNumber, clientType);
        this.name = name;
        //this.followingGame = new ArrayList<>();
        characters = CharacterListingFactory.getInstance().getCharacterListing(CharacterListingFactory.CHARACTER_ARRAY);
        charactersQuantity = 4;
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
