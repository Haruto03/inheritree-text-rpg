package game.NPC;

import game.conditions.Condition;

/**
 * Represents a single line of dialogue (monologue) that an NPC can say
 * when a certain {@link Condition} is met.
 *
 * <p>Each monologue consists of a {@code Condition} that determines whether it
 * should be spoken, and a corresponding {@code message} string.</p>
 */
public class Monologue {

    /** The condition under which this monologue is available. */
    private final Condition condition;

    /** The message to be displayed when the condition is satisfied. */
    private final String message;

    /**
     * Constructs a {@code Monologue} with the given condition and message.
     *
     * @param condition the {@link Condition} that determines if this monologue can be triggered
     * @param message the monologue message to display
     */
    public Monologue(Condition condition, String message) {
        this.condition = condition;
        this.message = message;
    }

    public boolean availability(){
        return condition.check();
    }

    public String getMessage() {
        return message;
    }
}
