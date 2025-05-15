
package game.conditions;

import edu.monash.fit2099.engine.positions.Location;
import game.EnvironmentScanner;
import game.conditions.providers.LocationProvider;


public class NearbyCapabilityCondition implements Condition {

    private final LocationProvider locationProvider;
    private final Enum<?> capability;

    public NearbyCapabilityCondition(LocationProvider locationProvider, Enum<?> capability) {
        this.locationProvider = locationProvider;
        this.capability = capability;
    }

    @Override
    public boolean check() {
        Location currentlocation = locationProvider.getLocation();
        return EnvironmentScanner.isEntityWithCapabilityNearby(currentlocation, capability);
    }

}
