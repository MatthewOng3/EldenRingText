package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.RangedAttackGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Astrologer's Staff</h1>
 * A staff carried by the Astrologer archetype
 * It deals 274 damage with 50% hit rate
 * @author Matt
 * @version 1.0
 */
public class AstrologerStaff extends RangedWeapon implements Purchasable, Sellable {
    private static final int sellPrice = 100;
    private static final int purchasePrice = 800;

    /**
     * Constructor
     * */
    public AstrologerStaff(){
        super("Astrologer's Staff", 'f', 274, "blasts", 50);
    }


    /**
     * Method to retrieve purchase price of item
     * @return static integer value of purchase price of weapon
     * */
    @Override
    public int getPurchasePrice() {
        return purchasePrice;
    }
    /**
     * Method to be executed in purchase action to add item to purchasing actor inventory
     * @param purchasingActor, actor doing the purchasing
     * */
    @Override
    public void purchasedBy(Actor purchasingActor) {
        purchasingActor.addWeaponToInventory(this);
    }
    /**
     * Method to retrieve sell price of item
     * @return static integer value of selling price of weapon
     * */
    @Override
    public int getSellPrice() {
        return sellPrice;
    }
    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    @Override
    public void soldBy(Actor sellingActor) {
        sellingActor.removeWeaponFromInventory(this);
    }

    @Override
    public int getRange() {
        return 3;
    }
}
