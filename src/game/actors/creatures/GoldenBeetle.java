package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.eating.EatAction;
import game.eating.Eatable;
import game.behaviours.FollowBehaviour;
import game.behaviours.ProduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.GeneralCapability;
import game.hatching.GoldenBeetleEgg;
import game.weapons.actions.AttackAction;

public class GoldenBeetle extends Creature implements ActorProducible, Eatable {


    private static final String NAME = "Golden Beetle";
    private static final char DISPLAY_CHAR = 'b';
    private static final int HIT_POINTS = 25;
    private static final int EGG_PRODUCTION_INTERVAL = 5;

    private int turnsSinceEggProduced = 0;


    private static final int PRIORITY_PRODUCE = 0;
    private static final int PRIORITY_FOLLOW = 5;
    private static final int PRIORITY_WANDER = 999;

    public GoldenBeetle() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addCapability(GeneralCapability.CONSUMABLE_ON_MAP);

        this.addBehaviour(PRIORITY_PRODUCE, new ProduceBehaviour(this));
        this.addBehaviour(PRIORITY_FOLLOW, new FollowBehaviour());
        this.addBehaviour(PRIORITY_WANDER, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.turnsSinceEggProduced++;

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canProduceOffspring(Actor producer, GameMap map) {
        return this.turnsSinceEggProduced >= EGG_PRODUCTION_INTERVAL;
    }

    @Override
    public String produceOffspring(Actor producer, GameMap map) {

        Location producerLocation = map.locationOf(producer);
        producerLocation.addItem(new GoldenBeetleEgg());
        this.turnsSinceEggProduced = 0; // Reset counter
        return producer + " lays a GoldenBeetleEgg at (" + producerLocation.x() + ","
                + producerLocation.y() + ")!";


    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionsList = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(GeneralCapability.CONSUMER)) {
            actionsList.add(new EatAction(this));
        }
        
        if (otherActor.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            actionsList.add(new AttackAction(this, direction));
        }

        return actionsList;
    }

    @Override
    public String eatenBy(Actor eater, GameMap map) {

        int healAmount = 15;
        int runesGained = 1000;

        // Eaten effect
        eater.heal(healAmount);
        eater.addBalance(runesGained);
        map.removeActor(this);

        return eater + " eats " + this + ", healing for " + healAmount + " HP and gaining "
                + runesGained + " runes.";
    }

    @Override
    public String getEatMenuDescription(Actor actor) {
        return actor + " eat " + this;
    }
}