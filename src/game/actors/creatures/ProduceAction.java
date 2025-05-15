package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action that allows a specific ActorProducible creature to produce offspring.
 */
public class ProduceAction extends Action {

    protected ActorProducible producerActor;
    /**
     * Constructor.
     * @param producer The creature that will produce offspring. It must implement ActorProducible.
     * The Actor performing this action must be this producer.
     */
    public ProduceAction(ActorProducible producer) {
        this.producerActor = producer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor != this.producerActor) {

            return actor + " cannot force another to produce offspring.";
        }

        String result = this.producerActor.produceOffspring(actor, map);

        if (result == null || result.isEmpty()){
            return actor + " attempts to produce offspring."; // Default message
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        // This action is typically not chosen from a menu by the creature itself.
        return actor + " produces offspring";
    }
}