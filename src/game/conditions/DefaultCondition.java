package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

/**
 * A default condition that always returns true.
 * <p>This condition is used when no specific condition is required, meaning it will always evaluate to true.</p>
 *
 * <p>It can be used as a fallback or a default condition when no other condition applies.</p>
 */
public class DefaultCondition implements Condition {

    /**
     * Always returns true, representing a condition that is always satisfied.
     * <p>This method is used when there are no specific requirements for the condition. It will always return true,
     * meaning the condition is always met.</p>
     *
     * @param target the NPC for whom the condition is being checked
     * @param actor the actor interacting with the NPC
     * @param map the current GameMap where the interaction occurs
     * @return true, as this condition is always satisfied
     */
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        return true; // This condition is always met
    }
}
