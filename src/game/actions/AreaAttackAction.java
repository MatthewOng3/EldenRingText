package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * <h1>Area Attack Action</h1>
 * Action subclass to perform an area attack on the surrounding
 * @author Matt
 * @verion 1.0
 * */
public class AreaAttackAction extends Action {

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor with using weapon
     * @param weapon, weapon of the actor performing the action
     */
    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     */
    public AreaAttackAction( ) {}

    /**
     * When executed, execute an attack action on every surrounding actor, chance to miss varies from each individual
     *
     * @param attackingActor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor attackingActor, GameMap map) {
        if(weapon == null){
            weapon = attackingActor.getIntrinsicWeapon();
        }

        String result = attackingActor + " attacks their surrounding!";

        //If its not the weapon doing the area attack, get the intrinsic weapon of the attacker
        for (Exit exit : map.locationOf(attackingActor).getExits()) {
            Location destination = exit.getDestination();
            //For every surrounding actor, execute the attack action on each one
            if (destination.containsAnActor()){
                result += "\n" + new AttackAction(destination.getActor(),exit.getName(), weapon).execute(attackingActor, map);
            }
        }

        return result;
    }

    /**
     * Describes if area attack is performed with weapon or without
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks the area with " + (weapon != null ? weapon : actor.getIntrinsicWeapon());
    }
}
