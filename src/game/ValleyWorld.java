package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.World;
import game.ui.FancyMessage;

/**
 * A custom World class for the Valley game that overrides the end game message.
 */
public class ValleyWorld extends World {

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public ValleyWorld(Display display) {
        super(display);
    }

    /**
     * Override the default end game message to display the "YOU DIED" message.
     *
     * @return The "YOU DIED" message string from FancyMessage.
     */
    @Override
    protected String endGameMessage() {
        return FancyMessage.YOU_DIED;
    }
}