package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class BedOfChaosClaw extends IntrinsicWeapon {

    public BedOfChaosClaw(int initialDamage, String verb, int hitRate) {
        super(initialDamage, verb, hitRate);
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }
}