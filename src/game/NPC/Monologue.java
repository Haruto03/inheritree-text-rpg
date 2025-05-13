package game.NPC;

import game.conditions.Condition;

public class Monologue {
    Condition condition;
    String message;
    public Monologue(Condition condition, String message) {
        this.condition = condition;
        this.message = message;
    }

}
