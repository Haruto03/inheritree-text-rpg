package game.growingparts;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class GrowPartBehaviour implements Behaviour {

    private final Growable grower;

    public GrowPartBehaviour(Growable grower) {
        this.grower = grower;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location currentLocation = map.locationOf(actor);

        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                return null;
            }
        }
        // No target found decide to grow
        return new GrowPartAction(grower);
    }

}
