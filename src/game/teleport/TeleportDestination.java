package game.teleport;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a destination for teleportation.
 * Contains the target map and location information.
 */
public class TeleportDestination {
    private final GameMap targetMap;
    private final Location targetLocation;

    /**
     * Constructor for TeleportDestination.
     *
     * @param targetMap the destination game map
     * @param targetLocation the destination location
     */
    public TeleportDestination(GameMap targetMap, Location targetLocation) {
        this.targetMap = targetMap;
        this.targetLocation = targetLocation;
    }

    /**
     * Get the target map.
     * @return the target GameMap
     */
    public GameMap getTargetMap() {
        return targetMap;
    }

    /**
     * Get the target location.
     * @return the target Location
     */
    public Location getTargetLocation() {
        return targetLocation;
    }
}
