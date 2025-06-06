package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Collections;
import java.util.List;

public interface BossPart {

    int getDamageContribution();

    int getHealingContribution();

    boolean isProductive();

    void grow(Actor boss, GameMap map);

    default List<BossPart> getSubParts() {
        return Collections.emptyList();
    }
}