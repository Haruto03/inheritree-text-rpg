package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

/**
 * Represents a condition that can be checked for an NPC in the game.
 * <p>The condition defines certain criteria that must be met for an action or monologue to trigger.</p>
 *
 * <p>Conditions are often used in NPC monologues, behaviors, or actions to determine if a specific action should be performed.</p>
 */
public interface Condition {

    /**
     * Checks if a specific condition is met for the given NPC and actor.
     *
     * <p>This method evaluates the condition based on the state of the NPC, the actor interacting with it,
     * and the current game map. It returns true if the condition is satisfied, otherwise false.</p>
     *
     * @param target the NPC that the condition is being checked for
     * @param actor the actor interacting with the target NPC
     * @param map the current GameMap where the interaction occurs
     * @return true if the condition is met, otherwise false
     */
    boolean check(NPC target, Actor actor, GameMap map);
}
