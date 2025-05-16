package game.eating;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action that allows an Actor to eat an Eatable target.
 */
public class EatAction extends Action {

    private final Eatable target;

    /**
     * Constructor.
     *
     * @param target The Eatable target to be eaten.
     */
    public EatAction(Eatable target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.target.eatenBy(actor, map);
        }


    @Override
    public String menuDescription(Actor actor) {
        // Get the specific menu description from the Eatable item
        return this.target.getEatMenuDescription(actor);
    }
}