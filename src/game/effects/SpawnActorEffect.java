package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;

import java.util.List;
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
        Location location = map.locationOf(targetActor);
        List<Exit> exits = location.getExits();
        // Select a random exit
        Random random = new Random();
        Exit randomExit = exits.get(random.nextInt(exits.size()));
        // Check if the destination is valid for spawning
        Location destination = randomExit.getDestination();
        if (!map.isAnActorAt(destination) && destination.canActorEnter(actorSupplier.get())) {
            map.addActor(actorSupplier.get(), destination);

        }
    }
}