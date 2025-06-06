package game.growingPart;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;

public class GrowPartBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() &&
                    destination.getActor().hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
                return null;
            }
        }
        return new GrowPartAction();
    }
}