package game;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for items (like eggs) that can hatch or produce an Actor.
 */
public interface ItemProducible {
    /**
     * Checks if the item can hatch in the current turn.
     * @param currentLocation The location of the item.
     * @return true if it can hatch, false otherwise.
     */
    boolean canproduce(Location currentLocation);

    /**
     * Performs the hatching process, typically by spawning a new Actor.
     * @param currentLocation The location where the item is.
     * @param map The game map.
     * @return A string describing the hatching event, or null if no message.
     */
    String produce(Location currentLocation, GameMap map);
}