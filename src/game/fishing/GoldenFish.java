package game.fishing;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.GeneralCapability;
import game.eating.EatAction;
import game.eating.Eatable;

public class GoldenFish extends Item implements Eatable, Fishable {

    private static final double CATCH_RATE = 0.05;

    public GoldenFish() {
        super("Golden Fish", 'G', true);
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        if (owner.hasCapability(GeneralCapability.CONSUMER)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }

    @Override
    public double getCatchChance() {
        return CATCH_RATE;
    }

    @Override
    public String catchBy(Actor actor) {
        actor.addItemToInventory(this);

        actor.addBalance(1000);
        
        return actor + " catches a shimmering Golden Fish! It feels like a stroke of luck. 1000 coins added to balance!";
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {
        eater.removeItemFromInventory(this);
        eater.heal(50); // A very powerful heal
        return eater + " eats the shimmering Golden Fish. It feels invigorating!";
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor + " eats the Golden Fish";
    }
}
