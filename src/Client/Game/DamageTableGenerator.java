package Client.Game;

import Client.Resources.Skill;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Random;

public class DamageTableGenerator implements Serializable {
    private Hashtable <Skill, DamageTable> damagePerSkill;

    public DamageTableGenerator() {
        damagePerSkill = new Hashtable<>();
        Skill types[] = Skill.values();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i<types.length; i++){
            damagePerSkill.put(types[i], new DamageTable(types[i], random));
        }
    }

    @Override
    public String toString() {
        String text =  "DamageTableGenerator{" +
                "damagePerSkill=";
        for (DamageTable dt : damagePerSkill.values()){
            text = text.concat(dt.toString()+"\n");
        }
        return text;
    }

    public DamageTable getDamageTable(Skill skill){
        return damagePerSkill.get(skill);
    }
}
