package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.actions.AttackAction;

/**
 * A behaviour that allows an actor to attack another actor if certain conditions are met.
 * <p>This behaviour checks if there are actors within the actor's current location's exits and if the actor's
 * target has health points above a certain threshold, allowing the actor to perform an attack action.</p>
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Determines whether the actor should perform an attack action based on the presence of another actor
     * and the health of the target actor.
     * <p>This method checks all exits from the actor's current location. If an actor is found in an adjacent location,
     * and the target has a health attribute above 50, the attacking actor will perform an attack action.</p>
     *
     * @param actor the actor whose behaviour is being determined
     * @param map the current GameMap where the actor is located
     * @return an AttackAction if the actor should attack a target, null if no attack is necessary
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        // Iterate through all exits to check for adjacent actors
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                // Ensure the target is not the same actor and has a health attribute
                if (target != actor && target.hasAttribute(BaseActorAttributes.HEALTH)) {
                    int targetHp = target.getAttribute(BaseActorAttributes.HEALTH);
                    // Only attack if the target's health is greater than 50
                    if (targetHp > 50) {
                        return new AttackAction(target, exit.getName());
                    }
                }
            }
        }

        // No valid attack conditions found, return null
        return null;
    }
}
