package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.SpecialAttackForm;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;

/**
 * <h1>Dog</h1>
 * Dog class that spawns from cages in castle
 * @author Matt
 * */
public class Dog extends Enemy{

    public Dog() {
        super("Dog", 'a', 104);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set faction
        this.setFaction(Faction.CASTLE_ENEMY);
        //Set death runes amount
        this.setDeathRunesAmount(52,1390);
        //Set attack form
        this.setAttackForm(new SpecialAttackForm(0));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }


    /**
     * Retrieve the intrinsic weapon of the current class
     * @return Intrinsic weapon of the DOG
     * */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }
}
