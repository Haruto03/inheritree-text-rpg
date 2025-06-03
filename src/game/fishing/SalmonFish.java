package game.fishing;
import edu.monash.fit2099.engine.items.Item;

public class SalmonFish extends Item implements Fishable{

    private static final double catchChance = 0.6;

    public SalmonFish() {
        super("Salmon Fish", 'S', true);
    }

    @Override
    public double getCatchChance() {
        return catchChance;
    }
}
