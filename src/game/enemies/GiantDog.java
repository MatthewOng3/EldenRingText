package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.SpecialAttackForm;
import game.actions.AreaAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameplay.Resettable;
import game.managers.ResetManager;

/**
 * <h1>Giant Dog</h1>
 * Dog but bigger
 * @author Matt
 * @version 1.0
 */
public class GiantDog extends Enemy implements  Resettable {

    public GiantDog() {
        super("Giant Dog", 'G', 693);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set faction
        this.setFaction(Faction.CANINE);
        //Set attack form
        this.setAttackForm(new SpecialAttackForm( 50));
        //Set death runes amount
        this.setDeathRunesAmount(313,1808);
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Giant Dog's special attack is an area attack slam with its intrinsic weapon
     * @return area attack action class
     * */
    public Action getSpecialAttack(){
        return new AreaAttackAction();
    }


    /**Retrieve the intrinsic weapon of the current class
     * */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }


}
