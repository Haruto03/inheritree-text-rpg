package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.ActorProducible;
import game.actors.creatures.ProduceAction;

public class ProduceBehaviour implements Behaviour {

    private final ActorProducible producer;
    public ProduceBehaviour(ActorProducible producerActor) {
        this.producer = producerActor;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (producer.canProduceOffspring(actor, map)) {
            return new ProduceAction(producer);
        }
        return null;
    }
}