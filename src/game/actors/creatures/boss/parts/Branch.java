package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Branch implements BossPart {

    private final List<BossPart> subParts = new ArrayList<>();
    private boolean isProductive = true;
    private final Random random = new Random();

    @Override
    public int getDamageContribution() {
        int subPartsDamage = subParts.stream()
                .mapToInt(BossPart::getDamageContribution)
                .sum();
        return 3 + subPartsDamage;
    }

    @Override
    public int getHealingContribution() {
        return subParts.stream()
                .mapToInt(BossPart::getHealingContribution)
                .sum();
    }

    @Override
    public boolean isProductive() {
        return this.isProductive;
    }

    @Override
    public List<BossPart> getSubParts() {
        return subParts;
    }

    @Override
    public void grow(Actor boss, GameMap map) {
        if (!isProductive) {
            return;
        }

        if (random.nextBoolean()) {
            Branch newSubBranch = new Branch();
            this.subParts.add(newSubBranch);
            newSubBranch.grow(boss, map);
        } else {
            this.subParts.add(new Leaf());
            this.isProductive = false;
        }
    }
}