package game.fishing;

import edu.monash.fit2099.engine.actors.Actor;

public interface Fishable {

    double getCatchChance();

    void catchBy(Actor actor);
}
