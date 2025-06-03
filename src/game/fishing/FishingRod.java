package game.fishing;
import edu.monash.fit2099.engine.items.Item;
import game.capabilities.GeneralCapability;

public class FishingRod extends Item{
    public FishingRod() {
        super("Fishing Rod", 'R', true);
        this.addCapability(GeneralCapability.CAN_FISH);
    }
}
