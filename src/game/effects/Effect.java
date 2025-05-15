package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Effect{
    void applyEffect(Actor actor, GameMap map);
    
}
