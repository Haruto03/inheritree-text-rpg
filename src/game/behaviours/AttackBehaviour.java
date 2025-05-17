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
 * A behaviour that allows an {@link Actor} to attack another {@link Actor} if certain conditions
 * are met.
 * <p>
 * This behaviour checks for target actors in adjacent locations (exits from the current actor's
 * location). If a potential target is found and that target has a health attribute
 * ({@link BaseActorAttributes#HEALTH}) greater than 50, this behaviour will return an
 * {@link AttackAction} targeting that actor. The {@link AttackAction} will use the attacking
 * actor's intrinsic weapon.
 * </p>
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Determines and returns an {@link AttackAction} if a suitable target is found.
     * <p>
     * The method iterates through all exits from the actor's current location. For each exit, it
     * checks if there is another actor at the destination. If an actor is present, is not the
     * attacker itself, and possesses a health attribute with a value greater than 50, an
     * {@link AttackAction} is generated against this target. The direction of the attack is based
     * on the exit's name. If multiple targets meet the criteria, the first one encountered during
     * the iteration is chosen.
     * </p>
     *
     * @param actor the {@link Actor} performing the behaviour (the attacker).
     * @param map   the {@link GameMap} where the actor is located.
     * @return an {@link AttackAction} directed at a valid target if one is found; otherwise,
     * {@code null}.
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
