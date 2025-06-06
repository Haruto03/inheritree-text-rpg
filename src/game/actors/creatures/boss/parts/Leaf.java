package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.List;

public class Leaf implements BossPart {

    @Override
    public int getDamageContribution() {
        return 1;
    }

    @Override
    public String grow(Actor actor, List<BossPart> directParts) {
        actor.heal(5);
        return actor + "is healed\n";
    }
}