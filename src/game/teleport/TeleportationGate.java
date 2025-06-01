package game.teleport;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;
import java.util.List;

/**
 * A teleportation gate that allows actors to travel between different maps.
 * The gate is represented by the character 'A' and can have multiple destinations.
 */
public class TeleportationGate extends Ground {
    private final List<TeleportDestination> destinations;

    /**
     * Constructor for TeleportationGate.
     * Initializes the gate with the character 'A'.
     */
    public TeleportationGate() {
        super('A', "TeleportationGate");
        this.destinations = new ArrayList<>();
    }

    /**
     * Add a destination to this teleportation gate.
     *
     * @param targetMap      the destination game map
     * @param targetLocation the destination location
     */
    public void addDestination(GameMap targetMap, Location targetLocation) {
        destinations.add(new TeleportDestination(targetMap, targetLocation));
    }

    /**
     * Returns the actions that can be performed on this teleportation gate.
     * Provides teleportation options to all configured destinations.
     *
     * @param actor     the actor performing the action
     * @param location  the current location
     * @param direction the direction of the action (not used for teleportation)
     * @return a list of possible teleport actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Only allow teleportation if the actor is standing ON this teleportation gate
        if (location.getGround() == this && location.containsAnActor() && location.getActor() == actor) {
            // Add teleport actions for each destination
            for (TeleportDestination destination : destinations) {
                // Only add teleport action if the destination is different from current location
                if (!isSameLocation(location, destination)) {
                    actions.add(new TeleportAction(destination));
                }
            }
        }
        return actions;
    }

    /**
     * Check if the current location is the same as the destination.
     *
     * @param currentLocation the current location
     * @param destination     the destination to check
     * @return true if locations are the same, false otherwise
     */
    private boolean isSameLocation(Location currentLocation, TeleportDestination destination) {
        return currentLocation.map() == destination.getTargetMap() &&
                currentLocation.x() == destination.getTargetLocation().x() &&
                currentLocation.y() == destination.getTargetLocation().y();
    }

    /**
     * Check if actors can enter this teleportation gate.
     * Teleportation gates allow actors to step on them.
     *
     * @param actor the actor attempting to enter
     * @return true, as actors can step on teleportation gates
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}