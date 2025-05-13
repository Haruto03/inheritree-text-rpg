
package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.function.Supplier;

/**
 * A {@link HatchingCondition} that allows an egg to hatch into a default creature
 * after it has been on the ground for a specified number of turns.
 *
 * @version 1.1 (Renamed from DefaultTimedHatchRule, implements HatchingCondition)
 * @see HatchingCondition
 * @see Egg#getTurnsOnGroundCounter()
 */
public class DefaultTimedHatchCondition implements HatchingCondition {
    private final int requiredTurnsOnGround;
    private final Supplier<Actor> creatureSupplier;

    public DefaultTimedHatchCondition(int requiredTurnsOnGround, Supplier<Actor> creatureSupplier) {
        this.requiredTurnsOnGround = requiredTurnsOnGround;
        this.creatureSupplier = creatureSupplier;
    }

    /**
     * Checks if the egg has been on the ground for at least the required number of turns.
     *
     * @param egg             The egg instance, used to get its {@code turnsOnGroundCounter}.
     * @param currentLocation The current location of the egg.
     * @param map             The game map.
     * @return {@code true} if the egg's {@code turnsOnGroundCounter} meets or exceeds {@code requiredTurnsOnGround},
     * {@code false} otherwise.
     */
    @Override
    public boolean meetsCondition(Egg egg, Location currentLocation, GameMap map) { // RENAMED from conditionMet
        return egg.getTurnsOnGroundCounter() >= this.requiredTurnsOnGround;
    }

    @Override
    public Actor hatchCreature(Egg egg, Location currentLocation, GameMap map) {
        return creatureSupplier.get();
    }

    @Override
    public String getHatchMessage(Actor hatchedCreature, Egg egg, Location currentLocation) {
        return "An " + hatchedCreature.toString() + " hatches from the " + egg.toString() +
                " at (" + currentLocation.x() + "," + currentLocation.y() + ")!";
    }
}
// SNIPPET_ENDS