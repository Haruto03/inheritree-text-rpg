package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;
import game.conditions.DefaultCondition;
import java.util.ArrayList;

/**
 * A representation of the "Sellen" NPC in the game. Sellen is a character associated with the
 * academy and glintstone magic.
 * <p>This NPC shares philosophical thoughts and insight about the academy's ways and magic.</p>
 */
public class NpcSellen extends Npc {

    /**
     * Display character representing Sellen on the game map.
     */
    private final static char DISPLAY_CHAR = 's';

    /**
     * Initial hit points (health) of Sellen.
     */
    private final static int HIT_POINTS = 150;

    /**
     * The name of this NPC.
     */
    private final static String NAME = "Sellen";

    /**
     * Constructor for the NPCSellen class.
     *
     * <p>This constructor sets up the Sellen NPC with its name, display character, and initial
     * health. It also defines a set of philosophical monologues that Sellen will share with the
     * player.</p>
     */
    public NpcSellen() {
        super(NpcSellen.NAME, NpcSellen.DISPLAY_CHAR, NpcSellen.HIT_POINTS);
    }

    @Override
    public ArrayList<Monologue> getMonologues(Actor listener, GameMap map) {
        // Define conditions for triggering specific monologues
        Condition defaultCondition = new DefaultCondition();

        ArrayList<Monologue> monologues = new ArrayList<>();
        monologues.add(new Monologue(defaultCondition,
                "The academy casts out those it fears. Yet knowledge, " +
                        "like the stars, cannot be bound forever."));
        monologues.add(new Monologue(defaultCondition,
                "You sense it too, don’t you? The Glintstone hums, even now."));

        return monologues;
    }
}
