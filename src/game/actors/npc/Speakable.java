package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;

public interface Speakable {

    ArrayList<Monologue> getMonologues(Actor listener, GameMap map);
}
