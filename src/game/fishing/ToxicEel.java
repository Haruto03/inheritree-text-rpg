package game.fishing;
import edu.monash.fit2099.engine.items.Item;

public class ToxicEel extends Item implements Fishable{
    
    private static final double catchChance = 0.3;

    public ToxicEel() {
        super("Toxic Eel", 'C', true);
    }

    public double getCatchChance() {
        return catchChance;
    }
}
