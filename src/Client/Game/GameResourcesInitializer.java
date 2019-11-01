package Client.Game;

import BoardElement.Character.CharacterListingFactory;
import BoardElement.Character.ICharacter;
import BoardElement.Character.ICharacterListing;
import BoardElement.Tools.ITool;
import BoardElement.Tools.IToolListing;
import BoardElement.Tools.ToolListingFactory;
import Client.Resources.Skill;
import Client.Resources.Warrior;
import Client.Resources.Weapon;
import Patterns.IPrototype;

public class GameResourcesInitializer {

    private ICharacterListing availableCharacters;
    private IToolListing availableWeapons;

    private CharacterListingFactory characterListingFactory;
    private ToolListingFactory toolListingFactory;

    public GameResourcesInitializer() {
        characterListingFactory = CharacterListingFactory.getInstance();
        toolListingFactory = ToolListingFactory.getInstance();

        availableCharacters = characterListingFactory.getCharacterListing(CharacterListingFactory.CHARACTER_ARRAY);
        availableWeapons = toolListingFactory.getToolListing(ToolListingFactory.TOOL_ARRAY);

        //hacer factory de weapon y warrior si sobra tiempo
        ICharacter character1 = new Warrior("FIRETITAN", 100, Skill.FIRE); //String name, float defaultLife, Skill skillType
        ICharacter character2 = new Warrior("AIRTITAN", 100, Skill.AIR);
        ICharacter character3 = new Warrior("WATERTITAN", 100, Skill.WATER);
        ICharacter character4 = new Warrior("GANDALF", 100, Skill.WHITE_MAGIC);
        ICharacter character5 = new Warrior("SAURON", 100, Skill.BLACK_MAGIC);
        ICharacter character6 = new Warrior("ELECTRICTITAN", 100, Skill.ELECTRICITY);
        ICharacter character7 = new Warrior("ICETITAN", 100, Skill.ICE);
        ICharacter character8 = new Warrior("IRONGOLEM", 100, Skill.IRON);
        ICharacter character9 = new Warrior("TRIPPER", 100, Skill.ACID);
        ICharacter character10 = new Warrior("JUDASPRIEST", 100, Skill.SPIRITUALITY);

        availableCharacters.addCharacter(character1);
        availableCharacters.addCharacter(character2);
        availableCharacters.addCharacter(character3);
        availableCharacters.addCharacter(character4);
        availableCharacters.addCharacter(character5);
        availableCharacters.addCharacter(character6);
        availableCharacters.addCharacter(character7);
        availableCharacters.addCharacter(character8);
        availableCharacters.addCharacter(character9);
        availableCharacters.addCharacter(character10);

        ITool weapon1 = new Weapon("FIRESWORD", Skill.FIRE.ordinal(), 1); //String name, int type, int simpleUseDecrement
        ITool weapon2 = new Weapon("AIRSWORD", Skill.AIR.ordinal(), 1);
        ITool weapon3 = new Weapon("WATERSPEAR", Skill.WATER.ordinal(), 1);
        ITool weapon4 = new Weapon("LIGHTSWORD", Skill.WHITE_MAGIC.ordinal(), 1);
        ITool weapon5 = new Weapon("BLACKAXE", Skill.BLACK_MAGIC.ordinal(), 1);
        ITool weapon6 = new Weapon("LIGHTNING", Skill.ELECTRICITY.ordinal(), 1);
        ITool weapon7 = new Weapon("BLIZZARD", Skill.ICE.ordinal(), 1);
        ITool weapon8 = new Weapon("MAZE", Skill.IRON.ordinal(), 1);
        ITool weapon9 = new Weapon("TOXICBOMB", Skill.ACID.ordinal(), 1);
        ITool weapon10 = new Weapon("HOLYGRAIL", Skill.SPIRITUALITY.ordinal(), 1);

        availableWeapons.addTool(weapon1);
        availableWeapons.addTool(weapon2);
        availableWeapons.addTool(weapon3);
        availableWeapons.addTool(weapon4);
        availableWeapons.addTool(weapon5);
        availableWeapons.addTool(weapon6);
        availableWeapons.addTool(weapon7);
        availableWeapons.addTool(weapon8);
        availableWeapons.addTool(weapon9);
        availableWeapons.addTool(weapon10);
    }

    public ITool getTool(Skill skill){
        for (int i = 0; i<availableWeapons.getSize(); i++){
            if(availableWeapons.getTool(i).getType() == skill.ordinal()){
                return availableWeapons.removeTool(i);
            }
        }
        return null;
    }

    public ICharacter getCharacter(Skill skill){
        for (int i = 0; i<availableWeapons.getSize(); i++){
            if(((Warrior)availableCharacters.getCharacter(i)).getSkillType().equals(skill)){
                return (ICharacter) availableCharacters.removeCharacter(i);
            }
        }
        return null;
    }
}
