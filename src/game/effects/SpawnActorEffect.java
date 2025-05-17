package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class SpawnActorEffect implements Effect {

    private final Supplier<Actor> actorSupplier;
    private final Actor spawnNearActor;

    public SpawnActorEffect(Supplier<Actor> actorSupplier, Actor spawnNearActor) {
        this.actorSupplier = actorSupplier;
        this.spawnNearActor = spawnNearActor;
    }

    public SpawnActorEffect(Supplier<Actor> actorSupplier) {
        this(actorSupplier, null);
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        Actor targetActor = (spawnNearActor != null) ? spawnNearActor : actor;
        Location actorLocation = map.locationOf(targetActor);
        ArrayList<Location> locations = new ArrayList<>();
        Random random = new Random();

        for (Exit exit : actorLocation.getExits()) {
            Location location = exit.getDestination();
            if (location.canActorEnter(actor)) {
                locations.add(location);
            }
        }
        if (!locations.isEmpty()) {
            int randomIndex = random.nextInt(locations.size());
            Location spawnLocation = locations.get(randomIndex);
            // Check if the destination is valid for spawning
            if (!map.isAnActorAt(spawnLocation) && spawnLocation.canActorEnter(
                    actorSupplier.get())) {
                map.addActor(actorSupplier.get(), spawnLocation);

            }
        }

    }
}