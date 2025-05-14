
package game.conditions;

import edu.monash.fit2099.engine.positions.Location;
import game.EnvironmentScanner;
import game.conditions.provider.LocationProvider;


public class NearbyCapabilityHatchCondition implements Condition {

    private final LocationProvider locationProvider;
    private final Enum<?> capability;

    public NearbyCapabilityHatchCondition(LocationProvider locationProvider, Enum<?> capability) {
        this.locationProvider = locationProvider;
        this.capability = capability;
    }

    @Override
    public boolean check() {
        Location currentlocation = locationProvider.getLocation();
        return EnvironmentScanner.isEntityWithCapabilityNearby(currentlocation, capability);
    }

}
