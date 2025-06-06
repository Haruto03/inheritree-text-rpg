package game.growingPart;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.boss.parts.BossPart;
import game.actors.creatures.boss.parts.Branch;
import game.actors.creatures.boss.parts.Leaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class GrowPartAction extends Action {
    private final Growable grower;
    private final Random random = new Random();

    public GrowPartAction(Growable grower) {
        this.grower = grower;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        StringJoiner result = new StringJoiner(System.lineSeparator());

        BossPart newDirectPart;
        if (random.nextBoolean()) {
            result.add(actor + " grows a new Branch...");
            newDirectPart = new Branch();
            grower.addDirectPart(newDirectPart);
            String directGrowResult = newDirectPart.grow(grower, map);
            if (directGrowResult != null && !directGrowResult.isEmpty()) {
                result.add(directGrowResult);
            }
        } else {
            result.add(actor + " grows a new Leaf...");
            newDirectPart = new Leaf();
            grower.addDirectPart(newDirectPart);
        }

        List<BossPart> productiveParts = new ArrayList<>();
        for (BossPart part : grower.getDirectParts()) {
            if (part != newDirectPart) {
                grower.collectProductiveParts(part, productiveParts);
            }
        }

        for (BossPart partToGrow : productiveParts) {
            String productiveGrowResult = partToGrow.grow(grower, map);
            if (productiveGrowResult != null && !productiveGrowResult.isEmpty()) {
                result.add(productiveGrowResult);
            }
        }

        int totalHealing = grower.getDirectParts().stream()
                .mapToInt(BossPart::getHealingContribution)
                .sum();
        if (totalHealing > 0) {
            grower.heal(totalHealing);
            result.add(actor + " is healed for " + totalHealing + " points. (" + grower.getAttribute(BaseActorAttributes.HEALTH) + "/" + grower.getAttributeMaximum(BaseActorAttributes.HEALTH) + ")");
        }

        if (result.length() == 0) {
            return menuDescription(actor);
        }

        return result.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...";
    }
}