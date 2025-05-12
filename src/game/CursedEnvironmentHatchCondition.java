
package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.GoldenBeetle;
import game.capabilities.GeneralCapability;
import game.grounds.GroundCapability;

/**
 * A {@link HatchingCondition} that allows an egg to hatch into a {@link GoldenBeetle}
 * if it is in the proximity of ground with {@link GroundCapability#CURSED}.
 *
 * @version 1.2 (Renamed from CursedEnvironmentHatchRule, implements HatchingCondition)
 * @see HatchingCondition
 * @see GoldenBeetle
 * @see GroundCapability#CURSED
 */
public class CursedEnvironmentHatchCondition implements HatchingCondition {

    /**
     * Checks if the egg is on or adjacent to any ground that has the
     * {@link GroundCapability#CURSED} capability.
     *
     * @param egg             The egg instance.
     * @param currentLocation The current location of the egg.
     * @param map             The game map.
     * @return {@code true} if cursed ground is found at or adjacent to the egg's location, {@code false} otherwise.
     */
    @Override
    public boolean meetsCondition(Egg egg, Location currentLocation, GameMap map) {
        if (currentLocation == null || map == null) {
            return false;
        }

        if (currentLocation.getGround().hasCapability(GroundCapability.CURSED)) {
            return true;
        }

        for (Exit exit : currentLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.getGround().hasCapability(GroundCapability.CURSED)) {
                return true;
            }

            for (Item itemOnTile : adjacentLocation.getItems()) {
                if (itemOnTile.hasCapability(GeneralCapability.CURSED_AURA)) {
                    return true;
                }
            }
            if (map.isAnActorAt(adjacentLocation)) {
                Actor adjacentActor = map.getActorAt(adjacentLocation);
                if (adjacentActor != null && adjacentActor.hasCapability(GeneralCapability.CURSED_AURA)) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public Actor hatchCreature(Egg egg, Location currentLocation, GameMap map) {
        return new GoldenBeetle();
    }

    @Override
    public String getHatchMessage(Actor hatchedCreature, Egg egg, Location currentLocation) {
        return "A " + hatchedCreature.toString() + " writhes out from the " + egg.toString() +
                " near cursed ground at (" + currentLocation.x() + "," + currentLocation.y() + ")!";
    }
}
