package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.Status;
import game.actions.UnsheatheAction;

/**
 * <h1>Uchiganta</h1>
 * A samurai sword
 * It deals 115 damage with 80% hit rate
 * @author Tsun
 * @version 1.0
 */

public class Uchigatana extends WeaponItem implements Purchasable, Sellable{
    /**
     * purchase price of weapon
     * */
    private static final int purchasePrice = 5000;
    /**
     * selling price of weapon
     * */
    private static final int sellPrice = 500;

    /**
     * Constructor
     */
    public  Uchigatana() {
        super(" Uchigatana", ')', 115, "slices", 80);
        this.addCapability(Status.WEAPON_SKILL);
        this.addCapability(Status.TARGETED_SKILL);
    }

    /**
     * Returns an UnsheatheAction
     * @param target target of unsheathe action
     * @param direction direction of target from actor
     * @see WeaponItem
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, direction,this);
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

    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }
}
