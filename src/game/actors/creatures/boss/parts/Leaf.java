package game.actors.creatures.boss.parts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.growingPart.Growable;

public class Leaf implements BossPart {

    @Override
    public int getDamageContribution() {
        return 1;
    }

    @Override
    public int getHealingContribution() {
        return 5;
    }

    @Override
    public boolean isProductive() {
        return false;
    }

    @Override
    public void grow(Growable boss, GameMap map) {
    }
}