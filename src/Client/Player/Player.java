package Client.Player;

import BoardElement.Character.ICharacterListing;
import BoardElement.Character.CharacterListingFactory;
import BoardElement.Character.ICharacterListing;
import Client.*;

import java.util.ArrayList;
import java.util.Observable;

public class Player extends Client {

    private String name;
    private Observable subscribedGame;
    private int score;
    private int totalScore;
    private ICharacterListing characters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Observable getSubscribedGame() {
        return subscribedGame;
    }

    public void setSubscribedGame(Observable subscribedGame) {
        this.subscribedGame = subscribedGame;
    }

    public ICharacterListing getCharacters() {
        return characters;
    }

    public void setCharacters(ICharacterListing characters) {
        this.characters = characters;
    }

    public Player(String hostName, int portNumber, ClientType clientType) {
        super(hostName, portNumber, clientType);
        characters = CharacterListingFactory.getInstance().getCharacterListing(CharacterListingFactory.CHARACTER_ARRAY);
    }
}
