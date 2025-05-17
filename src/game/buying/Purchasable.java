package game.buying;

import edu.monash.fit2099.engine.actors.Actor;
import game.effects.Effect;
import java.util.ArrayList;

public interface Purchasable {

    ArrayList<Effect> getBasePurchaseEffects();

    void sellTo(Actor actor);

}
