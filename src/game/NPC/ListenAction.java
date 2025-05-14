package game.NPC;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.*;

public class ListenAction extends Action {
    private final NPC target;

    public ListenAction(NPC target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<Monologue> monologues = target.getMonologues();
        List<Monologue> availableMonologues = new ArrayList<>();
        for (Monologue monologue : monologues) {
            if (monologue.condition.check(target, actor, map)) {
                availableMonologues.add(monologue);
            }
        }
        if (!availableMonologues.isEmpty()) {
            int randomIndex = new Random().nextInt(availableMonologues.size());
            return availableMonologues.get(randomIndex).message;
        }
        return "It says nothing...";
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " listen from " + target;
    }
}
