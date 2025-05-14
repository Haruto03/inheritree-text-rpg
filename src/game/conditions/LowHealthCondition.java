package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * A condition that checks if the actor's health is below a certain threshold.
 * <p>This condition is used to determine whether an actor has low health, based on a predefined
 * threshold.</p>
 */
public class LowHealthCondition implements Condition {

    /**
     * The health threshold to determine if the actor has low health.
     * <p>This value represents the health level below which an actor is considered to have low
     * health.</p>
     */
    private static final int LOW_HEALTH = 50;

    private final Actor actor;

    public LowHealthCondition(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean check() {
        return actor.getAttribute(BaseActorAttributes.HEALTH)
                < LOW_HEALTH;
    }
}
