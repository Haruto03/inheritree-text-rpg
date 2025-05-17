package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DamageEffect implements Effect {
    private final int amount;

    public DamageEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        actor.hurt(amount);
    }
}