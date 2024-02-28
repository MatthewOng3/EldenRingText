package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.Status;
import game.actions.SellAction;

/**
 * Interface for the sellable items
 * @author Tsun Law
 * @version 1.0
 * Modified by: Matt
 */
public interface Sellable {
    /**
     * Method to retrieve the sell price of weapon
     * @return integer representing sell price of weapon
     * */
    int getSellPrice();
    /**
     * Method to be executed in sell action to remove the sellable weapon from inventory
     * @param sellingActor, actor doing the selling
     * */
    void soldBy(Actor sellingActor);

}
