package game.actors.creatures.boss;

import game.actors.creatures.Creature;

public abstract class Boss extends Creature {

    public Boss(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}
