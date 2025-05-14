package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A condition that checks whether the actor's inventory is empty.
 * <p>This condition evaluates to true if the actor interacting with the NPC has no items in their
 * inventory.</p>
 */
public class EmptyInventoryCondition implements Condition {

    private final Actor actor;

    public EmptyInventoryCondition(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean check() {
        return actor.getItemInventory().isEmpty(); // Checks if actor's inventory is empty
    }
}
