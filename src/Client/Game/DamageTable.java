package Client.Game;

import Client.Resources.Skill;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Random;

public class DamageTable implements Serializable {
    private Skill skillType;
    private Hashtable<Skill, Integer> damage;

    public DamageTable(Skill skillType, Random random) {
        this.skillType = skillType;
        this.damage = new Hashtable<>();
        Skill types[] = Skill.values();
        for (int i=0; i<types.length; i++){
            int randomDamage = random.nextInt(80) + 20;
            damage.put(types[i], randomDamage);
        }
    }

    @Override
    public String toString() {
        /*
        String text = "DamageTable{" +
                "skillType=" + skillType +
                ", damage=";
        Skill types[] = Skill.values();
        int counter = 0;
        for (Integer dmg : damage.values()){
            text = text.concat(types[counter].name()+" "+dmg.toString()+", ");
            counter++;
        }*/
        int counter = 0;
        Skill types[] = Skill.values();
        String toString="";
        for (Integer dmg : damage.values()){
            toString = toString.concat(" "+dmg.toString());
            counter++;
        }
        return toString;
    }

    public Skill getSkillType() {
        return skillType;
    }

    public Hashtable<Skill, Integer> getDamage() {
        return damage;
    }
}
