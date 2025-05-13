package game.NPC;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ListenAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listen from NPC: ";
    }
}
