package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Consumable</h1>
 * Interface for consumable items, they have a consume action that will be called to consume said item
 * */
public interface Consumable {
    /**
     * Consume method that will be implemented to perform some consume effect
     * @param consumingActor, actor that is doing the consuming
     * */
    String consume(Actor consumingActor);
}
