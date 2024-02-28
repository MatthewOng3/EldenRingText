package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.ExchangeCurrency;
import game.actions.ExchangeAction;
import game.actions.SellAction;
import game.util.Status;
import game.weapons.Exchangeable;
import game.weapons.Sellable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Exchange Trader</h1>
 * Parent trader class for exchange trader type, allows subclasses to easily define what items can be exchanged for
 * and what the trader accepts as currency to make the exchange
 * @author Matt
 * */
public class ExchangeTrader extends Actor {
    /**
     * Enum exchange currency
     * */
    protected Enum<ExchangeCurrency> desiredCurrency;
    /**
     * Exchangeable inventory that stores the items subclasses offer for exchange
     * */
    private final List<Exchangeable> exchangeableInventory = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public ExchangeTrader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.NOT_ATTACKABLE);
        this.addCapability(Faction.ALLIES);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Setter to set in subclass the currency the exchange trader accepts
     * @param desiredCurrency enum of type ExchangeCurrency
     * */
    public void setDesiredCurrency(Enum<ExchangeCurrency> desiredCurrency){
        this.desiredCurrency = desiredCurrency;
    }

    /**
     * Getter for the exchange trader's exchange currency
     * @return desiredCurrency attribute enum
     * */
    public Enum<ExchangeCurrency> getDesiredCurrency(){
        return this.desiredCurrency;
    }

    /**
     * Adds exchangeable item into the exchange trader inventory
     * @param item, item that can be exchanged for, of Exchangeable interface type
     * */
    public void addExchangeableItem(Exchangeable item){
        this.exchangeableInventory.add(item);
    }

    /**
     * This method returns sell actions for every sellable weapon in otherActor inventory and exchange action if other actor has the item
     * that can be used to exchange.
     *
     * @param otherActor the Actor that can interact with the merchant
     * @param direction  String representing the direction of the other Actor, set in the location exits and passed into processActorTurn in world game loop
     * @param map        current GameMap
     * @return list of actions the other actor can perform with this trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //If actor has the capability of interacting with trader
        if (otherActor.hasCapability(Status.CAN_INTERACT_TRADER)){
            for(Item item : otherActor.getItemInventory()){
                //If other actor has an item that is the  desired currency
                if(item.hasCapability(this.getDesiredCurrency())){
                    for(Exchangeable exchangeableItem: this.exchangeableInventory){
                        actions.add(new ExchangeAction(exchangeableItem, item));
                    }
                    break;
                }
            }
            //Retrieve other actor's weapon and item inventory and cast each weapon to sellable and add new sell action for each one
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                actions.add(new SellAction((Sellable) weapon));
            }
            for (Item item : otherActor.getItemInventory()) {
                if(!item.hasCapability(Status.NOT_SELLABLE)){
                    actions.add(new SellAction((Sellable) item));
                }

            }
        }
        return actions;
    }
}
