package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class EnvironmentScanner {

    public static boolean isEntityWithCapabilityNearby(Location center, Enum<?> capability, GameMap map) {
        for (Exit exit : center.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(capability)) return true;
            if (destination.containsAnActor() && destination.getActor().hasCapability(capability)) return true;
            for (Item item : destination.getItems()) {
                if (item.hasCapability(capability)) return true;
            }
        }
        if (center.getGround().hasCapability(capability)) return true;
        if (center.containsAnActor() && center.getActor().hasCapability(capability)) return true;
        for (Item item : center.getItems()) {
            if (item.hasCapability(capability)) return true;
        }
        return false;
    }
}