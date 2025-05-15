package game.NPC;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.*;

/**
 * An {@link Action} that allows an {@link Actor} to listen to an {@link NPC}
 * and possibly hear a monologue if any condition is satisfied.
 *
 * <p>The {@code ListenAction} checks the list of {@link Monologue}s associated
 * with the target NPC and selects one randomly from those whose {@link game.conditions.Condition}
 * is satisfied given the current {@code actor} and {@code map} context.</p>
 */
public class ListenAction extends Action {

    /** The NPC this action is listening to. */
    private final NPC target;

    /**
     * Constructs a new {@code ListenAction} targeting the given NPC.
     *
     * @param target the NPC to listen to
     */
    public ListenAction(NPC target) {
        this.target = target;
    }

    /**
     * Executes the listen action. If any of the NPC's monologues are available
     * (i.e., their condition is satisfied), one is chosen at random and returned.
     * Otherwise, a default message is returned.
     *
     * @param actor the actor performing the action
     * @param map the game map the actor is on
     * @return the monologue message if any are available, otherwise a fallback message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Monologue> monologues = target.getMonologues();
        List<Monologue> availableMonologues = new ArrayList<>();
        for (Monologue monologue : monologues) {
            if (monologue.availability()) {
                availableMonologues.add(monologue);
            }
        }
        if (!availableMonologues.isEmpty()) {
            int randomIndex = new Random().nextInt(availableMonologues.size());
            return availableMonologues.get(randomIndex).getMessage();
        }
        return "It says nothing...";
    }

    /**
     * Returns a string description of this action, suitable for display in a menu.
     *
     * @param actor the actor performing the action
     * @return a string describing the listen action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + target;
    }
}
