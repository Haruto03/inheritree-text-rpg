package game.spells;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;


public class CastSpellAction extends Action {

    private final SpellBook spellbook;
    private final Actor target;


    /**
     * Constructor for CastSpellAction.
     *
     * @param spellbook The spellbook item being cast.
     * @param target    The actor performing the cast. .
     */
    public CastSpellAction(SpellBook spellbook, Actor target) {
        this.spellbook = spellbook;
        this.target = target;

    }

    @Override
    public String execute(Actor caster, GameMap map) {
        int manaCost = spellbook.getManaCost();

        if (!caster.hasAttribute(BaseActorAttributes.MANA)
                || caster.getAttribute(BaseActorAttributes.MANA) < manaCost) {
            return caster + " does not have enough mana to cast " + spellbook
                    + ". (Required: " + manaCost + ")";
        }

        // Deduct mana
        caster.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE,
                manaCost);

        // Activate the spell by calling the spellbook's activate method
        String result = spellbook.activate(caster, map, target);

        return result;
    }

    @Override
    public String menuDescription(Actor actor) { // 'actor' is the one performing the action
        String description = actor + " casts " + spellbook.toString();

        description += " (Cost: " + spellbook.getManaCost() + " Mana)";
        return description;
    }
}