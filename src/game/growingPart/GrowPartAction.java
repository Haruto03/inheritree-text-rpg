package game.growingPart;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.boss.parts.BossPart;
import game.actors.creatures.boss.parts.Branch;


import java.util.ArrayList;
import java.util.List;

public class GrowPartAction extends Action {
    private final Growable grower;

    public GrowPartAction(Growable grower) {
        this.grower = grower;
    }
    @Override
    public String execute(Actor actor, GameMap map) {


        System.out.println(actor + " grows a Branch...");
        Branch newDirectPart = new Branch();
        grower.addDirectPart(newDirectPart);
        newDirectPart.grow(grower, map);

        List<BossPart> productiveParts = new ArrayList<>();
        for (BossPart part : grower.getDirectParts()) {
            grower.collectProductiveParts(part, productiveParts);
        }

        for (BossPart partToGrow : productiveParts) {
            System.out.println("Branch is growing...");
            partToGrow.grow(grower, map);
        }

        int totalHealing = grower.getDirectParts().stream()
                .mapToInt(BossPart::getHealingContribution)
                .sum();
        if (totalHealing > 0) {
            grower.heal(totalHealing);
            System.out.println(actor + " (" + grower.getAttribute(BaseActorAttributes.HEALTH) + "/" + grower.getAttributeMaximum(BaseActorAttributes.HEALTH) + ") is healed");
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...";
    }
}