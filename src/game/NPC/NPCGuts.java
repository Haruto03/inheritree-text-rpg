package game.NPC;

import edu.monash.fit2099.engine.actors.Behaviour;
import game.behaviours.AttackBehaviour;
import game.conditions.Condition;
import game.conditions.DefaultCondition;
import game.conditions.LowHealthCondition;

public class NPCGuts extends NPC {
    /**
     * Display character representing the Guts on the game map.
     */
    private final static char DISPLAY_CHAR = 'g';
    /**
     * Initial hit points (health) of the Guts.
     */
    private final static int HIT_POINTS = 500;
    /**
     * The name.
     */
    private final static String NAME = "Guts";

    private static final int PRIORITY_ATTACK = 5;

    /**
     * The constructor of the Actor class.
     */
    public NPCGuts() {
        super(NPCGuts.NAME, NPCGuts.DISPLAY_CHAR, NPCGuts.HIT_POINTS );
        Behaviour AttackBehaviour = new AttackBehaviour();
        addBehaviour(PRIORITY_ATTACK , AttackBehaviour);
        Condition defaultCondition = new DefaultCondition();
        Condition lowHealthCondition = new LowHealthCondition();
        addMonologue(new Monologue(lowHealthCondition, "WEAK! TOO WEAK TO FIGHT ME!"));
        addMonologue(new Monologue(defaultCondition, "RAAAAGH!"));
        addMonologue(new Monologue(defaultCondition, "I’LL CRUSH YOU ALL!"));
    }
}
