package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.actors.creatures.OmenSheep;
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
     * Constructor.
     */
    public SheepEgg() {
        super("Sheep Egg", '0', true, HATCH_DURATION);
        // Note: EatAction is added via Egg.allowableActions or specific logic here
        // If EatAction is always present for SheepEgg:
        // this.addAction(new EatAction(this)); // Item class does not have addAction directly for general actions.
        // Actions are typically added in getAllowableActions or allowableActions.
    }

    // --- Hatchable Implementation ---
    @Override
    public boolean canproduce(Location currentLocation) {
        // Hatches only if on the ground and turnsOnGroundCounter met
        // (currentLocation.getActor() == null) is implicitly handled by Item.tick(Location) being called for ground items.
        return turnsOnGroundCounter >= turnsToHatch;
    }

    @Override
    public String produce(Location currentLocation, GameMap map) {
        if (map.isAnActorAt(currentLocation)) {
            return this.toString() + " at (" + currentLocation.x() + "," + currentLocation.y() + ") cannot hatch: location occupied.";
        }
        // Remove egg from ground before adding actor
        currentLocation.removeItem(this);
        OmenSheep newSheep = new OmenSheep(); // Assumes OmenSheep is in 'package game;' or imported
        map.addActor(newSheep, currentLocation);
        return "An Omen Sheep hatches from the " + this.toString() + " at (" + currentLocation.x() + "," + currentLocation.y() + ")!";
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