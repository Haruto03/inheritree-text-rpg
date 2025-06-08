package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.List;
import java.util.Random;

public class Branch implements BossPart {

    private final Random random = new Random();
    private boolean isProductive = true;

    @Override
    public int getDamageContribution() {
        return 3;
    }

    @Override
    public String grow(Actor actor, List<BossPart> directParts) {
        if (isProductive) {
            StringBuilder returnMessage = new StringBuilder();
            returnMessage.append("Branch is growing...\n");

            if (random.nextBoolean()) {
                directParts.add(new Branch());
                returnMessage.append("It grows a Branch...\n");
            } else {
                directParts.add(new Leaf());
                this.isProductive = false;
                returnMessage.append("It grows a Leaf...\n");
            }

            return returnMessage.toString();
        }
        return "";
    }
}