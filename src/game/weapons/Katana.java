package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class Katana extends WeaponItem {
    public Katana() {
        super("Katana", 'j', 50, "slashes", 60);
    }
    
    @Override
    public String applyBasePurchaseEffects(Actor buyer, GameMap map) {
        buyer.hurt(25);
        return "Farmer takes 25 damage upon purchasing Katana.";
    }
}
