package game.hatching;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.EatAction;
import game.Eatable;
import game.conditions.Condition;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;


public abstract class Egg extends Item implements Eatable {

    private final ArrayList<HatchingRules> hatchingRules = new ArrayList<>();
    private static final char DISPLAY_CHAR = '0';
    private static final boolean IS_PORTABLE = true;

    public Egg(String name) {
        super(name, Egg.DISPLAY_CHAR, Egg.IS_PORTABLE);
        initHatchingRules();
    }

    public abstract void initHatchingRules();

    public void addHatchingRules(Condition condition, Supplier<Actor> creatureSupplier) {
        this.hatchingRules.add(new HatchingRules(condition, creatureSupplier));
    }

    public Actor tryHatch() {
        for (HatchingRules hatchingRules : this.hatchingRules) {
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

        int randomIndex = random.nextInt(locations.size());
        return locations.get(randomIndex);
    }

    @Override
    public void tick(Location currentLocation) {
        Actor tryHatchlingActor = tryHatch();
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
        actions.add(new EatAction(this));
        return actions;
    }

    @Override
    public abstract String eatenBy(Actor eater, GameMap map);

    @Override
    public abstract String getEatMenuDescription(Actor actor);


}