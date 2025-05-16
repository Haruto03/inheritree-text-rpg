package game.hatching;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.GoldenBeetle;
import game.capabilities.GeneralCapability;
import game.conditions.NearbyCapabilityCondition;
import java.util.ArrayList;

public class GoldenBeetleEgg extends Egg {


    private static final int STAMINA_RESTORE_ON_EAT = 20;

    public GoldenBeetleEgg() {
        super("Golden Beetle Egg");
    }

    @Override
    public ArrayList<HatchingRules> getHatchingRules(Location currentLocation) {
        ArrayList<HatchingRules> hatchingRules = new ArrayList<>();
        hatchingRules.add(
                new HatchingRules(
                        new NearbyCapabilityCondition(currentLocation, GeneralCapability.CURSED),
                        GoldenBeetle::new));

        return hatchingRules;
    }


    @Override
    public void tick(Location currentLocation) {
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

}