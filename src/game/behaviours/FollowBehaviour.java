package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.capabilities.GeneralCapability; // For FOLLOWABLE capability

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A behaviour that allows an actor to follow a target.
 * The target is dynamically found if not already set or if the current target is invalid.
 * It will attempt to move one step closer to the target if a path that reduces
 * Manhattan distance is available.
 */
public class FollowBehaviour implements Behaviour {

    private Actor currentTarget = null; // The actor being currently followed

    /**
     * Constructor.
     */
    public FollowBehaviour() {
        // No specific target is set at construction; it will be found dynamically.
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // 1. Validate current target
        if (currentTarget != null &&
                (!map.contains(currentTarget) ||
                        !currentTarget.isConscious() ||
                        !currentTarget.hasCapability(GeneralCapability.FOLLOWABLE))) {
            currentTarget = null; // Invalidate target
        }

        // 2. If no current target, try to find a new one in the surroundings
        if (currentTarget == null) {
            Location here = map.locationOf(actor);
            // Check adjacent tiles for a followable actor
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (map.isAnActorAt(destination)) {
                    Actor potentialTarget = map.getActorAt(destination);
                    if (potentialTarget.hasCapability(GeneralCapability.FOLLOWABLE) && potentialTarget.isConscious()) {
                        currentTarget = potentialTarget; // Found a new target
                        break; // Follow the first one found
                    }
                }
            }
        }

        // 3. If a valid target exists, attempt to move closer
        if (currentTarget != null) {
            Location here = map.locationOf(actor);
            Location there = map.locationOf(currentTarget);

            // Don't move if already at the target's location (or very close, e.g. distance 1)
            // The original demo FollowBehaviour only moves if distance > 1 effectively,
            // as it checks for newDistance < currentDistance. If already at distance 1,
            // no move will make it < 1 (unless on top, which is usually not allowed).
            int currentDistance = distance(here, there);
            if (currentDistance <= 1 && !here.equals(there)) { // Already adjacent or at a good range
                return null; // Stop trying to move closer if already next to target.
                // Or could return a specific "guard" or "interact" action later.
            }


            Action preferredAction = null;
            int bestDistance = currentDistance;

            // Shuffle exits to avoid biased movement when multiple paths have the same best distance
            List<Exit> exits = new ArrayList<>(here.getExits());
            Collections.shuffle(exits);

            for (Exit exit : exits) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < bestDistance) { // Strictly move closer
                        bestDistance = newDistance;
                        // Use MoveActorAction from engine.actions
                        preferredAction = new MoveActorAction(destination, exit.getName());
                    }
                }
            }
            return preferredAction; // This will be null if no path strictly reduces distance
        }

        return null; // No target or no way to move closer
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Allows external clearing of the target, for example, if the actor's state changes.
     * (Optional, depending on whether other parts of the game need to force a target reset).
     */
    public void resetTarget() {
        this.currentTarget = null;
    }
}