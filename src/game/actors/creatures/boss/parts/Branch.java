package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.positions.GameMap;
import game.growingPart.Growable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
    public String grow(Growable boss, GameMap map) {
        if (!isProductive) {
            return "";
        }

        StringJoiner growMessage = new StringJoiner(System.lineSeparator());

        if (random.nextBoolean()) {
            Branch newSubBranch = new Branch();
            this.subParts.add(newSubBranch);
            growMessage.add("A new sub-branch grows from the branch.");

            String subBranchMessage = newSubBranch.grow(boss, map);
            if (subBranchMessage != null && !subBranchMessage.isEmpty()) {
                growMessage.add(subBranchMessage);
            }
        } else {
            this.subParts.add(new Leaf());
            this.isProductive = false;
            growMessage.add("A leaf sprouts, and this branch can no longer grow.");
        }

        return growMessage.toString().lines()
                .filter(line -> !line.isBlank())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}