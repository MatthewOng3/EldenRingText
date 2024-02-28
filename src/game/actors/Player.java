package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DropRunesAction;
import game.items.NeutralizingBoluses;
import game.gameplay.Resettable;
import game.util.FancyMessage;
import game.util.Status;
import game.archetypes.Archetype;
import game.items.FlaskOfCrimsonTears;
import game.managers.ResetManager;
import game.managers.RunesManager;
import game.weapons.HeavyCrossbow;

/**<h1>Player</h1>
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Matt
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	/**
	 * Attribute to store the previous location of player each turn
	 * */
	private Location previousLocation;

	/**
	 * Constructor.
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param archetype   The archetype class that player chose archetype.getStartingHitPoints()
	 */
	public Player(String name, char displayChar, Archetype archetype) {
		super(name, displayChar, archetype.getStartingHitPoints());

		//Add relevant capabilities
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.FOLLOWABLE);
		this.addCapability(Status.CAN_INTERACT_TRADER);
		this.addCapability(Status.RUNES_DROPPABLE);
		this.addCapability(Status.CAN_GAIN_RUNES);
		this.addCapability(Status.AFFECTED_BY_CLIFF);
		this.addCapability(Status.CAN_SUMMON);

		this.addCapability(Faction.ALLIES);
		this.addCapability(Faction.PLAYABLE);

		//Add starting weapon of archetype to inventory
		this.addWeaponToInventory(new HeavyCrossbow());
		//Add flask to inventory
		FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears();
		this.addItemToInventory(flask);

		//Register flask and player instance as resettable
		ResetManager.getInstance().registerResettable(flask);
		ResetManager.getInstance().registerResettable(this);

		//Add starting runes
		RunesManager.getInstance().addRunes(this, 0);

		//Add new consumable to remove poison
		this.addItemToInventory(new NeutralizingBoluses());

	}


	/**
	 * Takes an input list of actions, passes it into show menu() method which prints options to UI and receives input from user
	 * and returns the action associated with that input, executes it in world game loop
	 * @param actions, input list of possible actions player can perform , given from world run() method
	 * @param map, gamemap the current actor is on
	 * */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//Maybe add a seperate status check class but might be bad cuz dependency

		if(this.hasCapability(Status.POISON)){
			this.hurt(10);
		}

		if(!this.isConscious()){
			//Reset everything when player dies
			ResetManager.getInstance().resetEverything(map);
			//Drop runes
			new DropRunesAction(this.previousLocation).execute(this,map);

			//Print you died message
			for (String line : FancyMessage.YOU_DIED.split("\n")) {
				new Display().println(line);
			}

			//Return a move action to last rested spawn site
			return new MoveActorAction(ResetManager.getInstance().getSpawnSite(), "to last visited grace.");
		}

		// Handle multi-turn Actions by checking previous action getNextAction method, if none then take actions from action list
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//Registering previous location to current location for dropping runes
		this.previousLocation = map.locationOf(this);

		//Print player current hp, runes to console
		display.print(this + " " + this.hitPoints + "/" + this.maxHitPoints + "\n");
		display.println("Runes : " + RunesManager.getInstance().getRunesAmount(this) + "\n");

		return menu.showMenu(this, actions, display);
	}


	@Override
	public void reset(GameMap map) {
		//Remove statuses on reset
		this.removeCapability(Status.POISON);
		this.resetMaxHp(this.getMaxHp());
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punch", 90);
	}
}
