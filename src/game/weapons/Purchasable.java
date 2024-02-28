package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for the purchase price of each items
 * Created by:
 * @author Tsun Law
 * @version 1.0
 * Modified by: Matt
 */
public interface Purchasable {
    /**
     * Price of purchasable item
     * @return integer representing purchasable item price
     */
    int getPurchasePrice();
    /**
     * Adds the purchasable weapon to the actor's inventory once purchased
     * @param purchasingActor, actor that is doing the purchasing
     */
    void purchasedBy(Actor purchasingActor);
}
