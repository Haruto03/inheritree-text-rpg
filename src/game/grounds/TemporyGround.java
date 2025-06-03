package game.grounds;


import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class TemporyGround extends Ground {
    private final Ground originalGround;
    private final Ground temporaryground;
    private int duration;

    public TemporyGround(Ground originalGround, Ground temporaryground, int duration) {
        super(temporaryground.getDisplayChar(), temporaryground.toString());
        this.originalGround = originalGround;
        this.temporaryground = temporaryground;
        this.duration = duration;
    }


    @Override
    public void tick(Location location) {
        // If there are actors on this tile, you might apply damage per turn here
        temporaryground.tick(location);

        if (this.duration > 0) {
            this.duration--;
            if (this.duration <= 0) {
                // Duration has expired, revert to the original ground
                location.setGround(this.originalGround);
            }
        }
    }
}

