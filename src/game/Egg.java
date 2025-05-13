package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.ActionList;

/**
 * Abstract class representing an egg in the game.
 * Implements Eatable (potentially for default behavior or to enforce the contract)
 * and Hatchable (for hatching logic).
 */
public abstract class Egg extends Item implements Eatable, ItemProducible {

    protected int turnsToHatch;
    protected int turnsOnGroundCounter;

    /**
     * Constructor.
     *
     * @param name           the name of this Item
     * @param displayChar    the character to use to represent this item if it is on the ground
     * @param portable       true if and only if the Item can be picked up
     * @param turnsToHatch   the number of turns required for this egg to hatch when on the ground
     */
    public Egg(String name, char displayChar, boolean portable, int turnsToHatch) {
        super(name, displayChar, portable);
        this.turnsToHatch = turnsToHatch;
        this.turnsOnGroundCounter = 0;
    }

    /**
     * Inform an Egg on the ground of the passage of time.
     * This method is called once per turn if the item rests upon the ground.
     * It increments the on-ground counter and attempts to hatch if conditions are met.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsOnGroundCounter++;
        if (this.canproduce(currentLocation)) {
            GameMap map = currentLocation.map();
            String hatchMessage = this.produce(currentLocation, map);
            if (hatchMessage != null && !hatchMessage.isEmpty()) {
                System.out.println(hatchMessage);
            }
        }
    }

    /**
     * Inform a carried Egg of the passage of time.
     * Resets the on-ground counter as eggs typically don't hatch in inventory per requirement.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        this.turnsOnGroundCounter = 0; // Reset if picked up, won't hatch in inventory
    }

    /**
     * Eggs provide an EatAction if they are eatable.
     * This method needs to be implemented by concrete egg classes if they are eatable.
     * The UML shows EatAction having a dependency on Egg, and Egg implementing Eatable.
     * The EatAction will be for "this" specific egg.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        actions.add(new EatAction(this));
        return actions;
    }

    // Abstract Eatable methods (or provide default if some eggs are not special when eaten)
    @Override
    public abstract String eatenBy(Actor eater, GameMap map);

    @Override
    public abstract String getEatMenuDescription(Actor actor);


    @Override
    public abstract boolean canproduce(Location currentLocation);

    @Override
    public abstract String produce(Location currentLocation, GameMap map);

    public int getTurnsOnGroundCounter() {  return turnsOnGroundCounter;
    }
}