package game.spells;

import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class SpellBook extends Item {

    private final int manaCost;
    private final String description;

    public SpellBook(String name, char displayChar, int manaCost, String description) {
        super(name, displayChar, true);
        this.manaCost = manaCost;
        this.description = description;
    }

    public int getManaCost() {
        return manaCost; }

    public String getDescription() {
        return description;}



    public abstract String activate(Actor caster, GameMap map, Actor target);}

