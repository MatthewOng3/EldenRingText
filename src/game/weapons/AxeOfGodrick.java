package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * <h1>Axe of Godrick</h1>
 * Weapon that spawns from godrick
 * */
public class AxeOfGodrick extends WeaponItem implements Sellable, Exchangeable {
    private static final int sellPrice = 100;

    public AxeOfGodrick() {
        super("Axe Of Godrick ", 'T', 142, "slashes", 84);
    }

    @Override
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void exchange(Actor exchangingActor, Item itemToExchangeWith) {
        exchangingActor.addWeaponToInventory(this);
        exchangingActor.removeItemFromInventory(itemToExchangeWith);
    }
}
