
package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a specific condition and outcome for an egg hatching.
 * This allows for multiple, prioritized hatching conditions for a single egg type.
 *
 * @version 1.1 (Renamed from HatchingRule)
 * @see Egg
 * @see SheepEgg
 */
public interface HatchingCondition {
    /**
     * Checks if the conditions for this specific hatching condition are met for the given egg
     * at its current location.
     *
     * @param egg             The egg instance checking its conditions.
     * @param currentLocation The current location of the egg on the map.
     * @param map             The game map.
     * @return {@code true} if the conditions for this condition are met, {@code false} otherwise.
     */
    boolean meetsCondition(Egg egg, Location currentLocation, GameMap map);

    /**
     * Creates and returns the Actor (creature) that should hatch according to this condition.
     * This method is typically called only if {@link #meetsCondition(Egg, Location, GameMap)} returns true.
     *
     * @param egg             The egg that is hatching.
     * @param currentLocation The location where the egg is.
     * @param map             The game map.
     * @return The {@link Actor} instance representing the hatched creature.
     */
    Actor hatchCreature(Egg egg, Location currentLocation, GameMap map);

    /**
     * Gets the message to display when this condition results in a successful hatch.
     *
     * @param hatchedCreature The creature that hatched.
     * @param egg             The egg that hatched.
     * @param currentLocation The location of the hatch.
     * @return A descriptive string for the hatching event.
     */
    String getHatchMessage(Actor hatchedCreature, Egg egg, Location currentLocation);
}
