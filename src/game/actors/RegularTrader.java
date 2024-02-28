package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.util.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularTrader extends Actor{
    /**
     * List of purchasable instances to represent items that can be purchased
     */
    private final List<Purchasable> merchantInventory = new ArrayList<>();
    /**
     * Constructor
     *
     * @param merchantName
     * @param displayChar
     * @param hitPoints
     */
    public RegularTrader(String merchantName, char displayChar, int hitPoints) {
        super(merchantName, displayChar, hitPoints);
        this.addCapability(Status.NOT_ATTACKABLE);
        this.addCapability(Faction.ALLIES);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Add a weapon to this merchant's inventory
     * @param purchasableItem
     */
    public void addPurchasableItem(Purchasable purchasableItem) {
        merchantInventory.add(purchasableItem);
    }

    /**
     * Get a copy of this merchant's purchasable inventory
     *
     * @return An unmodifiable wrapper of the merchant inventory
     */
    public List<Purchasable> getMerchantInventory() {
        return Collections.unmodifiableList(merchantInventory);
    }

    /**
     * This method returns sell actions for every sellable weapon in otherActor inventory and purchase action for every weapon in merchant kale inventory
     *
     * @param otherActor the Actor that can interact with the merchant
     * @param direction  String representing the direction of the other Actor, set in the location exits and passed into processActorTurn in world game loop
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //If actor has the capability of interacting with trader
        if (otherActor.hasCapability(Status.CAN_INTERACT_TRADER)) {
            //Loop through the otherActor's inventory, create a new purchase  and sell action for each one
            for (Purchasable purchasableItem : this.getMerchantInventory()) {
                actions.add(new PurchaseAction(purchasableItem));
            }
            //Retrieve other actor's weapon inventory and cast each weapon to sellable and add new sell action for each one
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                actions.add(new SellAction((Sellable) weapon));
            }
        }
        return actions;
    }

}
