package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import game.actors.creatures.GoldenBeetle; // Will be created in a later step
import game.grounds.GroundCapability;

public class GoldenEgg extends Egg {

    private static final int STAMINA_RESTORE_ON_EAT = 20;

    public GoldenEgg() {
        super("Golden Egg", '0', true, Integer.MAX_VALUE);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }

    @Override
    public boolean canproduce(Location currentLocation) {
        if (currentLocation == null) return false;
        for (Exit exit : currentLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.getGround().hasCapability(GroundCapability.CURSED)) {
                return true;
            }
        }
        return currentLocation.getGround().hasCapability(GroundCapability.CURSED);
    }

    @Override
    public String produce(Location currentLocation, GameMap map) {
        if (map.isAnActorAt(currentLocation)) {
            return this.toString() + " at (" + currentLocation.x() + "," + currentLocation.y() + ") cannot hatch: location occupied.";
        }
        currentLocation.removeItem(this);
        // GoldenBeetle might not exist yet if committing strictly step-by-step.
        // This line can be commented out or use a placeholder if GoldenBeetle isn't created.
        // For now, we assume it will be available for the full feature.
        map.addActor(new GoldenBeetle(), currentLocation);
        return "A Golden Beetle hatches from the " + this + " at (" + currentLocation.x() + "," + currentLocation.y() + ") near a cursed entity!";
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {
        String message = eater + " eats the " + this;
        if (eater.hasAttribute(BaseActorAttributes.STAMINA)) {
            eater.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, STAMINA_RESTORE_ON_EAT);
            message += " and restores " + STAMINA_RESTORE_ON_EAT + " stamina.";
        } else {
            message += ", but feels no change in stamina.";
        }
        eater.removeItemFromInventory(this);
        return message;
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor + " eats " + this + " (Stamina +" + STAMINA_RESTORE_ON_EAT + ")";
    }
}