package game.growingPart;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;

public class GrowPartBehaviour implements Behaviour {
    private final Growable grower;
    public GrowPartBehaviour(Growable grower) {
        this.grower = grower;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        return new GrowPartAction(grower);
    }
}
