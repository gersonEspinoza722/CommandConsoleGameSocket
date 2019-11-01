package Client.Resources;

import BoardElement.Character.ICharacter;
import BoardElement.IBoardElement;
import BoardElement.Tools.Tool;
import Client.Game.DamageTable;
import Media.IMediaListing;
import Patterns.IPrototype;
import java.util.Random;

public class Weapon extends Tool {

    private DamageTable damageTable; //<tipo, daño>

    public Weapon(String name, int type, int simpleUseDecrement, DamageTable damageTable) { //cuando creo el weapon tengo que darle la cantidad de usos
        super(name, type, simpleUseDecrement);
        Random random = new Random(System.currentTimeMillis());
        this.damageTable = damageTable;//new DamageTable(Skill.values()[type], random);
    }

    public DamageTable getDamageTable() {
        return damageTable;
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
    public void incUse(int increment){
        simpleUseDecrement = simpleUseDecrement + increment;
    }

    @Override
    public void func(IBoardElement object) {//input can be character or tool to be affected by the weapon
        System.out.println(object.toString());
        //if(object instanceof ICharacter){
        //}
        attack((ICharacter) object);
    }

    private void attack(ICharacter character){
        Warrior warrior = (Warrior) character;
        warrior.decLife(damageTable.getDamage().get(warrior.getSkillType())); //que no se baje de 0
        //decUse(1); //default 4 usos? o llamar desde afuera
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

    @Override
    public void interact(IBoardElement otherElement){
        func(otherElement);
    }
}
