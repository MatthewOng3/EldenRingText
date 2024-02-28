
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.RunesManager;
import game.weapons.Purchasable;


/**
 * <h1>Purchase Action</h1>
 * An Action to purchase items from merchant
 * This class represent that the actor can purchase an item from the merchant and the item
 * need to implemt the Purchaseable interface to get the purchase price
 * @author Tsun Law
 * Modified by: Matt
 *
 */
public class PurchaseAction extends Action {

    /**
     * Purchasable item
     * */
    private final Purchasable purchasableItem;


    /**
     * Set the constructor for the PurchaseAction class
     * @param purchasableItem the Item to purchase
     */
    public PurchaseAction(Purchasable purchasableItem){
        this.purchasableItem = purchasableItem;
    }

    /**
     * Get the purchase price of item and remove the purchase price, and add it to inventory
     *
     * @param purchasingActor The actor making the purchase
     * @param map The map the actor is on
     * @return A message describing the outcome of the action.
     */
    @Override
    public String execute(Actor purchasingActor, GameMap map){
        int purchasePrice = this.purchasableItem.getPurchasePrice();

        if (RunesManager.getInstance().removeRunes(purchasingActor, purchasePrice)) {
            //Call purchased by method in purchasable interface to add weapon to inventory
            purchasableItem.purchasedBy(purchasingActor);
            return purchasingActor + " purchased " + purchasableItem + " for " + purchasePrice + " runes";
        } else {
            return purchasingActor + " does not have enough runes to purchase" + purchasableItem;
        }
    }

    /**
     * Returns a description of the purchase action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + purchasableItem;
    }

}