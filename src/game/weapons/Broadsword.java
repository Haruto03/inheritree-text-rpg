package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class Broadsword extends WeaponItem{
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }
    @Override
    public String applyBasePurchaseEffects(Actor buyer, GameMap map) {
        buyer.heal(10);
        return "Farmer is healed by 10 HP upon purchasing Broadsword.";
    }
}
