package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.function.Supplier;

public class SpawnActorEffect implements Effect {
    private final Supplier<Actor> actorSupplier;
    private final Location spawnLocation;

    public SpawnActorEffect(Supplier<Actor> actorSupplier, Location spawnLocation) {
        this.actorSupplier = actorSupplier;
        this.spawnLocation = spawnLocation;
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        if (spawnLocation != null && !map.isAnActorAt(spawnLocation) && spawnLocation.canActorEnter(actorSupplier.get())) {
            map.addActor(actorSupplier.get(), spawnLocation);
        }
    }
}