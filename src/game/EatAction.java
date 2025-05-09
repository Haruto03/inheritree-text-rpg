package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item; // To ensure eatableItem is an Item

/**
 * An Action that allows an Actor to eat an Eatable item from their inventory.
 */
public class EatAction extends Action {

    private Eatable eatableItem; // The Eatable item itself

    /**
     * Constructor.
     *
     * @param item The Eatable item to be eaten. Must also be an instance of Item.
     */
    public EatAction(Eatable item) {
        this.eatableItem = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Ensure the actor still possesses the item (it's an Item to be in inventory)
        Item itemInstance = (Item) this.eatableItem;
        if (actor.getItemInventory().contains(itemInstance)) {
            // Delegate the eating logic to the Eatable item itself
            return this.eatableItem.eatenBy(actor, map);
        }
        return actor.toString() + " no longer has the " + itemInstance + " to eat.";
    }

    @Override
    public String menuDescription(Actor actor) {
        // Get the specific menu description from the Eatable item
        return this.eatableItem.getEatMenuDescription(actor);
    }
}