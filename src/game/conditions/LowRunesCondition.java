package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;

public class LowRunesCondition implements Condition {
    int LOW_RUNES = 500;
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        return actor.getBalance() < LOW_RUNES;
    }
}
