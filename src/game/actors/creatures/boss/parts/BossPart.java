package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.List;

public interface BossPart {

    int getDamageContribution();

    String grow(Actor actor, List<BossPart> directParts);

}