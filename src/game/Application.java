/**
 * The main class to setup and run the game.
 *
 * @author Adrian Kristanto
 */
package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.actors.npc.NpcGuts;
import game.actors.npc.NpcKale;
import game.actors.npc.NpcSellen;
import game.grounds.Blight;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.Wall;
import game.healing.items.Talisman;
import game.plants.BloodroseSeed;
import game.plants.InheritreeSeed;
import game.ui.FancyMessage;
import java.util.Arrays;
import java.util.List;

public class Application {

    /**
     * Main method to run the game application. Sets up the game world, map, player, NPCs, and
     * items.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(), new Wall(),
                new Floor(), new Soil());

        List<String> map = Arrays.asList("xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");

        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap); //

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line); //
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100);
        world.addPlayer(player, gameMap.at(36, 12));

        player.addItemToInventory(new BloodroseSeed());
        player.addItemToInventory(new InheritreeSeed());

        // Initialize the Creatures
        SpiritGoat spiritGoat = new SpiritGoat();
        OmenSheep omenSheep = new OmenSheep();
        GoldenBeetle goldenBeetle = new GoldenBeetle();

        // Initialize the NPCs
        NpcSellen NPCSellen = new NpcSellen();
        NpcKale NPCKale = new NpcKale();
        NpcGuts NPCGuts = new NpcGuts();

        // game setup
        gameMap.addActor(spiritGoat, gameMap.at(24, 13));
        gameMap.addActor(omenSheep, gameMap.at(24, 12));
        gameMap.addActor(goldenBeetle, gameMap.at(24, 14));

        gameMap.addActor(NPCSellen, gameMap.at(10, 5));
        gameMap.addActor(NPCKale, gameMap.at(35, 12));
        gameMap.addActor(NPCGuts, gameMap.at(5, 13));

        gameMap.at(24, 11).addItem(new Talisman()); //
        world.run();
    }
}