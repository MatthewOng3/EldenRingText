package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;

/**
 * <h1>Club</h1>
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Matt
 */
public class Club extends WeaponItem implements Purchasable, Sellable {
    /**purchase price of weapon
     * */
    private static final int purchasePrice = 600;
    /**selling price of weapon
     * */
    private static final int sellPrice = 100;


    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }
    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }
    /**
     * Method to be executed in purchase action to add this weapon to purchasing actor inventory
     * @param purchasingActor, actor doing the purchasing
     * */
    public void purchasedBy(Actor purchasingActor){
        purchasingActor.addWeaponToInventory(this);
    }
    /**
     * Getter for purchase price
     * @return  purchasePrice, integer
     * */
    public int getPurchasePrice() {
        return purchasePrice;
    }
    /**
     * Getter for selling price
     * @return  sellPrice, integer
     * */
    public int getSellPrice() {
        return sellPrice;
    }
}
