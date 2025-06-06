package game.fishing;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.capabilities.GeneralCapability;

public class Shovel extends Item implements Fishable {

    private static final double CATCH_RATE = 0.8;

    public Shovel() {
        super("Shovel", 'S', true);
        this.addCapability(GeneralCapability.CAN_DIG);

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
        // Shovel has no special effect when caught
    }
}
