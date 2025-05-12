package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
// DoNothingAction is not explicitly returned by this version of playTurn, but Creature might return it.
// import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
// Replace with the actual path to your engine's FollowBehaviour
// For example, if it's in edu.monash.fit2099.engine.behaviours:
// import edu.monash.fit2099.engine.behaviours.FollowBehaviour;
import edu.monash.fit2099.demo.mars.behaviours.FollowBehaviour; // Using the provided demo path

import game.ActorProducible;
import game.GoldenEgg;
import game.ConsumeBeetleAction;
import game.behaviours.ProduceBehaviour;
import game.behaviours.WanderBehaviour; // Make sure this exists in game.behaviours
import game.capabilities.GeneralCapability;
import game.weapons.actions.AttackAction; // Assuming this is the correct path

public class GoldenBeetle extends Creature implements ActorProducible {

    private static final char DISPLAY_CHAR = 'b';
    private static final int HIT_POINTS = 25;
    private static final String NAME = "Golden Beetle";
    private static final int EGG_PRODUCTION_INTERVAL = 5;

    private int turnsSinceEggProduced = 0;
    private Actor targetToFollow = null;
    private Behaviour activeFollowBehaviour = null; // Stores the current instance of FollowBehaviour

    // Behaviour priorities (lower number = higher priority)
    private static final int PRIORITY_PRODUCE = 0;
    private static final int PRIORITY_FOLLOW = 5;
    private static final int PRIORITY_WANDER = 10;

    public GoldenBeetle() {
        super(NAME, DISPLAY_CHAR, HIT_POINTS);
        this.addCapability(GeneralCapability.CONSUMABLE_ON_MAP);

        // Add fixed behaviours
        this.addBehaviour(PRIORITY_PRODUCE, new ProduceBehaviour(this));
        this.addBehaviour(PRIORITY_WANDER, new WanderBehaviour());
        // FollowBehaviour is added/removed dynamically in playTurn
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.turnsSinceEggProduced++; // Increment counter for egg production

        // Manage FollowBehaviour:
        // If current target is invalid (not on map, unconscious, or no longer followable)
        if (targetToFollow != null &&
                (!map.contains(targetToFollow) ||
                        !targetToFollow.isConscious() ||
                        !targetToFollow.hasCapability(GeneralCapability.FOLLOWABLE))) {
            if (activeFollowBehaviour != null) {
                this.behaviours.remove(PRIORITY_FOLLOW); // Remove by specific key
                activeFollowBehaviour = null;
            }
            targetToFollow = null;
        }

        // If no target, try to find a new one
        if (targetToFollow == null) {
            Location here = map.locationOf(this);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (map.isAnActorAt(destination)) {
                    Actor potentialTarget = map.getActorAt(destination);
                    if (potentialTarget.hasCapability(GeneralCapability.FOLLOWABLE) && potentialTarget.isConscious()) {
                        targetToFollow = potentialTarget;
                        // Use the engine's FollowBehaviour
                        activeFollowBehaviour = new FollowBehaviour(targetToFollow);
                        this.addBehaviour(PRIORITY_FOLLOW, activeFollowBehaviour);
                        break; // Found a target
                    }
                }
            }
        }

        // Let Creature's playTurn handle behaviour execution based on priorities
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canProduceOffspring(Actor producer, GameMap map) {
        return this.turnsSinceEggProduced >= EGG_PRODUCTION_INTERVAL;
    }

    @Override
    public String produceOffspring(Actor producer, GameMap map) {
        Location producerLocation = map.locationOf(producer);
        for (Exit exit : producerLocation.getExits()) {
            Location destination = exit.getDestination();
            if (!map.isAnActorAt(destination) &&
                    destination.getGround().canActorEnter(producer) &&
                    destination.getItems().isEmpty()) {
                destination.addItem(new GoldenEgg());
                this.turnsSinceEggProduced = 0; // Reset counter
                return producer + " lays a Golden Egg at (" + destination.x() + "," + destination.y() + ")!";
            }
        }
        return producer + " couldn't find a suitable spot to lay an egg.";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionsList = super.allowableActions(otherActor, direction, map);
        // Farmer (assumed to have HOSTILE_TO_ENEMY) can attack
        if (otherActor.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            actionsList.add(new AttackAction(this, direction));
            // Farmer can consume if adjacent and Beetle is consumable
            if (this.hasCapability(GeneralCapability.CONSUMABLE_ON_MAP)) {
                actionsList.add(new ConsumeBeetleAction(this));
            }
        }
        return actionsList;
    }
}