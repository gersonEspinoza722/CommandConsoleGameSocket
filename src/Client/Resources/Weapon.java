package Client.Resources;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import BoardElement.Tools.ITool;
import BoardElement.Tools.Tool;
import Media.IMediaElement;
import Media.IMediaListing;
import Media.MediaListingFactory;
import Patterns.IBuilder;
import Patterns.IPrototype;

public class Weapon extends Tool {


    public Weapon(String name, int type, int simpleUseDecrement) {
        super(name, type, simpleUseDecrement);
    }

    @Override
    public void setDefaultLife(int amount) {
        defaultLife = amount;
    }

    @Override
    public void decLife(int amount) {
        if(decrementableLife - amount <= 0){
            decrementableLife = 0;
        }
        else{
            decrementableLife -= amount;
        }
    }

    @Override
    public void incLife(int amount) {
        if(decrementableLife + amount >= defaultLife){
            decrementableLife = defaultLife;
        }
        else{
            decrementableLife += amount;
        }
    }

    @Override
    public void decUse(int decrement) {
        simpleUseDecrement = simpleUseDecrement - decrement;
    }

    @Override
    public void func(IBoardElement object) {//input can be character or tool to be affected by the weapon
        if(object instanceof ICharacter){

        }
    }

    @Override
    public IPrototype clone() {
        return null;
    }

    @Override
    public IPrototype deepClone() {

        BoardElement.Tools.Concrete.Weapon clonedWeapon = new BoardElement.Tools.Concrete.Weapon(this.simpleUseDecrement, this.name, this.defaultLife, this.decrementableLife, this.reach, this.level, this.minCharacterLevelReq, this.minPlayerLevelReq, this.media,type);
        return clonedWeapon;
    }

    @Override
    public IMediaListing getMediaListing() {
        return this.media;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getToString() {
        String toString="";
        toString = "Name" + this.name + "\n" +
                "SimpleUseDecrement" + this.simpleUseDecrement + "\n"+
                "Type" + this.type + "\n";
        return toString;
    }

    @Override
    public void decLevel() {
        this.level --;
    }
}
