package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeBeetleAction extends Action {

    private final Actor targetBeetle;
    private final int healAmount = 15;
    private final int runesGained = 1000;

    public ConsumeBeetleAction(Actor targetBeetle) {
        this.targetBeetle = targetBeetle;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(healAmount);
        actor.addBalance(runesGained);
        map.removeActor(targetBeetle);

        return actor + " consumes " + targetBeetle + ", healing for " + healAmount + " HP and gaining " + runesGained + " runes.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + targetBeetle;
    }
}