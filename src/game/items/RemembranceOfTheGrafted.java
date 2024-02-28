package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.util.ExchangeCurrency;
import game.weapons.Sellable;
/**
 * <h1>Remembrance Of The Grafted</h1>
 * Class representing remembrance of the grafted item
 * @author Tsun Law
 * modified by: Matt
 * */
public class RemembranceOfTheGrafted extends Item implements Sellable {
    /**
     * Sell price of item
     * */
    private static final int sellPrice = 20000;

    /**
     * Constructor adding the currency type to the capability
     * */
    public RemembranceOfTheGrafted() {
        super(" Remembrance of the Grafted", 'o', true);
        this.addCapability(ExchangeCurrency.REMEMBRANCE);
    }

    @Override
    public void soldBy(Actor sellingActor){
        sellingActor.removeItemFromInventory(this);
    }

    public int getSellPrice() {
        return sellPrice;
    }

}
