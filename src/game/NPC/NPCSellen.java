package game.NPC;

import game.conditions.Condition;
import game.conditions.DefaultCondition;

/**
 * A representation of the "Sellen" NPC in the game.
 * Sellen is a character associated with the academy and glintstone magic.
 * <p>This NPC shares philosophical thoughts and insight about the academy's ways and magic.</p>
 */
public class NPCSellen extends NPC {

    /** Display character representing Sellen on the game map. */
    private final static char DISPLAY_CHAR = 's';

    /** Initial hit points (health) of Sellen. */
    private final static int HIT_POINTS = 150;

    /** The name of this NPC. */
    private final static String NAME = "Sellen";

    /**
     * Constructor for the NPCSellen class.
     *
     * <p>This constructor sets up the Sellen NPC with its name, display character, and initial health.
     * It also defines a set of philosophical monologues that Sellen will share with the player.</p>
     */
    public NPCSellen() {
        super(NPCSellen.NAME, NPCSellen.DISPLAY_CHAR, NPCSellen.HIT_POINTS);

        // Define the default condition for triggering monologues
        Condition defaultCondition = new DefaultCondition();

        // Add monologues to Sellen's pool based on the default condition
        addMonologue(new Monologue(defaultCondition, "The academy casts out those it fears. Yet knowledge, " +
                "like the stars, cannot be bound forever."));
        addMonologue(new Monologue(defaultCondition, "You sense it too, don’t you? The Glintstone hums, even now."));
    }
}
