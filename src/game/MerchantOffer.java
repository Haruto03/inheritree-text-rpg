package game;

import edu.monash.fit2099.engine.items.Item;
import game.effects.Effect;
import java.util.List;


public class MerchantOffer {
    private final Item item;
    private final int price;
    private final List<Effect> effects;

    public MerchantOffer(Item item, int price, List<Effect> effects) {
        this.item = item;
        this.price = price;
        this.effects = effects;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void addEffects(Effect effect) {
        effects.add(effect);
    }
}