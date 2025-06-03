package game.grounds;

/**
 * An enumeration representing capabilities specific to ground types
 * ({@link edu.monash.fit2099.engine.positions.Ground}). These capabilities define specific
 * properties or interactions related to the terrain itself, such as whether seeds can be planted on
 * it or if it has a detrimental effect.
 */
public enum GroundCapability {
    /**
     * Indicates that an actor can perform a planting action (e.g., using a
     * {@link game.plants.Seed}) on this ground. Typically found on {@link Soil}.
     */
    CAN_PLANT_SEED,
    /**
     * Indicates that the ground is cursed or blighted. Entities with this capability might have
     * negative effects or be targets for purification actions. Found on {@link Blight}.
     */
    CURSED,
    CanBurned
}