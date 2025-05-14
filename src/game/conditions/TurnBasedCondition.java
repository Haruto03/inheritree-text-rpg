
package game.conditions;


import game.conditions.provider.TurnProvider;

public class TurnBasedCondition implements Condition {

    private final TurnProvider turnProvider;
    private final int specificTurn;

    public TurnBasedCondition(TurnProvider turnProvider,int specificTurn) {
        this.turnProvider = turnProvider;
        this.specificTurn = specificTurn;
    }

    @Override
    public boolean check() {
        return turnProvider.getTurn() > specificTurn;
    }

}
