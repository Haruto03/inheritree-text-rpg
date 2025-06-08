package game.growingparts;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class GrowPartAction extends Action {

    private final Growable grower;

    public GrowPartAction(Growable grower) {
        this.grower = grower;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor != grower) {
            return null;
        }
        return actor + " is growing...\n" + grower.attemptGrow();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...\n";
    }
}