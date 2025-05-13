package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ActorProducible;
import game.ProduceAction;

public class ProduceBehaviour implements Behaviour {

    private ActorProducible producer;
    public ProduceBehaviour(ActorProducible producerActor) {
        this.producer = producerActor;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor != this.producer) return null;

        if (producer.canProduceOffspring(actor, map)) {
            return new ProduceAction(producer);
        }
        return null;
    }
}