package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.Exchangeable;

/**
 * <h1>Exchange Action</h1>
 * Action to exchange items between 2 actors
 * @author Tsun Law
 * modified by: Matt
 * */
public class ExchangeAction extends Action {
    /**
     * Item that is being exchanged for
     * */
    private Exchangeable itemToExchange;
    /**
     * Item that is being given up
     * */
    private Item itemToGive;
    /**
     * Constructor
     * @param itemToExchange Exchangeable interface item
     * @param itemToGive Item being offered
     * */
    public ExchangeAction(Exchangeable itemToExchange, Item itemToGive) {
        this.itemToExchange = itemToExchange;
        this.itemToGive = itemToGive;
    }

    /**
     * Calls the exchange method of the exchangeable interface to exchange with exchange trader
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the executed action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        itemToExchange.exchange(actor, this.itemToGive);
        return actor + " exchanged " +  this.itemToGive + " for " + this.itemToExchange;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges " + this.itemToGive + " for " + this.itemToExchange;
    }
}