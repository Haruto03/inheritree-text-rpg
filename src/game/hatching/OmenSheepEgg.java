package game.hatching;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.OmenSheep;
import game.conditions.TurnBasedCondition;
import java.util.ArrayList;

public class OmenSheepEgg extends Egg {

    private static final int HATCH_DURATION = 3;
    private static final int MAX_HEALTH_BOOST = 10;

    private int turnOnGround = 0;

    public OmenSheepEgg() {
        super("OmenSheep Egg");
    }

    @Override
    public ArrayList<HatchingRules> getHatchingRules(Location currentLocation) {
        ArrayList<HatchingRules> hatchingRules = new ArrayList<>();
        hatchingRules.add(new HatchingRules(new TurnBasedCondition(turnOnGround, HATCH_DURATION),
                OmenSheep::new));

        return hatchingRules;
    }

    @Override
    public void tick(Location currentLocation) {
        turnOnGround++;
        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        turnOnGround = 0;
    }


    // --- Eatable Implementation ---
    @Override
    public String eatenBy(Actor eater, GameMap map) {
        String message = eater + " eats the " + this;
        // Farmer specific effect
        // Increase Farmer's maximum health by 10 points.
        // This relies on Player (Farmer) having its health managed by BaseActorAttributes.HEALTH
        // and supporting modification of its maximum.
        eater.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,
                MAX_HEALTH_BOOST);
        message += " and feels invigorated. Max HP increased by " + MAX_HEALTH_BOOST + "!";
        eater.removeItemFromInventory(this); // Egg is consumed
        return message;
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor.toString() + " eats " + this + " (Max HP +" + MAX_HEALTH_BOOST + ")";

    }

}