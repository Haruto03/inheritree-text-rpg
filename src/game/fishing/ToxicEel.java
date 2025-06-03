package game.fishing;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.GeneralCapability;
import game.eating.EatAction;
import game.eating.Eatable;

public class ToxicEel extends PondItem implements Eatable{
    
    private static final double catchChance = 0.3;

    public ToxicEel() {
        super("Toxic Eel", 'C', catchChance);
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
        return catchChance;
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {
        eater.removeItemFromInventory(this);

        return eater + " eats " + this + ".";
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor + " eat " + this;
    }
}
