package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.Status;

public class StatusAttackAction extends Action{

    private String direction;
    private Actor target;
    private WeaponItem weapon;
    private Status status;
    /**
     * Constructor.
     *
     * @param target target actor of attack
     * @param direction direction target is from actor
     * @param weapon weapon used in attack
     * @param status item representation of status to be used in the attack
     */
    public StatusAttackAction(Actor target, String direction, WeaponItem weapon, Status status) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
        this.status = status;
    }

    /**
     * return new attack action but modify damage and hit rate
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened when action is executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Add status to target
        Display display = new Display();
        display.println("Inflicted "+target+" with "+status);
        target.addCapability(status);

        String result = new AttackAction(target, direction, weapon).execute(actor, map);

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
        return actor + " inflicts " + status + " and stabs " + this.target + " at " + this.direction + " with " + this.weapon;
    }

}
