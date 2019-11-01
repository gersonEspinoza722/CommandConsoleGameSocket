package Client.Resources;

import BoardElement.Character.CharacterAbstract;
import BoardElement.Tools.ITool;
import BoardElement.Tools.ToolListingFactory;
import Media.IMediaElement;
import Media.MediaListingFactory;

public class Warrior extends CharacterAbstract {
    private Skill skillType;

    public Warrior(Skill skillType) {
        this.skillType = skillType;
    }

    public Warrior(String name, float defaultLife, Skill skillType) {
        super(name, defaultLife);
        this.skillType = skillType;
        super.decrementableLife = defaultLife;
        super.tools = ToolListingFactory.getInstance().getToolListing(ToolListingFactory.TOOL_ARRAY);
        super.media = MediaListingFactory.getInstance().getMediaListing(MediaListingFactory.IMAGE_ARRAY);
    }

    public Skill getSkillType() {
        return skillType;
    }

    public void setSkillType(Skill skillType) {
        this.skillType = skillType;
    }

    public void addTool(ITool tool) {
        this.tools.addTool(tool);
    }

    public void addImage(IMediaElement image) {
        this.media.loadMedia(image);
    }

    @Override
    public void decLife(int percentage){
        decrementableLife -= defaultLife * percentage / 100;
        if(decrementableLife > 0){
            decrementableLife = 0;
        }
    }

    /*
    @Override
    public void interact(IBoardElement otherElement){ //como seria?
        if(otherElement instanceof ITool){

        }
    }*/
}
