package game.fishing;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.capabilities.GeneralCapability;

public class Shovel extends Item implements Fishable {

    private static final double CATCH_RATE = 0.6;

    public Shovel() {
        super("Shovel", 'S', true);
        this.addCapability(GeneralCapability.CAN_DIG);

    }

    @Override
    public double getCatchChance() {
        return CATCH_RATE;
    }

    @Override
    public String catchBy(Actor actor) {
        actor.addItemToInventory(this);
        return actor + " catches a Shovel. It seems like a lucky day... or not. What can it do?";
    }
}
