package Client.Resources;

import BoardElement.Character.CharacterAbstract;
import BoardElement.Tools.ITool;
import BoardElement.Tools.ToolListingFactory;
import Media.IMediaElement;
import Media.MediaListingFactory;

public class Warrior extends CharacterAbstract {
    private int skillType;

    public Warrior(int skillType) {
        this.skillType = skillType;
    }

    public Warrior(String name, float defaultLife, int skillType) {
        super(name, defaultLife);
        this.skillType = skillType;
        super.decrementableLife = defaultLife;
        super.tools = ToolListingFactory.getInstance().getToolListing(ToolListingFactory.TOOL_ARRAY);
        super.media = MediaListingFactory.getInstance().getMediaListing(MediaListingFactory.IMAGE_ARRAY);
    }

    public int getSkillType() {
        return skillType;
    }

    public void setSkillType(int skillType) {
        this.skillType = skillType;
    }

    public void addTool(ITool tool) {
        this.tools.addTool(tool);
    }

    public void addImage(IMediaElement image) {
        this.media.loadMedia(image);
    }
}
