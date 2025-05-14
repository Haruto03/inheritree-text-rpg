package game.NPC;

import edu.monash.fit2099.engine.actors.Behaviour;
import game.behaviours.AttackBehaviour;
import game.conditions.Condition;
import game.conditions.DefaultCondition;
import game.conditions.LowHealthCondition;
import game.weapons.BareFist;

/**
 * A representation of the "Guts" character in the game.
 * This NPC is a powerful fighter with unique behaviors and monologues.
 * <p>The Guts character performs an attack behavior when given the chance and has specific monologues
 * triggered by certain conditions, such as low health.</p>
 */
public class NPCGuts extends NPC {

    /** Display character representing the Guts on the game map. */
    private final static char DISPLAY_CHAR = 'g';

    /** Initial hit points (health) of the Guts. */
    private final static int HIT_POINTS = 500;

    /** The name of this NPC. */
    private final static String NAME = "Guts";

    /** Priority for the attack behavior. */
    private static final int PRIORITY_ATTACK = 5;

    /**
     * Constructor for the NPCGuts class.
     *
     * <p>This constructor sets up the Guts NPC with its name, display character, and initial health.
     * It also adds an attack behavior with a priority and defines a set of monologues triggered by certain conditions.</p>
     */
    public NPCGuts() {
        super(NPCGuts.NAME, NPCGuts.DISPLAY_CHAR, NPCGuts.HIT_POINTS);
        // Set the Intrinsic weapon BareFist for Guts
        this.setIntrinsicWeapon(new BareFist());

        // Define and add the Attack behaviour with high priority
        Behaviour attackBehaviour = new AttackBehaviour();
        addBehaviour(PRIORITY_ATTACK, attackBehaviour);

        // Define conditions for triggering specific monologues
        Condition defaultCondition = new DefaultCondition();
        Condition lowHealthCondition = new LowHealthCondition();

        // Add monologues based on the conditions
        addMonologue(new Monologue(lowHealthCondition, "WEAK! TOO WEAK TO FIGHT ME!"));
        addMonologue(new Monologue(defaultCondition, "RAAAAGH!"));
        addMonologue(new Monologue(defaultCondition, "I’LL CRUSH YOU ALL!"));
    }
}
