package Client;

import Client.Game.DamageTable;
import Client.Resources.Skill;

import java.util.Random;

public class TestMain {
    public static void main (String [ ] args) {
        Random random = new Random(System.currentTimeMillis());
        DamageTable generator = new DamageTable(Skill.ACID, random);
        System.out.println(generator.toString());
    }
}
