package game.actors.creatures.boss;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.Creature;
import game.actors.creatures.boss.parts.BossPart;
import game.actors.npc.AttackConditionEvaluator;
import game.behaviours.AttackBehaviour;
import game.growingPart.GrowPartBehaviour;
import game.capabilities.GeneralCapability;
import game.growingPart.Growable;
import game.weapons.BedOfChaosClaw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BedOfChaos extends Creature implements AttackConditionEvaluator, Growable {

    private final List<BossPart> directParts = new ArrayList<>();
    private static final int BASE_DAMAGE = 25;
    private static final int HIT_RATE = 75;
    private static final int PRIORITY_ATTACK = 1;
    private static final int PRIORITY_GROW = 5;

    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        this.setIntrinsicWeapon(new BedOfChaosClaw(BASE_DAMAGE, "strikes", HIT_RATE));

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
        if (potentialTarget.hasCapability(GeneralCapability.HOSTILE_TO_ENEMY)) {
            int accumulatedDamage = directParts.stream()
                    .mapToInt(BossPart::getDamageContribution)
                    .sum();
            int totalDamage = BASE_DAMAGE + accumulatedDamage;

            BedOfChaosClaw weapon = (BedOfChaosClaw) getIntrinsicWeapon();
            weapon.setDamage(totalDamage);
            return true;
        }
        return false;
    }

    public void addDirectPart(BossPart part) {
        this.directParts.add(part);
    }

    public List<BossPart> getDirectParts() {
        return Collections.unmodifiableList(directParts);
    }

    public void collectProductiveParts(BossPart part, List<BossPart> productiveParts) {
        if (part.isProductive()) {
            productiveParts.add(part);
        }
        for (BossPart subPart : part.getSubParts()) {
            collectProductiveParts(subPart, productiveParts);
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return super.allowableActions(otherActor, direction, map);
    }
}