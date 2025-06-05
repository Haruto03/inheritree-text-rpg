package game.spell;

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

        for (Exit effectExit : casterLocation.getExits()) {
            Location tileToBurn = effectExit.getDestination();
            Ground originalGround = tileToBurn.getGround(); //


            if (tileToBurn.containsAnActor()) {
                Actor victim = tileToBurn.getActor();
                if (victim != caster) {
                    victim.hurt(INSTANT_AREA_DAMAGE); //
                    if (!victim.isConscious()) {
                        victim.unconscious(map);
                    }
                }
            }
            if (originalGround.hasCapability(GroundCapability.CanBurned)) {
                tileToBurn.setGround(new TemporyGround(originalGround,new BurningGround(), BurnDuration));
            }
    }

        String casterName = caster.toString();
        return "The surrounding of " + casterName + " has been burned";
    }

            @Override
    public ActionList allowableActions (Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor != null && otherActor.isConscious() && otherActor.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            actions.add(new CastSpellAction(this, otherActor));
        }
        return actions;
    }
}
