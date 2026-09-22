# Inheritree — a text-based RPG in Java

A turn-based, text-rendered role-playing game inspired by *Elden Ring*, built
in Java on top of a small object-oriented game engine. The player is a Farmer
exploring two maps — the *Valley of the Inheritree* and *Limveld* — where the
land is cursed with Blight. Plant sacred trees to cure the ground, fight
creatures and a growing boss, cast spells, fish, trade with merchants and
talk to NPCs.

Developed by Haruto Iriyama as part of a five-person team over two design
iterations. The engine (`src/edu/…/engine`) was provided; everything under
`src/game` is the team's own code.

## Features

| Area | What's there |
|---|---|
| **World** | Two maps linked by teleportation gates; ground types with capabilities (Blight, Soil, Floor, Wall, Pond, burning / temporary ground) |
| **Creatures** | Spirit Goat, Omen Sheep, Golden Beetle — each with priority- or random-based behaviour selection (wander, follow, attack, produce) |
| **Bed of Chaos boss** | A boss that *grows* every turn: branches and leaves are recursively spawned as `BossPart`s, and its attack power scales with the parts it has grown |
| **Eggs & hatching** | Creatures lay eggs; eggs hatch under rule-based conditions (timed, cursed-environment) and can be eaten for buffs |
| **Plants** | Inheritree and Bloodrose seeds; planting cures Blight, trees heal nearby actors, Bloodrose damages them |
| **Combat** | Weapon items (Broadsword, Katana, Dragonslayer Greatsword) and intrinsic weapons; attack actions driven by capabilities |
| **NPCs & trade** | Sellen, Kale and Guts with monologues that change with game state; merchants sell weapons whose purchase applies effects (heal, max-HP/stamina, spawn a creature) |
| **Spells** | Spellbook with Fire, Heal, Poison and Teleport spells that cost mana and apply effects to actors or ground |
| **Fishing** | Fishing rod and pond with weighted catch table (salmon, golden fish, toxic eel, old boot) plus a shovel to dig up plants |
| **Healing** | Talisman that cures cursed actors and ground |

### Design highlights

- **Composition over inheritance.** Behaviours (`AttackBehaviour`, `FollowBehaviour`, `WanderBehaviour`, `ProduceBehaviour`, `GrowPartBehaviour`) are plugged into actors through a `BehaviourSelector`, so a new creature is a configuration rather than a class hierarchy.
- **Conditions and effects as first-class objects.** `Condition` (low health, low runes, nearby capability, turn-based, …) and `Effect` (damage, heal, continuous damage, spawn actor, …) are small composable interfaces; NPC dialogue, merchant offers and spells all reuse them.
- **Capabilities, not `instanceof`.** Ground, items and actors advertise `GeneralCapability` / `GroundCapability` enums, and actions check capabilities, which keeps the engine and game decoupled.
- **Recursive boss growth.** `BedOfChaos` holds a tree of `Branch`/`Leaf` parts; growth is a recursive walk that lets branches sprout further branches or leaves, and damage is summed over the tree.

My own contributions were centred on the creature/egg/hatching system, the
`Produce` behaviour and the Bed of Chaos boss (the recursive `BossPart`
design, `GrowPartAction` / `GrowPartBehaviour` and its weapon).

## Running

Requires JDK 17+.

```bash
javac -d out $(find src -name "*.java")
java -cp out game.Application
```

Or open the project in IntelliJ IDEA and run `game.Application`. The game is
played in the terminal: each turn lists the available actions and you type
the key of the one you want.

## Documentation

- `docs/design/game.png`, `docs/design/engine.png` — class diagrams of the game and engine packages
- `docs/design/uml_spells_and_fishing.png` — UML for the spell and fishing systems
- `docs/design/iteration-1/`, `docs/design/iteration-2/` — design rationale, UML and sequence diagrams for each feature in the two iterations
