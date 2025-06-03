package game.grounds; // Assuming this is the correct package

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Defines the properties and per-turn effect of burning ground.
 * This class itself does not manage duration or revert to original ground;
 * that is handled by TemporyGround.
 */
public class BurningGround extends Ground {

    private static final char FIRE_DISPLAY_CHAR = '^'; // Or your preferred character for fire
    private static final int DAMAGE_PER_TURN_FROM_FIRE = 5; // Example damage per turn

    /**
     * Constructor for BurningEffectProperties.
     */
    public BurningGround() {
        // Corrected super constructor call
        super(FIRE_DISPLAY_CHAR, "Burning Ground"); // Provide a name for the ground type
        // You might add specific capabilities here if your Ground system uses them
        // e.g., this.addCapability(SpecificGameCapability.IS_FIRE_EFFECT);
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
                // It's good practice to have a distinct message or source for tick damage

                victim.hurt(DAMAGE_PER_TURN_FROM_FIRE);
                if (!victim.isConscious()) {
                    // Handle unconsciousness/death from per-turn damage
                    // e.g., map.actorDied(victim); or victim.unconscious(map);
                    // Ensure the unconscious method is called correctly, it might need just the map
                    // or also the actor responsible if applicable.
                    // Based on FireSpell, unconscious(map) seems to be the way
                    victim.unconscious(currentLocation.map());

                }
            }
        }
    }
}