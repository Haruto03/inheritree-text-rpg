package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

public interface Condition {
    boolean check(NPC target, Actor actor, GameMap map);
}
