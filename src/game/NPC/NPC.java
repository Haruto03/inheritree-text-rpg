package game.NPC;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;

import java.util.*;

/**
 * An abstract class representing a Non-Player Character (NPC).
 * All NPCs in the game should inherit from this class.
 */
public abstract class NPC extends Actor {

    final List<Monologue> monologuePool = new ArrayList<>();
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    private static final int PRIORITY_WANDER = 10;

    /**
     * Constructor for an NPC.
     *
     * @param name        the name of the NPC
     * @param displayChar the character to represent the NPC on the map
     * @param hitPoints   initial and maximum health of the NPC
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addBehaviour(PRIORITY_WANDER, new WanderBehaviour());
    }
    /**
     * Returns the actions that other actors can do to this NPC.
     * By default, NPCs don't offer any interactions unless overridden.
     *
     * @param otherActor the actor interacting with this NPC
     * @param direction  the direction of the other actor
     * @param map        the current GameMap
     * @return an empty ActionList by default
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map); // Include default allowable actions if any
        actions.add(new ListenAction(this));
        return actions;
    }

    /**
     * Defines the behavior of the NPC on its turn.
     * Subclasses must implement this to define movement, attack, etc.
     *
     * @param actions the list of possible actions
     * @param lastAction the action the actor did last turn
     * @param map the map the actor is on
     * @param display the I/O object to which messages may be written
     * @return the action to perform this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // TreeMap iterates keys in natural order (ascending), so lower numbers (higher priority) come first.
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        // If no behaviour returned an action
        return new DoNothingAction();
    }

    public void addBehaviour(int priority, Behaviour behaviour) {
        if (behaviour != null) {
            behaviours.put(priority, behaviour);
        }
    }

    /**
     * Add a monologue string to the NPC's pool.
     *
     * @param monologue the line to add
     */
    public void addMonologue(Monologue monologue) {
        monologuePool.add(monologue);
    }

    public List<Monologue> getMonologues() {
        return monologuePool;
    }
}
