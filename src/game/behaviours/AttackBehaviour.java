package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.actions.AttackAction;

public class AttackBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                if (target != actor && target.hasAttribute(BaseActorAttributes.HEALTH)) {
                    int targetHp = target.getAttribute(BaseActorAttributes.HEALTH);
                    if (targetHp > 50) {
                        return new AttackAction(target, exit.getName());
                    }
                }
            }
        }

        return null;
    }
}

