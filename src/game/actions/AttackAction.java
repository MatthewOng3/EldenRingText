package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.util.Status;

/**
 * <h1>Attack Action</h1>
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;
	/**
	 * Default damage multiplier
	 */
	private int damageMultiplier = 1;
	private int accuracy = 0;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 * @param weapon weapon that is being used to perform the action
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with additional parameters to modify weapon damage and accuracy
	 *
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 * @param weapon weapon that is being used to perform the action
	 * @param damageMultiplier, damage modifier for special attacks
	 * @param accuracy, accuracy modifier for special attacks
	 */
	public AttackAction(Actor target, String direction, Weapon weapon, int damageMultiplier, int accuracy){
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
		this.damageMultiplier = damageMultiplier;
		this.accuracy = accuracy;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		int chanceToHit;
		//If accuracy value is not the initialised value, then use instance accuracy variable
		if(this.accuracy != 0){
			chanceToHit = this.accuracy;
		}
		//Else use default weapon accuracy
		else{
			chanceToHit = weapon.chanceToHit();
		}

		if (!(rand.nextInt(100) <= chanceToHit)) {
			return actor + " misses " + target + ".";
		}


		int damage = weapon.damage() * this.damageMultiplier;

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		//Trigger death action if target has no revivable capability and has 0 hp
		if (!target.isConscious() && !target.hasCapability(Status.REVIVABLE) && !target.hasCapability(Status.CAN_SUMMON)) {
			result += new DeathAction(actor).execute(target, map);
		}

		return result;
	}


	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}
}
