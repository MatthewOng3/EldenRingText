package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * <h1>Grafted Dragon</h1>
 * Weapon that drops from godrick
 * */
public class GraftedDragon extends WeaponItem implements Sellable, Exchangeable {
    private static final int sellPrice = 200;

    public GraftedDragon() {
        super("Grafted Dragon ", 'N', 89, "slices", 90);
    }

    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public void exchange(Actor exchangingActor, Item itemToExchangeWith) {
        exchangingActor.addWeaponToInventory(this);
        exchangingActor.removeItemFromInventory(itemToExchangeWith);
    }
}
