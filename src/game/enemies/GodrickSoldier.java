package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.WeaponAttackForm;
import game.behaviours.RangedAttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;
import game.weapons.HeavyCrossbow;

/**
 * <h1>Godrick Soldier</h1>
 * An enemy that spawns in the castle from barracks
 * @author Matt
 *
 */
public class GodrickSoldier extends Enemy{

    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        HeavyCrossbow crossbow = new HeavyCrossbow();
        //Set behaviours
        this.behaviours.put(1, new RangedAttackBehaviour(crossbow));
        this.behaviours.put(999, new WanderBehaviour());
        //Set faction
        this.setFaction(Faction.CASTLE_ENEMY);
        this.addWeaponToInventory(crossbow);
        //Set death runes amount
        this.setDeathRunesAmount(38,70);
        //Set attack form
        this.setAttackForm(new WeaponAttackForm(0, this.getWeapon()));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Retrieve the intrinsic weapon of the current class
     * @return Intrinsic weapon of the soldier
     * */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "whoops ass", 100);
    }
}
