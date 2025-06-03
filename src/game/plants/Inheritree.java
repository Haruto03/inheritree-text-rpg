package game.plants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;
import game.fishing.DigAction;
import game.grounds.GroundCapability;
import game.grounds.Soil;

/**
 * Represents an Inheritree plant, a type of beneficial {@link Plant} grown from a {@link Seed}.
 * Inheritrees have positive effects, including purifying adjacent cursed ground upon planting and
 * providing periodic healing and stamina restoration to nearby actors. Represented by 't' on the
 * map.
 */
public class Inheritree extends Plant {

    /**
     * The amount of health points restored to adjacent actors each turn.
     */
    private static final int HEAL_AMOUNT = 5;
    /**
     * The amount of stamina points restored to adjacent actors (if they have stamina) each turn.
     */
    private static final int STAMINA_RESTORE_AMOUNT = 5;

    /**
     * Constructor for the Inheritree. Initializes the plant with display character 't' and name
     * "Inherit tree".
     */
    public Inheritree() {
        super('t', "Inherit tree");
        // Inside Inheritree constructor
        this.addCapability(GeneralCapability.BLESSED);
    }


    /**
     * Executes the instant effects that occur when the Inheritree is planted. It checks all
     * adjacent ground tiles. If any adjacent ground has the {@link GroundCapability#CURSED}
     * capability, it is replaced with {@link Soil}.
     *
     * @param planter  The actor who planted the Inheritree (not directly used in this effect).
     * @param location The location where the Inheritree was planted.
     * @return A string describing the purification effect if any cursed ground was converted,
     * otherwise an empty string.
     */
    @Override
    public String executeInstantEffects(Actor planter, Location location) {

        int curedCount = 0; // Counter for purified tiles

        // Check adjacent locations
        for (Exit exit : location.getExits()) {
            Location adjacentLocation = exit.getDestination();
            Ground adjacentGround = adjacentLocation.getGround();
            // If the adjacent ground is cursed...
            if (adjacentGround.hasCapability(GroundCapability.CURSED)) {
                adjacentLocation.setGround(new Soil()); // ...replace it with Soil
                curedCount++; // Increment the counter
            }
        }

        // Return a message only if purification occurred
        if (curedCount > 0) {
            return " The Inherit tree purifies the surrounding cursed ground.";
        }
        return ""; // Return empty string if no purification happened
    }

    /**
     * Called once per turn, allowing the Inheritree to perform its periodic beneficial actions. The
     * Inheritree heals adjacent actors by {@value #HEAL_AMOUNT} health points and restores their
     * stamina by {@value #STAMINA_RESTORE_AMOUNT} points (if the actor has stamina).
     *
     * @param location The current location of this Inheritree on the map.
     */
    @Override
    public void tick(Location location) {
        // Iterate through adjacent locations
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            // Check if an actor is present at the adjacent location
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                // Heal the actor
                target.heal(HEAL_AMOUNT);
                // Restore stamina if the actor has the stamina attribute
                if (target.hasAttribute(BaseActorAttributes.STAMINA)) {
                    target.modifyAttribute(BaseActorAttributes.STAMINA,
                            ActorAttributeOperations.INCREASE, STAMINA_RESTORE_AMOUNT); //
                }
            }
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Item item : actor.getItemInventory()) {
            if (item.hasCapability(GeneralCapability.CAN_DIG)) {
                actions.add(new DigAction(item,location, new Soil()));
            }
        }
        return actions;
    }
}