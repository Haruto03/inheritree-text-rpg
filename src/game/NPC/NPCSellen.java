package game.NPC;

import game.conditions.Condition;
import game.conditions.DefaultCondition;


public class NPCSellen extends NPC {

    /**
     * Display character representing the Sellen on the game map.
     */
    private final static char DISPLAY_CHAR = 's';
    /**
     * Initial hit points (health) of the Sellen.
     */
    private final static int HIT_POINTS = 150;
    /**
     * The name.
     */
    private final static String NAME = "Sellen";


    /**
     * The constructor of the Actor class.
     *
     */
    public NPCSellen() {
        super(NPCSellen.NAME, NPCSellen.DISPLAY_CHAR, NPCSellen.HIT_POINTS );
        Condition defaultCondition = new DefaultCondition();
        addMonologue(new Monologue(defaultCondition, "The academy casts out those it fears. Yet knowledge, " +
                "like the stars, cannot be bound forever."));
        addMonologue(new Monologue(defaultCondition, "You sense it too, don’t you? The Glintstone hums, even now."));
    }
}
