package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

public class DefaultCondition implements Condition {
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        return true;
    }
}
