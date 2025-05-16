package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.GeneralCapability;
import game.PurchaseAction;
import game.MerchantOffer;
import game.behaviours.WanderBehaviour;

import java.util.*;

/**
 * An abstract class representing a Non-Player Character (NPC).
 * All NPCs in the game should inherit from this class.
 *
 * <p>The {@code NPC} class extends {@link Actor} and provides additional functionality
 * specific to NPC behavior, including handling monologues, defining NPC-specific behaviors,
 * and interacting with other actors through actions.</p>
 */
public abstract class Npc extends Actor {

    /** A map of behaviors for this NPC, keyed by their priority. */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /** The priority value used for wandering behavior. */
    private static final int PRIORITY_WANDER = 999;

    protected final List<MerchantOffer> offers = new ArrayList<>();

    /**
     * Constructor for an NPC.
     *
     * @param name        the name of the NPC
     * @param displayChar the character to represent the NPC on the map
     * @param hitPoints   initial and maximum health of the NPC
     */
    public Npc(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addBehaviour(PRIORITY_WANDER, new WanderBehaviour());
    }

    /**
     * Returns the actions that other actors can do to this NPC.
     * By default, NPCs don't offer any interactions unless overridden.
     *
     * <p>This method adds the {@link ListenAction} to the list of actions available
     * to other actors interacting with the NPC.</p>
     *
     * @param otherActor the actor interacting with this NPC
     * @param direction  the direction of the other actor
     * @param map        the current GameMap
     * @return an {@link ActionList} containing the actions that can be performed on this NPC
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map); // Include default allowable actions if any
        actions.add(new ListenAction(this));

        if (this.hasCapability(GeneralCapability.CAN_SELL)) {
            for (MerchantOffer offer: offers) {
                actions.add(new PurchaseAction(offer.getItem(), offer.getPrice(), this, offer.getEffects()));
            }
        }
        return actions;
    }

    /**
     * Defines the behavior of the NPC on its turn.
     * Subclasses must implement this to define movement, attack, etc.
     *
     * <p>The NPC chooses an action based on its behaviors, prioritizing them
     * based on their assigned priority values. The action returned by the highest priority
     * behavior is executed first. If no action is returned by any behavior, the NPC performs no action.</p>
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

    /**
     * Adds a new behavior to the NPC.
     *
     * <p>Behaviors are added with a priority, where lower numbers indicate higher priority.</p>
     *
     * @param priority the priority of the behavior
     * @param behaviour the behavior to add
     */
    public void addBehaviour(int priority, Behaviour behaviour) {
        if (behaviour != null) {
            behaviours.put(priority, behaviour);
        }
    }

    public abstract ArrayList<Monologue> getMonologues(Actor listener,GameMap map);
}
