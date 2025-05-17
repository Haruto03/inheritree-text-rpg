package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class HealEffect implements Effect {
    private final int healAmount;

    public HealEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        actor.heal(healAmount);
    }
}