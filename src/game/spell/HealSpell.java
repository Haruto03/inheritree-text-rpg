package game.spell;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.HealEffect;



public class HealSpell extends SpellBook {
    private static final int HEAL_AMOUNT = 25;
    private static final int MANA_COST = 30;
    private static final String DESCRIPTION = "Heals a target actor for " + HEAL_AMOUNT + " HP.";


    public HealSpell() {
        super("Heal", 'h', MANA_COST, DESCRIPTION);
    }


    @Override
    public String activate(Actor caster, GameMap map,Actor targetActor){
        if (caster == targetActor) {
            new HealEffect(HEAL_AMOUNT).applyEffect(targetActor, map); // Using your existing HealEffect
            return caster + " casts " + this.getClass() + " on " + targetActor + ", healing for " + HEAL_AMOUNT + " HP.";
        }
        return caster + " fails to cast " + this.getClass() + ": No valid target specified for activation.";
    }


    public ActionList allowableActions(Actor caster, GameMap map) {
        ActionList actions = super.allowableActions(caster, map);
         actions.add(new CastSpellAction(this, caster));
        return actions;

    }
}



