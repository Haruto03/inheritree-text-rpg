package game.plants;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a Bloodrose plant, a type of {@link Plant} grown from a {@link Seed}. Bloodroses have
 * harmful effects both instantly upon planting and periodically each turn. They drain health and
 * stamina from the planter instantly and damage adjacent actors each turn. Represented by 'w' on
 * the map.
 */
public class Bloodrose extends Plant {

    /**
     * The amount of damage dealt each turn to actors in adjacent locations.
     */
    private static final int DAMAGE_TO_SURROUNDING = 10;
    /**
     * The amount of health drained from the actor who plants the Bloodrose upon planting.
     */
    private static final int DAMAGE_TO_PLANTER = 5;
    /**
     * The amount of stamina drained from the actor who plants the Bloodrose upon planting.
     */
    private static final int STAMINA_COST_WHEN_PLANT = 5;

    /**
     * Constructor for the Bloodrose. Initializes the plant with display character 'w' and name
     * "Blood rose".
     */
    public Bloodrose() {
        super('w', "Blood rose");
    }

    /**
     * Executes the instant effects that occur when the Bloodrose is planted. This includes
     * decreasing the planter's stamina by {@value #STAMINA_COST_WHEN_PLANT} and hurting the planter
     * for {@value #DAMAGE_TO_PLANTER} health points.
     *
     * @param planter  The actor who planted the Bloodrose.
     * @param location The location where the Bloodrose was planted.
     * @return A string describing the health and stamina drain inflicted on the planter.
     */
    @Override
    public String executeInstantEffects(Actor planter, Location location) {
        // Drain stamina from the planter
        planter.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,
                Bloodrose.STAMINA_COST_WHEN_PLANT);
        // Damage the planter
        planter.hurt(DAMAGE_TO_PLANTER);
        if (!planter.isConscious()) {
            planter.unconscious(location.map());
        }
        // Return descriptive message
        return " The Bloodrose saps " + DAMAGE_TO_PLANTER + " health from " + planter + ".\n"
                + "The Bloodrose also saps " + STAMINA_COST_WHEN_PLANT + " stamina from " + planter
                + ".";
    }

    /**
     * Called once per turn, allowing the Bloodrose to perform its periodic actions. The Bloodrose
     * damages any actors present in adjacent squares by {@value #DAMAGE_TO_SURROUNDING}. If an
     * adjacent actor becomes unconscious due to this damage, their unconscious state is handled.
     *
     * @param location The current location of this Bloodrose on the map.
     */
    @Override
    public void tick(Location location) {
        // Iterate through adjacent locations
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            // Check if an actor is present at the adjacent location
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                // Damage the adjacent actor
                target.hurt(DAMAGE_TO_SURROUNDING);
                // Check if the target became unconscious and handle it
                if (!target.isConscious()) {
                    target.unconscious(location.map());
                }
            }
        }
    }
}