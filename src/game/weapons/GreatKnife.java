package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.QuickStepAction;
import game.util.Status;

/**
 * <h1>GreatKnife</h1>
 * A knife of a bandit
 * It deals 75 damage with 70% hit rate
 * @author Tsun
 * @version 1.0
 */
public class GreatKnife extends WeaponItem implements Purchasable, Sellable{
    /**purchase price of weapon
     * */
    private static final int purchasePrice = 3500;
    /**selling price of weapon
     * */
    private static final int sellPrice = 350;


    /**
     * Constructor
     */
    public  GreatKnife() {
        super(" Great Knife", '/', 75, "stab", 70);
        this.addCapability(Status.WEAPON_SKILL);
        this.addCapability(Status.TARGETED_SKILL);
    }

    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }

    /**
     * Returns a QuickStepAction
     * @param target target of QuickStepAction
     * @param direction direction of target from actor
     * @see WeaponItem
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(target, this, direction);
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
