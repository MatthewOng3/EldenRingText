package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.QuickStepAction;
import game.actions.AttackAction;
import game.actions.StatusAttackAction;
import game.util.Status;
/**
 * <h1>PoisonedKnife</h1>
 * PoisonedKnife class extends Weaponitem and implements Sellable, Purchasable
 * It is a new weapon that can inflict a status onto the target
 *
 * Created by:
 * @author Kenny
 * Modified by: Matthew
 *
 */
public class PoisonedKnife extends WeaponItem implements Sellable, Purchasable{
    /**
     * Purchase price of weapon
     * */
    private static final int purchasePrice = 1500;
    /**
     * Selling price of weapon
     * */
    private static final int sellPrice = 100;
    /**
     * Status to be inflicted by weapon
     * */
    private Status status = Status.POISON;
    /**
     * Constructor
     */
    public  PoisonedKnife(){
        super("Poisoned Knife", '{', 50, "stabs", 90);
        this.addCapability(Status.WEAPON_SKILL);
        this.addCapability(Status.TARGETED_SKILL);
    }


    /**
     * Returns an attack action that inflicts poison
     * @param target target of QuickStepAction
     * @param direction direction of target from actor
     * @see WeaponItem
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new StatusAttackAction(target, direction, this, status);
    }


    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public void soldBy(Actor sellingActor) {
        sellingActor.removeWeaponFromInventory(this);
    }

    @Override
    public int getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public void purchasedBy(Actor purchasingActor) {
        purchasingActor.addWeaponToInventory(this);
    }
}
