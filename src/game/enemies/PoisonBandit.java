package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.WeaponAttackForm;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;
import game.weapons.PoisonedKnife;
/**
 * <h1>PoisonBandit</h1>
 * PoisonBandit class extends Enemy
 * It is a new enemy that wields a poisoned knife and spawns from toxictown
 *
 * Created by:
 * @author Kenny
 * Modified by: Matthew
 *
 */
public class PoisonBandit extends Enemy{
    /**
     * Constructor to initialise the capabilities and add the FACTION and the region and ground it spawns from
     * */
    public PoisonBandit() {
        super("Poison Bandit", 'w', 102);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set weapon
        this.addWeaponToInventory(new PoisonedKnife());
        //Set faction
        this.setFaction(Faction.BANDIT);
        //Set death runes amount
        this.setDeathRunesAmount(100,1500);
        //Set attack form
        this.setAttackForm(new WeaponAttackForm(80, this.getWeapon()));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }



    /**
     * Retrieve the intrinsic weapon of the current class
     * */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "bites", 95);
    }
}
