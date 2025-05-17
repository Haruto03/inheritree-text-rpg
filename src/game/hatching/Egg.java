package game.hatching;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.GeneralCapability;
import game.eating.EatAction;
import game.eating.Eatable;
import java.util.ArrayList;
import java.util.Random;


public abstract class Egg extends Item implements Eatable {

    private static final char DISPLAY_CHAR = '0';
    private static final boolean IS_PORTABLE = true;

    public Egg(String name) {
        super(name, Egg.DISPLAY_CHAR, Egg.IS_PORTABLE);
    }

    public abstract ArrayList<HatchingRules> getHatchingRules(Location currentLocation);


    public Actor tryHatch(Location currentLocation) {
        for (HatchingRules hatchingRules : this.getHatchingRules(currentLocation)) {
            Actor hatchlingActor = hatchingRules.tryHatch();
            if (hatchlingActor != null) {
                return hatchlingActor;
            }
        }
        return null;
    }

    public Location tryProduce(Location currentLocation, Actor actor) {

        ArrayList<Location> locations = new ArrayList<>();
        Random random = new Random();

        if (currentLocation.canActorEnter(actor)) {
            locations.add(currentLocation);
        }
        for (Exit exit : currentLocation.getExits()) {
            Location location = exit.getDestination();
            if (location.canActorEnter(actor)) {
                locations.add(location);
            }
        }
        if (!locations.isEmpty()) {
            int randomIndex = random.nextInt(locations.size());
            return locations.get(randomIndex);
        }
        return null;
    }

    @Override
    public void tick(Location currentLocation) {
        Actor tryHatchlingActor = tryHatch(currentLocation);
        if (tryHatchlingActor != null) {
            Location produceLocation = tryProduce(currentLocation, tryHatchlingActor);
            if (produceLocation != null) {
                produceLocation.addActor(tryHatchlingActor);
                currentLocation.removeItem(this);
            }
        }
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        if (owner.hasCapability(GeneralCapability.CONSUMER)) {
            actions.add(new EatAction(this));
        }

        return actions;
    }

    @Override
    public abstract String eatenBy(Actor eater, GameMap map);

    @Override
    public abstract String getEatMenuDescription(Actor actor);


}