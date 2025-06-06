package game.weapons.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Random;

public class BossAttackAction extends Action {
    private final Actor target;
    private final int damage;
    private final int hitRate;
    private final Random rand = new Random();

    public BossAttackAction(Actor target, int damage, int hitRate) {
        this.target = target;
        this.damage = damage;
        this.hitRate = hitRate;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) < hitRate)) {
            return actor + " misses " + target + ".";
        }

        target.hurt(damage);
        String result = actor + " attacks " + target + " for " + damage + " damage.";
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }
}