package game.conditions;


/**
 * A default condition that always returns true.
 * <p>This condition is used when no specific condition is required, meaning it will always evaluate
 * to true.</p>
 *
 * <p>It can be used as a fallback or a default condition when no other condition applies.</p>
 */
public class DefaultCondition implements Condition {


    @Override
    public boolean check() {
        return true;
    }
}
