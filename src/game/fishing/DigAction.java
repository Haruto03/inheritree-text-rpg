package game.fishing;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Soil;

public class DigAction extends Action {

    private final Location digLocation;
    private final Ground newGround;

    public DigAction(Location digLocation) {
        this.digLocation = digLocation;
        this.newGround = new Soil();

    }

    public DigAction(Location digLocation, Ground newGround) {
        this.digLocation = digLocation;
        this.newGround = newGround;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Ground orginalGround = digLocation.getGround();
        digLocation.setGround(newGround);
        return orginalGround + "has been dig and replaced to" + newGround;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
