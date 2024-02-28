package game.actors;

import game.util.ExchangeCurrency;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * <h1>Finger Reader Enia</h1>
 * Finger Reader Enia class that allows tarnished to exchange remembrance of grafted with axe of godrick and grafted dragon
 * @author Tsun Law
 * modified by: Matt
 * */
public class FingerReaderEnia extends ExchangeTrader {
    /**
     * Constructor adding in exchangeable items and setting desired currency
     * */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', Integer.MAX_VALUE);
        this.addExchangeableItem(new AxeOfGodrick());
        this.addExchangeableItem(new GraftedDragon());
        this.setDesiredCurrency(ExchangeCurrency.REMEMBRANCE);
    }


}
