package game.NPC;

import game.conditions.*;

public class NPCKale extends NPC {

    /**
     * Display character representing the Kale on the game map.
     */
    private final static char DISPLAY_CHAR = 'k';
    /**
     * Initial hit points (health) of the Kale.
     */
    private final static int HIT_POINTS = 200;
    /**
     * The name.
     */
    private final static String NAME = "Sellen";
    /**
     * The constructor of the Actor class.
     */
    public NPCKale() {
        super(NPCKale.NAME, NPCKale.DISPLAY_CHAR, NPCKale.HIT_POINTS );
        Condition defaultCondition = new DefaultCondition();
        Condition lowRunesCondition = new LowRunesCondition();
        Condition emptyInventoryCondition = new EmptyInventoryCondition();
        Condition cursedSurroundCondition = new CursedSurroundCondition();
        addMonologue(new Monologue(lowRunesCondition, "Ah, hard times, I see. Keep your head low and your blade sharp."));
        addMonologue(new Monologue(emptyInventoryCondition, "Not a scrap to your name? Even a farmer should carry a trinket or two."));
        addMonologue(new Monologue(cursedSurroundCondition, "Rest by the flame when you can, friend. These lands will wear you thin."));
        addMonologue(new Monologue(defaultCondition, "A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen."));

    }
}
