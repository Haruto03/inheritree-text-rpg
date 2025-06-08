package game.actors.creatures.boss;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.Creature;
import game.actors.creatures.boss.parts.BossPart;
import game.actors.creatures.boss.parts.Branch;
import game.actors.creatures.boss.parts.Leaf;
import game.behaviours.AttackConditionEvaluator;
import game.behaviours.AttackBehaviour;
import game.capabilities.GeneralCapability;
import game.growingparts.GrowPartBehaviour;
import game.growingparts.Growable;
import game.weapons.BedOfChaosClaw;
import game.weapons.actions.AttackAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BedOfChaos extends Creature implements AttackConditionEvaluator, Growable {

    private static final int BASE_DAMAGE = 25;
    private static final int HIT_RATE = 75;
    private static final int PRIORITY_ATTACK = 1;
    private static final int PRIORITY_GROW = 5;
    private final Random random = new Random();
    private final List<BossPart> directParts = new ArrayList<>();
    private final BedOfChaosClaw bossWeapon = new BedOfChaosClaw(BASE_DAMAGE, "strikes", HIT_RATE);

    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        this.setIntrinsicWeapon(bossWeapon);

    }

    @Override
    protected void initializeBehaviours() {
        this.addBehaviour(PRIORITY_ATTACK, new AttackBehaviour(this));
        this.addBehaviour(PRIORITY_GROW, new GrowPartBehaviour(this));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean evaluate(Actor attacker, Actor potentialTarget, GameMap map) {
        int totalAdditionalDamage = this.getDamageContribution();
        bossWeapon.setDamage(BASE_DAMAGE + totalAdditionalDamage);
        return true;
    }

    @Override
    public String attemptGrow() {
        StringBuilder growMessage = new StringBuilder();
        if (random.nextBoolean()) {
            directParts.add(new Branch());
            growMessage.append("It grows a Branch...\n");
        } else {
            directParts.add(new Leaf());
            growMessage.append("It grows a Leaf...\n");
        }
        int index = 0;
        while (index < directParts.size()) {
            BossPart bossPart = directParts.get(index);
            growMessage.append(bossPart.grow(this, directParts));
            index++;
        }
        return growMessage.toString();
    }

    public int getDamageContribution() {
        int totalAdditionalDamage = 0;
        for (BossPart bossPart : directParts) {
            totalAdditionalDamage += bossPart.getDamageContribution();
        }
        return totalAdditionalDamage;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}