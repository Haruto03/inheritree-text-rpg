package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.NPC.NPC;
import game.grounds.GroundCapability;

/**
 * A condition that checks if the NPC is surrounded by cursed grounds.
 * <p>This condition evaluates whether any of the adjacent locations to the NPC's current location contains cursed ground.</p>
 *
 * <p>If an adjacent location has the {@link GroundCapability#CURSED} capability, the condition is satisfied.</p>
 */
public class CursedSurroundCondition implements Condition {

    /**
     * Checks if the NPC is surrounded by cursed ground.
     * <p>This method checks all the adjacent locations to the NPC's current position. If any of them contain cursed ground,
     * the condition will return true, indicating that the NPC is under the influence of cursed surroundings.</p>
     *
     * @param target the NPC for whom the condition is being checked
     * @param actor the actor interacting with the NPC
     * @param map the current GameMap where the interaction occurs
     * @return true if any adjacent location contains cursed ground, otherwise false
     */
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        // Check all exits from the current location of target (NPC)
        for (Exit exit : map.locationOf(target).getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.getGround().hasCapability(GroundCapability.CURSED)) {
                return true; // If any adjacent location is cursed, return true
            }
        }
        return false; // Return false if no cursed ground is found
    }
}
