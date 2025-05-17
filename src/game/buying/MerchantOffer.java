package game.buying;

import game.effects.Effect;
import java.util.ArrayList;


public class MerchantOffer {

    private final Purchasable purchasableItem;
    private final int price;
    private final ArrayList<Effect> additionalEffects;

    public MerchantOffer(Purchasable purchasableItem, int price, ArrayList<Effect> additionalEffects) {
        this.purchasableItem = purchasableItem;
        this.price = price;
        this.additionalEffects = additionalEffects;
    }

    public Purchasable getItem() {
        return purchasableItem;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Effect> getEffects() {
        return additionalEffects;
    }

}