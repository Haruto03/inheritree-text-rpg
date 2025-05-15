package game.weapons;

import game.Purchasable;

public class Broadsword extends WeaponItem implements Purchasable {
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }
}
