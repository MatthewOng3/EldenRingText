package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.util.Status;
import game.actions.AreaAttackAction;

/**
 * <h1>Grossmesser</h1>
 * A curved sword usually carried around by Heavy Skeletal Swordsman
 * It deals 115 damage with 85% hit rate
 * @author Matt
 * @version 1.0
 */
public class Grossmesser extends WeaponItem implements Sellable{
    private static final int sellPrice = 100;

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slashes", 85);
        this.addCapability(Status.WEAPON_SKILL);
    }
    /**
     * Return area attack action
     * @param holder, actor holding the weapon
     * @return instance of area attack action
     * */
    @Override
    public Action getSkill(Actor holder) {
        return new AreaAttackAction(this);
    }
    /**
     * Method to be executed in sell action to remove this weapon from selling actor inventory
     * @param sellingActor, actor doing the selling
     * */
    public void soldBy(Actor sellingActor){
        sellingActor.removeWeaponFromInventory(this);
    }
    /**
     * Getter for selling price
     * @return  sellPrice, integer
     * */
    public int getSellPrice() {
        return sellPrice;
    }

}
