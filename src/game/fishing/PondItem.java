package game.fishing;
import edu.monash.fit2099.engine.items.Item;

public abstract class PondItem extends Item{

    private double catchChance;

    public PondItem(String name, char displayChar, double catchChance) {
        super(name, displayChar, true);
        this.catchChance = catchChance;
    }

    public double getCatchChance() {
        return catchChance;
    }
}
