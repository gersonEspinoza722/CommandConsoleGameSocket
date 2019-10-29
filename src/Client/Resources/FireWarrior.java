package Client.Resources;

import BoardElement.Character.CharacterAbstract;

public class FireWarrior extends CharacterAbstract {
    private int skillType;

    public FireWarrior(int skillType) {
        this.skillType = skillType;
    }

    public FireWarrior(String name, float defaultLife, int skillType) {
        super(name, defaultLife);
        this.skillType = skillType;
    }

    public int getSkillType() {
        return skillType;
    }

    public void setSkillType(int skillType) {
        this.skillType = skillType;
    }
    
}
