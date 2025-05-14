package game.hatching;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.GoldenBeetle;
import game.capabilities.GeneralCapability;
import game.conditions.NearbyCapabilityCondition;
import game.conditions.provider.LocationProvider;

public class GoldenBeetleEgg extends Egg implements LocationProvider {


    private Location currentLocationOnGround;
    private static final int STAMINA_RESTORE_ON_EAT = 20;

    public GoldenBeetleEgg() {
        super("Golden Beetle Egg");
    }

    @Override
    public void initHatchingRules() {
        this.addHatchingRules(new NearbyCapabilityCondition(this, GeneralCapability.CURSED),
                GoldenBeetle::new);
    }

    @Override
    public void tick(Location currentLocation) {
        this.currentLocationOnGround = currentLocation;
        super.tick(currentLocation);
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {
        String message = eater + " eats the " + this;
        if (eater.hasAttribute(BaseActorAttributes.STAMINA)) {
            eater.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
                    STAMINA_RESTORE_ON_EAT);
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

    @Override
    public Location getLocation() {
        return currentLocationOnGround;
    }
}