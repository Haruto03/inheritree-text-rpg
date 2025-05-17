package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.buying.Purchasable;
import game.effects.Effect;
import game.effects.IncreaseMaxHealthEffect;
import java.util.ArrayList;

public class DragonslayerGreatsword extends WeaponItem implements Purchasable {

    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
    }

    @Override
    public ArrayList<Effect> getBasePurchaseEffects() {
        ArrayList<Effect> baseEffects = new ArrayList<>();
        baseEffects.add(new IncreaseMaxHealthEffect(15));
        return baseEffects;
    }

    @Override
    public void sellTo(Actor actor) {
        actor.addItemToInventory(this);
    }
}