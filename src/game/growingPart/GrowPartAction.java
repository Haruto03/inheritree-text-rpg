package game.growingPart;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.boss.BedOfChaos;
import game.actors.creatures.boss.parts.BossPart;
import game.actors.creatures.boss.parts.Branch;

import java.util.ArrayList;
import java.util.List;

public class GrowPartAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(actor instanceof BedOfChaos)) {
            return actor + " cannot grow parts.";
        }

        BedOfChaos boss = (BedOfChaos) actor;

        System.out.println("it grows a Branch...");
        Branch newDirectBranch = new Branch();
        boss.addDirectPart(newDirectBranch);
        newDirectBranch.grow(boss, map);

        List<BossPart> productiveParts = new ArrayList<>();
        for (BossPart part : boss.getDirectParts()) {
            boss.collectProductiveParts(part, productiveParts);
        }

        for (BossPart partToGrow : productiveParts) {
            System.out.println("Branch is growing...");
            partToGrow.grow(boss, map);
        }

        int totalHealing = boss.getDirectParts().stream()
                .mapToInt(BossPart::getHealingContribution)
                .sum();
        if (totalHealing > 0) {
            boss.heal(totalHealing);
            System.out.println(boss.name + " (" + boss.getAttribute("HEALTH") + "/" + boss.getAttributeMaximum("HEALTH") + ") is healed");
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...";
    }
}