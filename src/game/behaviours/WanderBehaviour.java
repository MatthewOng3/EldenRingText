package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DespawnAction;
import game.actors.NPC;
import game.enemies.Enemy;
import game.util.RandomNumberGenerator;

/**
 * <h1>Wander Behaviour</h1>
 * Wander behaviour class that returns a move actor action to a random location each turn
 * Created by: Matt
 * @author Riordan D. Alfredo
 * Modified by: Matt
 *
 */
public class WanderBehaviour implements Behaviour {
	
	private final Random random = new Random();
	/**
	 * Boolean attribute passed in to let wander behaviour know if the actor implementing this behaviour can or can't despawn while wandering
	 * */
	private boolean cannotDespawn = false;

	public WanderBehaviour(boolean cannotDespawn){
		this.cannotDespawn = cannotDespawn;
	}

	public WanderBehaviour(){}
	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param NPC the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(NPC NPC, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();

		//If actor implementing this behaviour can despawn when wandering, then do the despawn check, else if cannot despawn is true, then skip
		if(cannotDespawn == false){
			//10% chance for Enemy to despawn by calling despawnable interface method
			if(RandomNumberGenerator.calculateChance(10)) {
				return new DespawnAction();
			}
		}


		for (Exit exit : map.locationOf(NPC).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(NPC)) {
            	actions.add(exit.getDestination().getMoveAction(NPC, exit.getName(), exit.getHotKey()));
            }
        }

		//If action list is not empty return a random exit
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}


	}


}
