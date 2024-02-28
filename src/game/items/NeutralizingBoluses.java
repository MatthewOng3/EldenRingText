package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.util.Status;
import game.weapons.Purchasable;

import java.util.ArrayList;
import java.util.List;
/**
 * <h1>NeutralizingBoluses</h1>
 * NeutralizingBoluses class that extends item and implements Consumable
 * used to pass a consume action that when executed will remove status.poison from the actor
 *
 * @author Kenny
 * */
public class NeutralizingBoluses extends Item implements Consumable{
    /**
     * Constructor
     *
     * */
    public NeutralizingBoluses() {
        super("Neutralizing Boluses", '8', true);
        this.addAction(new ConsumeAction(this));
        this.addCapability(Status.CONSUMABLE);
        this.addCapability(Status.NOT_SELLABLE);
    }
    /**
     * Consuming actor consumes and removes poison status
     * */
    @Override
    public String consume(Actor consumingActor) {

        if (consumingActor.hasCapability(Status.POISON)){
            consumingActor.removeCapability(Status.POISON);
        }

        consumingActor.removeItemFromInventory(this);

        return "You have consumed the Boluses and cured Poison";
    }
}
