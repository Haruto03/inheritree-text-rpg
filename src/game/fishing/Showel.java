package game.fishing;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.capabilities.GeneralCapability;

public class Showel extends Item implements Fishable {

    private static final double CATCH_RATE = 0.8;

    public Showel() {
        super("Showel", 'S', true);
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
}
