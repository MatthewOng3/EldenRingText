package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.gameplay.Resettable;
import game.managers.RunesManager;
import game.util.RandomNumberGenerator;
import game.util.Status;
/**
 * <h1>GoldenRunes</h1>
 * GoldenRunes class that extends item and implements Consumable
 * used to pass a consume action that when executed will add a random amount of runes to the actor
 *
 * @author Kenny
 * */
public class GoldenRunes extends Item implements Consumable{
    /**
     * Integer representing runes stored
     * */
    private int runes;
    /**
     * Constructor
     *
     * */
    public GoldenRunes() {
        super("GoldenRunes", '*', true);
        this.addAction(new ConsumeAction(this));
        this.addCapability(Status.CONSUMABLE);
        this.runes = RandomNumberGenerator.getRandomInt(200,10000);
    }
    /**
     * Consume method to add runes to actor and remove item from inventory
     * */
    @Override
    public String consume(Actor consumingActor) {
        RunesManager.getInstance().addRunes(consumingActor,runes);
        consumingActor.removeItemFromInventory(this);
        return "You consumed the Golden Runes and gained " + runes + " runes.";
    }


}
