package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

/**
 * A condition that checks whether the actor's inventory is empty.
 * <p>This condition evaluates to true if the actor interacting with the NPC has no items in their inventory.</p>
 */
public class EmptyInventoryCondition implements Condition {

    /**
     * Checks if the actor's inventory is empty.
     * <p>This method checks whether the actor has no items in their inventory. If the inventory is empty,
     * the condition is satisfied (returns true), otherwise it returns false.</p>
     *
     * @param target the NPC for whom the condition is being checked
     * @param actor the actor interacting with the NPC
     * @param map the current GameMap where the interaction occurs
     * @return true if the actor's inventory is empty, false otherwise
     */
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        return actor.getItemInventory().isEmpty(); // Checks if actor's inventory is empty
    }
}
