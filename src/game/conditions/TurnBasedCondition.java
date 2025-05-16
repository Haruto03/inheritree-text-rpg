package game.conditions;


public class TurnBasedCondition implements Condition {

    private final int currentTurn;
    private final int specificTurn;

    public TurnBasedCondition(int currentTurn, int specificTurn) {
        this.currentTurn = currentTurn;
        this.specificTurn = specificTurn;
    }

    @Override
    public boolean check() {
        return currentTurn > specificTurn;
    }

}
