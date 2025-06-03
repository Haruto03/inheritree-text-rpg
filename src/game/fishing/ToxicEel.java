package game.fishing;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.GeneralCapability;
import game.eating.EatAction;
import game.eating.Eatable;

public class ToxicEel extends Item implements Eatable, Fishable {

    private static final double CATCH_RATE = 0.5;

    public ToxicEel() {
        super("Toxic Eel", 'C', true);
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map); // Includes DropAction if portable
        if (owner.hasCapability(GeneralCapability.CONSUMER)) {
            actions.add(new EatAction(this)); // Allow eating if owner is a consumer
        }
        return actions;
    }

    public double getCatchChance() {
        return CATCH_RATE;
    }

    @Override
    public void catchBy(Actor actor) {
        actor.addItemToInventory(this);
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {
        eater.removeItemFromInventory(this);
        eater.hurt(10);
        return eater + " eats " + this + ".";
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor + " eat " + this;
    }
}
