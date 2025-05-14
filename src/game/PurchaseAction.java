package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.NPC.NPC;

public class PurchaseAction extends Action {
    private final Actor buyer;
    private final NPC merchant;
    private final Item item;

    public PurchaseAction(Actor buyer, NPC merchant, Item item) {
        this.buyer = buyer;
        this.merchant = merchant;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return buyer + " buys " + item + " from " + merchant;
    }

    @Override
    public String menuDescription(Actor actor) {
        return buyer + " buys " + item + " from " + merchant;
    }
}