package game.spells;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import game.teleport.TeleportAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeleportSpell extends SpellBook {
    private static final int MANA_COST = 20;
    private static final String DESCRIPTION = "Teleports the caster to a random valid location on the current map.";
    private static final Random random = new Random();

    public TeleportSpell() {
        super("Teleport", 't', MANA_COST, DESCRIPTION);
    }


    /**
     * Activates the TeleportSpell.
     * This method finds a random valid location on the current map and teleports the caster there
     * using the game.teleport.TeleportAction.
     * The 'target' parameter in this context will be the caster themselves.
     *
     * @param caster The actor casting the spell.
     * @param map    The map the caster is on.
     * @param target The target of the spell (which is the caster for this spell).
     * @return A string describing the outcome of the spell's activation.
     */
    @Override
    public String activate(Actor caster, GameMap map, Actor target) {
        // Ensure the spell is being cast by/on the caster, as expected for a SELF target type.

        Location currentLocation = map.locationOf(target);
        if (currentLocation == null) {
            return target + " is not on any map to teleport from.";
        }
        // The 'map' parameter is the map the actor is currently on.
        GameMap currentMap = currentLocation.map();

        List<Location> validTeleportLocations = new ArrayList<>();
        for (int y : currentMap.getYRange()) { // Iterate through all Y coordinates
            for (int x : currentMap.getXRange()) { // Iterate through all X coordinates
                Location potentialLocation = currentMap.at(x, y);
                // Check if the location is valid for teleportation
                if (potentialLocation != currentLocation && // Not the target current spot
                        potentialLocation.canActorEnter(target)) { // target can enter (checks for obstacles and other actors)
                    validTeleportLocations.add(potentialLocation); // Add to list of candidates
                }
            }
        }
        if (validTeleportLocations.isEmpty()) {
            return target + " could not find a valid new location to teleport to on " + currentMap + ".";
        }
        // If there are valid locations, pick one at random from the collected list
        Location randomDestinationLocation = validTeleportLocations.get(random.nextInt(validTeleportLocations.size()));

        // Then proceed to teleport to randomDestinationLocation
        TeleportAction internalTeleportAction = new TeleportAction(randomDestinationLocation);
        return internalTeleportAction.execute(target, currentMap);}

    /**
     * Returns the allowable actions for this spell, which includes casting it if the caster has enough mana.
     *
     * @param caster The actor who might cast the spell.
     * @param map    The map the caster is on.
     * @return An ActionList containing CastSpellAction if conditions are met.
     */
    @Override
    public ActionList allowableActions(Actor caster, GameMap map) {
        ActionList actions = super.allowableActions(caster, map); // Gets DropItemAction etc. from Item class


        actions.add(new CastSpellAction(this, caster));
        return actions;
    }
}
