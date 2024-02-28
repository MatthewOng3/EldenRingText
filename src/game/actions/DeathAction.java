package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.ResetManager;
import game.managers.RunesManager;
import game.util.Status;

/**
 * <h1>Death Action</h1>
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    /**
     * Attribute to store the attacker
     * */
    private Actor attacker;

    /**
     * Constructor that takes in the actor class of the attacking actor
     * */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }
    /**
     * Constructor that doesnt have any arguments
     * */
    public DeathAction(){}

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param dyingActor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor dyingActor, GameMap map) {
        String result = "";

        ActionList dropActions = new ActionList();
        //Drop all items if not a player
        if (dyingActor.hasCapability(Status.RUNES_DROPPABLE) == false) {
            for (Item item : dyingActor.getItemInventory())
                dropActions.add(item.getDropAction(dyingActor));
            for (WeaponItem weapon : dyingActor.getWeaponInventory())
                dropActions.add(weapon.getDropAction(dyingActor));
            for (Action drop : dropActions)
                drop.execute(dyingActor, map);
        }

        //If attack can gain runes from target, receive runes from target death
        if(this.attacker != null){
            if(attacker.hasCapability(Status.CAN_GAIN_RUNES) && dyingActor.hasCapability(Status.CAN_GIVE_RUNES) ){
                RunesManager.getInstance().gainRunesFromEnemy(attacker,dyingActor);
            }
        }
        // remove actor
        map.removeActor(dyingActor);
        result += System.lineSeparator() + menuDescription(dyingActor);

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
