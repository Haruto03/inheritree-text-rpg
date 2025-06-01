package game.teleport;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Action to teleport an actor to a different location/map.
 */
public class TeleportAction extends Action {
    private final TeleportDestination destination;

    /**
     * Constructor for TeleportAction.
     *
     * @param destination the destination to teleport to
     */
    public TeleportAction(TeleportDestination destination) {
        this.destination = destination;
    }

    /**
     * Execute the teleportation.
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a description of the action result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = destination.getTargetLocation();
        GameMap targetMap = destination.getTargetMap();

        // Check if target location is valid
        if (targetLocation == null || targetMap == null) {
            return actor + " failed to teleport - invalid destination!";
        }

        // Check if target location is already occupied
        if (targetLocation.containsAnActor()) {
            return actor + " failed to teleport - destination is blocked!";
        }

        // Check if actor can enter the target location
        if (!targetLocation.canActorEnter(actor)) {
            return actor + " failed to teleport - cannot enter destination!";
        }

        // All checks passed - perform teleportation
        map.removeActor(actor);
        targetMap.addActor(actor, targetLocation);

        return actor + " teleports to " + destination.getTargetLocation();
    }

    /**
     * Returns a description of this action suitable for displaying in a menu.
     *
     * @param actor the actor performing the action
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + destination.getTargetLocation();
    }
}