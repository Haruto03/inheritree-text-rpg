package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Defines the properties and per-turn effect of burning ground.
 * This class itself does not manage duration or revert to original ground;
 * that is handled by TemporyGround.
 */
public class BurningGround extends Ground {

    private static final char FIRE_DISPLAY_CHAR = '^';
    private static final int DAMAGE_PER_TURN_FROM_FIRE = 5;

    /**
     * Constructor for BurningEffectProperties.
     */
    public BurningGround() {

        super(FIRE_DISPLAY_CHAR, "Burning Ground");

    }

    /**
     * Applies the per-turn effect of this burning property (e.g., damage to an actor).
     * This method is called by TemporyGround's tick method.
     *
     * @param currentLocation The location where the burning effect is active.
     */
    public void tick(Location currentLocation) {
        if (currentLocation.containsAnActor()) {
            Actor victim = currentLocation.getActor();
            if (victim != null) {

                victim.hurt(DAMAGE_PER_TURN_FROM_FIRE);
                if (!victim.isConscious()) {
                    victim.unconscious(currentLocation.map());

                }
            }
        }
    }
}