package game.actors;

import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.PoisonedKnife;
import game.weapons.Uchigatana;
/**
 * <h1>Merchant Kale</h1>
 * Merchant that buys and sells weapons
 * @author Matt
 * */
public class MerchantKale extends RegularTrader {

    public MerchantKale() {
        super("Merchant Kale", 'K', Integer.MAX_VALUE);
        this.addPurchasableItem(new Uchigatana());
        this.addPurchasableItem(new GreatKnife());
        this.addPurchasableItem(new Club());
        this.addPurchasableItem(new PoisonedKnife());
    }

}
