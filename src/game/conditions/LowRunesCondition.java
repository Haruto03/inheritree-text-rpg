package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;

public class LowRunesCondition implements Condition {

    int LOW_RUNES = 500;

    private final Actor actor;

    public LowRunesCondition(Actor actor) {
        this.actor = actor;
    }

    @Override
    public boolean check() {
        return actor.getBalance() < LOW_RUNES;
    }
}
