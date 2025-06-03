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
        // Roll for each fishable item independently
        for (Fishable fishableItem : fishableItems) {
            if (random.nextDouble() < fishableItem.getCatchChance()) {
                fishableItem.catchBy(actor);// Add fish to actor's inventory
                return "You caught " + fishableItem + " from the pond with " + fishingItem + "!";
            }
        }
        return "You are unlucky! Nothing catched...";
    }

    @Override
    public String menuDescription(Actor actor) {
        // This method returns a description of the action for the menu.
        return actor + " fishes at the Pond " + pondLocation + " with " + fishingItem + ".";
    }
}
