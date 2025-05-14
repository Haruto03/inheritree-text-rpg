package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class DragonslayerGreatsword extends WeaponItem {
    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
    }
    @Override
    public String applyBasePurchaseEffects(Actor buyer, GameMap map) {
        buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);
        return "Farmer's max health increased by 15 upon purchasing Dragonslayer Greatsword.";
    }
}