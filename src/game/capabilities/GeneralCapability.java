package game.capabilities;

/**
 * An enumeration representing general capabilities or states that game entities can possess. These
 * capabilities can be used to determine interactions, resistances, or specific behaviours. Example:
 * Checking if an actor should attack another (HOSTILE_TO_ENEMY) or if an entity can be targeted by
 * a healing/cleansing effect (CAN_BE_CURED).
 *
 * @author Riordan D. Alfredo
 * Modified by: GoeyQiHang
 *
 */
public enum GeneralCapability {
    /**
     * Capability indicating an actor will attack actors designated as enemies.
     */
    HOSTILE_TO_ENEMY,
    /**
     * Capability indicating an entity have cure ability.
     */
    CAN_CURED,

    BLESSED,

    /**
     * Capability indicating an actor can be followed
     */
    FOLLOWABLE,
    /**
     * Capability indicating an actor can be consumed directly from the map
     */
    CONSUMABLE_ON_MAP,
    CURSED_AURA;

    }
    //test commit