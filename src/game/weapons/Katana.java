package game.weapons;

import game.Purchasable;

public class Katana extends WeaponItem implements Purchasable {
    public Katana() {
        super("Katana", 'j', 50, "slashes", 60);
    }
}
