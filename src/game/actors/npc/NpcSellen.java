package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;
import game.conditions.DefaultCondition;
import game.effects.Effect;
import game.effects.HealEffect;
import game.effects.IncreaseMaxHealthEffect;
import game.effects.IncreaseMaxStaminaEffect;
import game.effects.DamageEffect;
import game.effects.SpawnActorEffect;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;
import game.weapons.Katana;
import game.MerchantOffer;
import game.capabilities.GeneralCapability;
import java.util.ArrayList;
import java.util.List;

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
        this.addCapability(GeneralCapability.CAN_SELL);
        // Define offers
        // Broadsword
        List<Effect> broadswordEffects = new ArrayList<>();
        broadswordEffects.add(new HealEffect(10));
        broadswordEffects.add(new IncreaseMaxHealthEffect(20));
        offers.add(new MerchantOffer(new Broadsword(), 100, broadswordEffects));

        // Dragonslayer Greatsword
        List<Effect> dragonslayerEffects = new ArrayList<>();
        dragonslayerEffects.add(new IncreaseMaxHealthEffect(15));
        dragonslayerEffects.add(new SpawnActorEffect(GoldenBeetle::new));
        offers.add(new MerchantOffer(new DragonslayerGreatsword(), 1500, dragonslayerEffects));

        // Katana
        List<Effect> katanaEffects = new ArrayList<>();
        katanaEffects.add(new DamageEffect(25));
        katanaEffects.add(new HealEffect(10));
        katanaEffects.add(new IncreaseMaxStaminaEffect(20));
        katanaEffects.add(new SpawnActorEffect(OmenSheep::new, this));
        offers.add(new MerchantOffer(new Katana(), 500, katanaEffects));
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
