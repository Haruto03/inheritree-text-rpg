package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.NPC.NPC;
import game.grounds.GroundCapability;

public class CursedSurroundCondition implements Condition {
    @Override
    public boolean check(NPC target, Actor actor, GameMap map) {
        // Check all exits from the current location of target(npc)
        for (Exit exit : map.locationOf(target).getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.getGround().hasCapability(GroundCapability.CURSED)) {
                return true;
            }
        }
        return false;
    }
}