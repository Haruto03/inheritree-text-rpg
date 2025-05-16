package game.conditions;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;


public class NearbyCapabilityCondition implements Condition {

    private final Location centerLocation;
    private final Enum<?> capability;

    public NearbyCapabilityCondition(Location centerLocation, Enum<?> capability) {
        this.centerLocation = centerLocation;
        this.capability = capability;
    }

    @Override
    public boolean check() {

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(centerLocation);
        for (Exit exit : centerLocation.getExits()) {
            Location destination = exit.getDestination();
            locations.add(destination);
        }
        for (Location location : locations) {
            if (location.getGround().hasCapability(capability)) {
                return true;
            }
            if (location.containsAnActor() && location.getActor().hasCapability(capability)) {
                return true;
            }
            for (Item item : location.getItems()) {
                if (item.hasCapability(capability)) {
                    return true;
                }
            }
        }

        return false;
    }

}

