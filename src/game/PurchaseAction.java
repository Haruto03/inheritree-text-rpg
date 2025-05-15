package game;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.effects.Effects;

public class PurchaseAction extends Action {
    private final Item itemToPurchase; 
    private final int runePrice;
    private final Actor merchant;
    private final List<Effects> purchaseEffects; 

    public PurchaseAction(Item itemToPurchase, int runePrice, Actor merchant, List<Effects> purchaseEffects) {
        this.itemToPurchase = itemToPurchase;
        this.runePrice = runePrice;
        this.merchant = merchant;
        this.purchaseEffects = purchaseEffects; 
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Assume actor has getBalance() and removeBalance() methods
        if (actor.getBalance() < runePrice) {
            return actor + " does not have enough runes to buy the " + itemToPurchase.toString() + ".";
        }

        actor.deductBalance(runePrice);

        // Add item to actor's inventory
        actor.addItemToInventory(itemToPurchase);

        // Apply all stored purchase effects
        for (Effects effect : purchaseEffects) {
            effect.applyEffect(actor, map); // Pass actor and map to apply method
        }

        return actor + " bought a " + itemToPurchase.toString() + " from " + merchant.toString() + " for " + runePrice + " runes.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + itemToPurchase.toString() + " (" + runePrice + " runes) from " + merchant.toString();
    }
}