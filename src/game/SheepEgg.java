package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.actors.creatures.OmenSheep;

import java.util.ArrayList;
import java.util.List;
// Assuming OmenSheep is in 'package game;' due to the "everything just write package game;" constraint.
// If OmenSheep is in game.actors.creatures, then import game.actors.creatures.OmenSheep;

/**
 * Represents an egg laid by an Omen Sheep.
 * It can hatch into an Omen Sheep or be eaten by the Farmer (Player).
 */
public class SheepEgg extends Egg {

    private static final int HATCH_DURATION = 3;    // Hatches in 3 turns on the ground
    private static final int MAX_HEALTH_BOOST = 10; // For Farmer when eaten
    /**
     * A list of {@link HatchingCondition}s that determine how this egg can hatch.
     * Conditions are evaluated in the order they appear in this list.
     */
    private final List<HatchingCondition> hatchingConditions;
    /**
     * Constructor.
     */
    public SheepEgg() {
        super("Sheep Egg", '0', true, HATCH_DURATION);
        // Note: EatAction is added via Egg.allowableActions or specific logic here
        // If EatAction is always present for SheepEgg:
        // this.addAction(new EatAction(this)); // Item class does not have addAction directly for general actions.
        // Actions are typically added in getAllowableActions or allowableActions.
        this.hatchingConditions = new ArrayList<>();        // Add hatching conditions in order of desired priority.
        hatchingConditions.add(new CursedEnvironmentHatchCondition()); // Uses new class name
        hatchingConditions.add(new DefaultTimedHatchCondition(HATCH_DURATION, OmenSheep::new));
    }

    // --- Hatchable Implementation ---
    /**
     * Checks if any of this egg's {@link HatchingCondition}s allow it to hatch at its current location and game state.
     * It iterates through the {@code hatchingConditions} list and returns {@code true} if any condition's
     * {@link HatchingCondition#meetsCondition(Egg, Location, GameMap)} method returns {@code true}.
     *
     * @param currentLocation The current location of the egg on the map.
     * @return {@code true} if at least one hatching condition's conditions are met, {@code false} otherwise.
     */
    @Override
    public boolean canproduce(Location currentLocation) {
        if (currentLocation == null) return false;
        GameMap map = currentLocation.map();
        if (map == null) return false;

        for (HatchingCondition condition : hatchingConditions) { // Iterate over hatchingConditions
            if (condition.meetsCondition(this, currentLocation, map)) { // Call meetsCondition
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to hatch the egg by iterating through its {@link HatchingCondition}s in order of priority.
     * The first condition for which {@link HatchingCondition#meetsCondition(Egg, Location, GameMap)} is true
     * will have its {@link HatchingCondition#hatchCreature(Egg, Location, GameMap)} method called.
     * If a creature is successfully hatched, the egg item is removed from the map, and a descriptive
     * message from the condition is returned.
     *
     * @param currentLocation The current location of the egg on the map.
     * @param map             The {@link GameMap} where the egg is located.
     * @return A string describing the hatching event. If the location is occupied, a specific message is returned.
     * If no hatching condition's conditions are met, a generic failure message is returned.
     */
    @Override
    public String produce(Location currentLocation, GameMap map) {
        if (map.isAnActorAt(currentLocation)) {
            return this.toString() + " at (" + currentLocation.x() + "," + currentLocation.y() + ") cannot hatch: location occupied.";
        }

        for (HatchingCondition condition : hatchingConditions) { // Iterate over hatchingConditions
            if (condition.meetsCondition(this, currentLocation, map)) { // Call meetsCondition
                Actor hatchedCreature = condition.hatchCreature(this, currentLocation, map);
                if (hatchedCreature != null) {
                    currentLocation.removeItem(this);
                    map.addActor(hatchedCreature, currentLocation);
                    return condition.getHatchMessage(hatchedCreature, this, currentLocation);
                }
            }
        }
        return this.toString() + " at (" + currentLocation.x() + "," + currentLocation.y() + ") did not meet any hatching conditions this turn (internal produce logic).";
    }

    // --- Eatable Implementation ---
    @Override
    public String eatenBy(Actor eater, GameMap map) {
        String message = eater.toString() + " eats the " + this.toString();
        // Farmer specific effect
            // Increase Farmer's maximum health by 10 points.
            // This relies on Player (Farmer) having its health managed by BaseActorAttributes.HEALTH
            // and supporting modification of its maximum.
            eater.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, MAX_HEALTH_BOOST);
            message += " and feels invigorated. Max HP increased by " + MAX_HEALTH_BOOST + "!";
        eater.removeItemFromInventory(this); // Egg is consumed
        return message;
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
            return actor.toString() + " eats " + this.toString() + " (Max HP +" + MAX_HEALTH_BOOST + ")";

    }
}