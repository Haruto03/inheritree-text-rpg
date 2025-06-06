package game.fishing;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;
import java.util.ArrayList;

public class Pond extends Ground {

    private final ArrayList<Fishable> fishableItems;

    public Pond() {
        super('~', "Pond");
        this.fishableItems = new ArrayList<>();
        fishableItems.add(new SalmonFish());
        fishableItems.add(new ToxicEel());
        fishableItems.add(new Shovel());
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        for (Item item : actor.getItemInventory()) {
            if (item.hasCapability(GeneralCapability.CAN_FISH)) {
                actions.add(new FishAction(item, this, location));
            }
        }
        return actions;
    }

    public ArrayList<Fishable> getFishableItems() {
        return new ArrayList<>(fishableItems);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
