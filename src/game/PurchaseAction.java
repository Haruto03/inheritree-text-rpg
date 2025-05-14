package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPC.NPC;
import game.weapons.WeaponItem;

public class PurchaseAction extends Action {
    private final Actor buyer;
    private final NPC merchant;
    private final WeaponItem weapon;

    public PurchaseAction(Actor buyer, NPC merchant, WeaponItem weapon) {
        this.buyer = buyer;
        this.merchant = merchant;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return buyer + " buys " + weapon + " from " + merchant;
    }

    @Override
    public String menuDescription(Actor actor) {
        return buyer + " buys " + weapon + " from " + merchant;
    }
}