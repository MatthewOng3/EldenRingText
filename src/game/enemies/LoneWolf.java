package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.SpecialAttackForm;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameplay.Resettable;
import game.managers.ResetManager;

/**
 * BEHOLD, DOG!
 * <h1>Lone Wolf</h1>
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Matt
 *
 */
public class LoneWolf extends Enemy implements Resettable {


    /**
     * Constructor to initialise the capabilities and add the FACTION and the region and ground it spawns from
     * */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set faction
        this.setFaction(Faction.CANINE);
        //Set death runes amount
        this.setDeathRunesAmount(55,1470);
        //Set attack form
        this.setAttackForm(new SpecialAttackForm(0));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }


    /**
     * Retrieve the intrinsic weapon of the current class
     * */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }


}
