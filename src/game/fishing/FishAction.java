package game.fishing;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;
import java.util.Random;

public class FishAction extends Action {

    private final Pond pond;
    private final Item fishingItem;
    private final Location pondLocation;
    private final Random random = new Random();

    public FishAction(Item fishingRod, Pond pond, Location pondLocation) {
        this.fishingItem = fishingRod;
        this.pond = pond;
        this.pondLocation = pondLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Fishable> fishableItems = pond.getFishableItems();
        ArrayList<Fishable> potentialCatches = new ArrayList<>();
        // Roll for each fishable item independently
        for (Fishable fishableItem : fishableItems) {
            if (random.nextDouble() < fishableItem.getCatchChance()) {
                potentialCatches.add(fishableItem);
            }
        }

        if (potentialCatches.isEmpty()) {
            return "You are unlucky! Nothing was caught...";
        } else {
            // If there are potential catches, pick one at random from the list.
            Fishable caughtItem = potentialCatches.get(random.nextInt(potentialCatches.size()));

            // Add the single, randomly selected caught item to the actor's inventory.
            caughtItem.catchBy(actor);

            // Return a success message for the caught item.
            return "You caught " + caughtItem + " from the pond with " + fishingItem + "!";
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fishes at the Pond at (" + pondLocation.x() + ", " + pondLocation.y() + ") with " + fishingItem + ".";
    }
}
