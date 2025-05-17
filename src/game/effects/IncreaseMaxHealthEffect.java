package game.effects;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class IncreaseMaxHealthEffect implements Effect{
    private final int increaseAmount;

    public IncreaseMaxHealthEffect(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public void applyEffect(Actor actor, GameMap map) {
        // Assuming Actor has a method to increase max health
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, increaseAmount);
    }
}
