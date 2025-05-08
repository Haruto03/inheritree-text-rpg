package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing the fertile soil found in the valley, suitable for planting. It is
 * represented by the character '.' and named "Soil". This ground type has the
 * {@link GroundCapability#CAN_PLANT_SEED} capability, allowing actors to plant
 * {@link game.plants.Seed} items on it. It inherits other default behaviors from the {@link Ground}
 * class.
 *
 * @author Adrian Kristanto Modified by: Goey Qi Hang
 */
public class Soil extends Ground {

    /**
     * Constructor for the Soil class. Sets the display character to '.', the name to "Soil", and
     * adds the capability indicating that seeds can be planted here.
     */
    public Soil() {
        super('.', "Soil");
        this.addCapability(GroundCapability.CAN_PLANT_SEED);
    }
}