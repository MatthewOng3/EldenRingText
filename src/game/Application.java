package game;


import java.util.Arrays;

import java.util.List;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.FingerReaderEnia;
import game.actors.MerchantKale;
import game.actors.Player;
import game.archetypes.Archetype;
import game.archetypes.ArchetypeCreation;
import game.factory.EastEnemyFactory;
import game.factory.WestEnemyFactory;
import game.grounds.*;
import game.items.RemembranceOfTheGrafted;
import game.managers.ResetManager;
import game.items.GoldenRunes;
import game.util.FancyMessage;
import game.weapons.PoisonedKnife;


/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Everyone
 *
 * */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Cliff(), new Cage(), new Barrack(), new SummonSign(), new ToxicTown());

		List<String> map = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................................................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+............................................................",
				"..............++...............=...........................................",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		List<String> stormveil = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",

				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____..............<..............<..............................",
				".........____..............................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String > roundTableHold = Arrays.asList("##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");

		List<String > bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++"
		);

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		GameMap stormVeilMap = new GameMap(groundFactory, stormveil);
		world.addGameMap(stormVeilMap);

		GameMap roundTableHoldMap = new GameMap(groundFactory, roundTableHold);
		world.addGameMap(roundTableHoldMap);

		GameMap bossRoomMap = new GameMap(groundFactory, bossRoom);
		world.addGameMap(bossRoomMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}


		//Initialise environments
		gameMap.at(17, 11).setGround(new GustOfWind(new WestEnemyFactory()));
		gameMap.at(50, 11).setGround(new GustOfWind(new EastEnemyFactory()));
		gameMap.at(27, 11).setGround(new Graveyard(new WestEnemyFactory()));
		gameMap.at(50, 8).setGround(new Graveyard(new EastEnemyFactory()));
		gameMap.at(27, 23).setGround(new PuddleOfWater(new WestEnemyFactory()));
		gameMap.at(58, 20).setGround(new PuddleOfWater(new EastEnemyFactory()));

		//Initialise site of lost graces and activate first spawn point
		Location firstLocation = gameMap.at(37, 11);
		SiteOfLostGrace firstStep = new SiteOfLostGrace("First step", firstLocation);
		firstStep.activateLostGrace();
		firstStep.setRested();
		firstLocation.setGround(firstStep);

		Location roundTableSite =  roundTableHoldMap.at(9,4);
		roundTableSite.setGround(new SiteOfLostGrace("Table of lost grace", roundTableSite));

		Location stormveilMain =  stormVeilMap.at(44,2);
		stormveilMain.setGround(new SiteOfLostGrace("Stormveil Main Gate", stormveilMain));


		//Register original spawn site
		ResetManager.getInstance().registerSpawnSite(gameMap.at(37, 11));

		//Choose an archetype
		Archetype chosenArchetype = ArchetypeCreation.getInstance().createArchetype();

		//Initialise player and other actors
		Player player = new Player("Tarnished", '@', chosenArchetype);
		gameMap.at(40, 12).addActor(new MerchantKale());
		roundTableHoldMap.at(9,0).addActor(new FingerReaderEnia());

		roundTableHoldMap.at(10,2).addItem(new RemembranceOfTheGrafted());
		roundTableHoldMap.at(11,2).addItem(new RemembranceOfTheGrafted());
		world.addPlayer(player, gameMap.at(37, 11)); //CHANGE THIS BACK AFTER TESTING


		//add golden runes
		gameMap.at(30, 11).addItem(new GoldenRunes());
		roundTableHoldMap.at(5,5).addItem(new GoldenRunes());

		// initialise the doors
		gameMap.at(29,2).setGround(new GoldenFogDoor(stormVeilMap,1,1, "Stormveil Castle"));
		gameMap.at(6,22).setGround(new GoldenFogDoor(roundTableHoldMap,9,10, "Round Table Hold"));
		stormVeilMap.at(1,1).setGround(new GoldenFogDoor(gameMap,29,2, "Limgrave"));
		stormVeilMap.at(38,23).setGround(new GoldenFogDoor(bossRoomMap,12,7, "Boss Room"));
		roundTableHoldMap.at(9,10).setGround(new GoldenFogDoor(gameMap,6,22, "Limgrave"));
		world.run();
	}
}
