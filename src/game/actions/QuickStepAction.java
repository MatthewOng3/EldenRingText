package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * <h1>QuickstepAction</h1>
 * QuickstepAction that attacks a target and then moves actor to free space
 *
 * @author Kenny
 * Modified by: Matthew
 * */
public class QuickStepAction extends Action {
    /**
     * Attribute to store the target of the quick step action
     */
    Actor target;
    WeaponItem weapon;
    String direction;
    public QuickStepAction(Actor target, WeaponItem weapon, String direction){
        this.target = target;
        this.weapon = weapon;
        this.direction = direction;
    }
    /**
     * Attribute to store the target of the quick step action
     *
     * @param performingActor actor performing the attack
     * @param map instance of Game map attack has occurred on
     * @return A description of what happened when action is executed
     */
    @Override
    public String execute(Actor performingActor, GameMap map) {
        System.out.println(performingActor);
        String result="";
        //Executes an attack action
        result += new AttackAction(this.target, this.direction, this.weapon).execute(performingActor,map);
        //Check surrounding locations to see if moving to another location is possible and execute a move actor actio
        for (Exit exit : map.locationOf(performingActor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(performingActor)) {
                new MoveActorAction(destination, "around").execute(performingActor, map);
                result += System.lineSeparator() + performingActor + " performs quick step and moves away ";
                return result;
            }
        }
        //If no movement is possible then print action not able to be performed
        result += " Quick step moving action was not able to be performed ";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + this.target + " at " + this.direction + " with " + this.weapon + " and performs quick step ";
    }
}
