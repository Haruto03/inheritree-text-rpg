package game.spells;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.ContinuousDamageEffect;


public class PoisonSpell extends SpellBook {

    private static final int MANA_COST = 40;
    private static final String NAME = "Poison Spell";
    private static final char DISPLAY_CHAR = 'p';
    private static final String DESCRIPTION = "Cast a poisonspell on nearby target, poisoning them for 3 turns (10 damage/turn).";
    private static final int POISON_DURATION = 3;
    private static final int POISON_DAMAGE_PER_TURN = 25;

    public PoisonSpell() {
        super(NAME, DISPLAY_CHAR, MANA_COST, DESCRIPTION);
    }

    /**
     * Activates the PoisonSpell on a target Actor. Applies a PoisonStatusEffect to the target.
     *
     * @param caster The actor casting the spell.
     * @param map    The map the actors are on.
     * @param target The actor being targeted by the spell.
     * @return A string describing that the target has been poisoned.
     */
    @Override
    public String activate(Actor caster, GameMap map, Actor target) {

        ContinuousDamageEffect poisonEffect = new ContinuousDamageEffect("Poisoned by " + NAME, POISON_DURATION,
                POISON_DAMAGE_PER_TURN);
        target.addStatusEffect(poisonEffect);
        return target + " is engulfed in a " + NAME + " and becomes poisoned!";
    }

    /**
     * Returns a list of allowable actions that the owner of this spell (the caster) can perform ON
     * the 'otherActor' at the given 'location' using this spell. This method is called by the game
     * engine when iterating through the caster's inventory for each adjacent actor.
     *
     * @param otherActor The potential target actor.
     * @param location   The location of the otherActor.
     * @return An ActionList containing CastSpellAction if the target is valid. The CastSpellAction
     * itself will handle mana and hostility checks.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor,
                location); // Gets base actions from Item if any
        if (otherActor != null && otherActor.isConscious()) {
            actions.add(new CastSpellAction(this, otherActor));
        }
        return actions;
    }

}