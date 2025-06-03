package game.fishing;
import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.Random;

public class FishAction extends Action{

    private Pond pond;
    private Item fishingItem; 
    private final Location pondLocation;
    private Random random = new Random();

    public FishAction(Item fishingRod, Pond pond, Location pondLocation) {
        this.fishingItem = fishingRod;
        this.pond = pond;
        this.pondLocation = pondLocation;
    }
    
    @Override 
    public String execute(Actor actor, GameMap map) {
        List<PondItem> fishableItems = pond.getFishableItems();
        // Roll for each fishable item independently
        for (PondItem item : fishableItems) {
            if (random.nextDouble() < item.getCatchChance()) {
                actor.addItemToInventory(item); // Add fish to actor's inventory
                return "You caught " + item + " from the pond with " + fishingItem + "!";
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
