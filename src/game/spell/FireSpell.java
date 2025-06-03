package game.spell; // Ensure this matches your package structure

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;
import game.grounds.BurningGround;
import game.grounds.GroundCapability;
import game.grounds.TemporyGround;

public class FireSpell extends SpellBook {
    private static final int MANA_COST = 25;
    private static final String NAME = "Conflagration"; // Yet another name
    private static final char DISPLAY_CHAR = 'f';
    private static final int INSTANT_AREA_DAMAGE =10;
    private static final String DESCRIPTION = "If an enemy is nearby, ignites surrounding tiles. Instantly burns occupants, and tiles remain burning for 3 turns, damaging those on them.";
    private static final int BurnDuration = 3;
    public FireSpell() {
        super(NAME, DISPLAY_CHAR, MANA_COST, DESCRIPTION);
    }


    @Override
    public String activate(Actor caster, GameMap map, Actor target) {

        Location casterLocation = map.locationOf(caster);

        for (Exit effectExit : casterLocation.getExits()) { // Iterate again for actual effect application
            Location tileToBurn = effectExit.getDestination();
            Ground originalGround = tileToBurn.getGround(); //

            // 2a. Apply instant burn damage to actors on this tile (except caster)
            if (tileToBurn.containsAnActor()) {
                Actor victim = tileToBurn.getActor();
                if (victim != caster) { // AoE doesn't harm caster unless specified
                    victim.hurt(INSTANT_AREA_DAMAGE); //
                    if (!victim.isConscious()) {
                        victim.unconscious(map); // Assuming this method handles necessary map updates
                    }
                }
            }
            if (originalGround.hasCapability(GroundCapability.CanBurned)) { // Corrected: removed semicolon here
                tileToBurn.setGround(new TemporyGround(originalGround,new BurningGround(), BurnDuration));
            }
    }
        // Construct the return string with the caster's name
        String casterName = caster.toString(); // Or caster.getName() if you have such a method
        return "The surrounding of " + casterName + " has been burned";
    }

            @Override
    public ActionList allowableActions (Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location); // Gets base actions from Item if any
        if (otherActor != null && otherActor.isConscious() && otherActor.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            actions.add(new CastSpellAction(this, otherActor));
        }
        return actions;
    }
}
