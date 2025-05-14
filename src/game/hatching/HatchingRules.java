package game.hatching;

import edu.monash.fit2099.engine.actors.Actor;
import game.conditions.Condition;
import java.util.function.Supplier;

public class HatchingRules {

    private final Condition condition;
    private final Supplier<Actor> creatureSupplier;

    public HatchingRules(Condition condition, Supplier<Actor> creatureSupplier) {
        this.condition = condition;
        this.creatureSupplier = creatureSupplier;
    }

    public Actor tryHatch(){
        if (condition.check()) {
            return creatureSupplier.get();
        }
        return null;
    }


}
