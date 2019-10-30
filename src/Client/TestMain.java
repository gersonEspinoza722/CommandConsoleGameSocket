package Client;

import Client.Game.DamageTableGenerator;

public class TestMain {
    public static void main (String [ ] args) {
        DamageTableGenerator generator = new DamageTableGenerator();
        System.out.println(generator.toString());
    }
}
