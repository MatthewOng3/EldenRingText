package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * <h1>Exchangeable Interface</h1>
 * Interface that can be implemented by items or weapons, that allows them to be exchanged by an exchange trader
 * @author Tsun Law
 * modified by Matt
 * */
public interface Exchangeable {
    /**
     * Method that will be called in exchange action to add that exchangeable interface item to the actor inventory and removing itemToExchangeWith from their inventory
     * @param exchangingActor Actor executing the exchange action
     * @param itemToExchangeWith Item you are exchanging for
     * */
    void exchange(Actor exchangingActor, Item itemToExchangeWith);
}
