package game.effects; // Or your equivalent package for status effects

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class ContinuousDamage extends StatusEffect {
    private int duration;
    private final int damagePerTurn;

    /**
     * Constructor for PoisonStatusEffect.
     *
     * @param name          The name of the status effect (e.g., "Poisoned").
     * @param duration      How many turns the poison effect lasts.
     * @param damagePerTurn The damage inflicted each turn.
     */
    public ContinuousDamage(String name, int duration, int damagePerTurn) {
        super(name);
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
    }

    /**
     * Called each turn. Applies damage to the actor and decrements the effect's duration.
     * If the duration runs out, the effect is removed.
     * If the actor becomes unconscious from poison damage, their unconscious state is handled.
     *
     * @param location The location of the actor with the status effect.
     * @param actor    The actor holding the status effect.
     */
    @Override
    public void tick(Location location, Actor actor) {
        if (duration > 0) {
            actor.hurt(damagePerTurn);
             System.out.println(actor + " takes " + damagePerTurn + " damage from " + this + ". " + (duration-1) + " turns remaining.");

            if (!actor.isConscious()) {
                // If the actor becomes unconscious, handle it.
                // The map can be retrieved from the actor's location if needed by unconscious()
                GameMap map = location.map();
                actor.unconscious(map);
                actor.removeStatusEffect(this);
                // Once unconscious, the effect might be implicitly removed or should be explicitly removed here.
                // For now, let it try to remove itself when duration hits 0.
            }
            duration--;
        }

        if (duration <= 0) {
            actor.removeStatusEffect(this);

        }
    }
}