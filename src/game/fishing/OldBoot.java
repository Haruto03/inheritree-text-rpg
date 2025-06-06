package game.fishing;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A piece of junk that can be caught while fishing.
 * It serves no purpose other than cluttering inventory.
 */
public class OldBoot extends Item implements Fishable {

    private static final double CATCH_RATE = 0.4; 

    /**
     * Constructor.
     */
    public OldBoot() {
        super("Old Boot", 'b', true);
    }

    @Override
    public double getCatchChance() {
        return CATCH_RATE;
    }

    @Override
    public void catchBy(Actor actor) {
        actor.addItemToInventory(this);
    }

    @Override 
    public void fishedEffect(Actor actor) {
        // OldBoot has no special effect when caught
    }

}
