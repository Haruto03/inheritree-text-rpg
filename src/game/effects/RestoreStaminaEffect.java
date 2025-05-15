package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class RestoreStaminaEffect implements Effect {
    private final int amount;

    public RestoreStaminaEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, amount);
    }
}