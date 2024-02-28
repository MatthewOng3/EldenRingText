package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * <h1>Unsheath Action</h1>
 * Unsheath Action class used by the Uchigatana to double its damage at a 60 % hit rate
 *
 * @author Kenny
 * @version 1.0
 */

public class UnsheatheAction extends Action {
    private String direction;
    private Actor target;
    private WeaponItem weapon;
    /**
     * Constructor.
     *
     * @param target target actor of attack
     * @param direction direction target is from actor
     * @param weapon weapon used in attack
     *
     */
    public UnsheatheAction(Actor target, String direction, WeaponItem weapon) {

        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Execute new attack action but modify damage and hit rate
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened when action is executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = new AttackAction(target, direction, weapon, 2, 60).execute(actor, map);

        return result;
    }

    /**
     * Describes the attacking action on the menu UI
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes and attacks " + this.target + " at " + this.direction + " with " + this.weapon;
    }

}
