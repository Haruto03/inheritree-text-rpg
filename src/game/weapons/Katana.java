package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.buying.Purchasable;
import game.effects.DamageEffect;
import game.effects.Effect;
import java.util.ArrayList;

public class Katana extends WeaponItem implements Purchasable {

    public Katana() {
        super("Katana", 'j', 50, "slashes", 60);
    }

    @Override
    public ArrayList<Effect> getBasePurchaseEffects() {
        ArrayList<Effect> baseEffects = new ArrayList<>();
        baseEffects.add(new DamageEffect(25));
        return baseEffects;
    }

    @Override
    public void sellTo(Actor actor) {
        actor.addItemToInventory(this);
    }
}
