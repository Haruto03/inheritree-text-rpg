package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

/**
 * A condition that checks if the actor's health is below a certain threshold.
 * <p>This condition is used to determine whether an actor has low health, based on a predefined threshold.</p>
 */
public class LowHealthCondition implements Condition {

    /**
     * The health threshold to determine if the actor has low health.
     * <p>This value represents the health level below which an actor is considered to have low health.</p>
     */
    private static final int LOW_HEALTH = 50;

    /**
     * Checks if the actor's health is below the low health threshold.
     * <p>This method compares the actor's current health with the predefined threshold. If the actor's health is lower
     * than the threshold, the condition is satisfied (returns true), otherwise it returns false.</p>
     *
     * @param target the NPC for whom the condition is being checked
     * @param actor the actor interacting with the NPC
     * @param map the current GameMap where the interaction occurs
     * @return true if the actor's health is below the low health threshold, false otherwise
     */
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        return actor.getAttribute(BaseActorAttributes.HEALTH) < LOW_HEALTH; // Checks if actor's health is below the threshold
    }
}
