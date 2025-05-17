package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class IncreaseMaxStaminaEffect implements Effect {
    private final int increaseAmount;

    public IncreaseMaxStaminaEffect(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, increaseAmount);
    }
}