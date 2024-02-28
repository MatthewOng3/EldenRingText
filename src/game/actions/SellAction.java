package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.RunesManager;
import game.weapons.Sellable;

/**
 * <h1>Sell Action</h1>
 * An Action to sell items.
 * This class represents an actor can take to sell a weapon item for a runes. The selling actor
 *  * need to have the weapon item in their inventory in order to perform the sell action.
 * Created by:
 * @author Tsun Law
 * Modified by:
 *
 */
public class SellAction extends Action {
    /**
     * Sellable item
     */
    private final Sellable sellingItem;

    /**
     * Set the constructor for the PurchaseAction class
     * @param sellingItem, Sellable item being sold
     */
    public SellAction(Sellable sellingItem) {
        this.sellingItem = sellingItem;
    }

    /**
     * Executes the sell action, remove weapon from seller inventory using sold by method and add the sell price to the actor's runes in rune manager
     *
     * @param sellingActor The actor performing the action.
     * @param map The map the actor is on
     * @return A message describing the outcome of the action.
     */

    @Override
    public String execute(Actor sellingActor, GameMap map) {
        sellingItem.soldBy(sellingActor);
        RunesManager.getInstance().addRunes(sellingActor, this.sellingItem.getSellPrice());
        return  sellingActor + " sold " + this.sellingItem + " for " + this.sellingItem.getSellPrice()+ " runes";
    }

    /**
     * Returns a description of the sell action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sell " + this.sellingItem;
    }

}
