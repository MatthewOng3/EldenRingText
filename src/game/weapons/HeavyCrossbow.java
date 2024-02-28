package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.RangedAttackGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Heavy Crossbow</h1>
 * A heavy crossbow
 * It deals 64 damage with 57% hit rate
 * @author Matt
 * @version 1.0
 */
public class HeavyCrossbow extends RangedWeapon implements Purchasable, Sellable  {
    /**
     * Purchase price of weapon
     * */
    private static final int purchasePrice = 1500;
    /**
     * Selling price of weapon
     * */
    private static final int sellPrice = 100;

    public HeavyCrossbow() {
        super("Heavy Crossbow", '}', 64, "shoots", 57);
    }

    public int getRange() {
        return 2;
    }

    /**
     * Method to be executed in purchase action to add this weapon to purchasing actor inventory
     * @param purchasingActor, actor doing the purchasing
     * */
    public void purchasedBy(Actor purchasingActor){
        purchasingActor.addWeaponToInventory(this);
    }

    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
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
