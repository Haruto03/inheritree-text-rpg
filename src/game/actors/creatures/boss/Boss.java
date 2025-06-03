package game.actors.creatures.boss;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.creatures.Creature;

public abstract class Boss extends Creature {
    public Boss(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
}}
