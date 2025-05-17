package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.buying.Purchasable;
import game.effects.Effect;
import game.effects.HealEffect;
import java.util.ArrayList;

public class Broadsword extends WeaponItem implements Purchasable {
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }

    @Override
    public ArrayList<Effect> getBasePurchaseEffects() {
        ArrayList<Effect> baseEffects = new ArrayList<>();
        baseEffects.add(new HealEffect(10));
        return baseEffects;
    }

    @Override
    public void sellTo(Actor actor) {
        actor.addItemToInventory(this);

    }
}
