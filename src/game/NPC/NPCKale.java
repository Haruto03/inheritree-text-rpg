package game.NPC;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.MerchantOffer;
import game.capabilities.GeneralCapability;
import game.conditions.*;
import game.conditions.providers.LocationProvider;
import game.effects.Effect;
import game.effects.HealEffect;
import game.effects.IncreaseMaxHealthEffect;
import game.effects.IncreaseMaxStaminaEffect;
import game.effects.RestoreStaminaEffect;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;

/**
 * A representation of the "Kale" character in the game.
 * This NPC is a merchant figure with unique behaviors and monologues,
 * triggered by specific conditions like low runes, empty inventory, and cursed surroundings.
 * <p>The Kale character is a traveler who shares his wisdom in response to certain game conditions.</p>
 */
public class NPCKale extends NPC implements LocationProvider {

    /** Display character representing Kale on the game map. */
    private final static char DISPLAY_CHAR = 'k';

    /** Initial hit points (health) of Kale. */
    private final static int HIT_POINTS = 200;

    /** The name of this NPC. */
    private final static String NAME = "Sellen";

    private GameMap currentMap;

    /**
     * Constructor for the NPCKale class.
     *
     * <p>This constructor sets up the Kale NPC with its name, display character, and initial health.
     * It also defines a set of monologues that are triggered by different conditions in the game world.</p>
     */
    public NPCKale() {
        super(NPCKale.NAME, NPCKale.DISPLAY_CHAR, NPCKale.HIT_POINTS);

        // Define conditions for triggering specific monologues
        Condition defaultCondition = new DefaultCondition();
        Condition lowRunesCondition = new LowRunesCondition(this);
        Condition emptyInventoryCondition = new EmptyInventoryCondition(this);
        Condition nearbyCapabilityCondition = new NearbyCapabilityCondition(this, GeneralCapability.CURSED);

        // Add monologues based on the conditions
        addMonologue(new Monologue(lowRunesCondition, "Ah, hard times, I see. Keep your head low and your blade sharp."));
        addMonologue(new Monologue(emptyInventoryCondition, "Not a scrap to your name? Even a farmer should carry a trinket or two."));
        addMonologue(new Monologue(nearbyCapabilityCondition, "Rest by the flame when you can, friend. These lands will wear you thin."));
        addMonologue(new Monologue(defaultCondition, "A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen."));

        // Define offers
        // Broadsword Offer 1
        List<Effect> broadswordEffects1 = new ArrayList<>();
        broadswordEffects1.add(new HealEffect(10));
        broadswordEffects1.add(new IncreaseMaxStaminaEffect(30));
        offers.add(new MerchantOffer(new Broadsword(), 150, broadswordEffects1));

        // Broadsword Offer 2
        List<Effect> broadswordEffects2 = new ArrayList<>();
        broadswordEffects2.add(new HealEffect(10));
        broadswordEffects2.add(new HealEffect(10));
        offers.add(new MerchantOffer(new Broadsword(), 120, broadswordEffects2));

        // Dragonslayer Greatsword
        List<Effect> dragonslayerEffects = new ArrayList<>();
        dragonslayerEffects.add(new IncreaseMaxHealthEffect(15));
        dragonslayerEffects.add(new RestoreStaminaEffect(20));
        offers.add(new MerchantOffer(new DragonslayerGreatsword(), 1700, dragonslayerEffects));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        currentMap = map;
        return super.playTurn(actions, lastAction, map, display);
    }
    @Override
    public Location getLocation() {
        return currentMap.locationOf(this);
    }
}
