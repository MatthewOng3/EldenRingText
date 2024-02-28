package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.SpecialAttackForm;
import game.actions.AreaAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;

/**
 * <h1>Giant Crayfish</h1>
 * @author Matt
 *
 */
public class GiantCrayfish extends Enemy {

    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set faction
        this.setFaction(Faction.SHELLFISH);
        //Set death runes amount
        this.setDeathRunesAmount(500,2374);
        //Set attack form
        this.setAttackForm(new SpecialAttackForm( 50));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Giant Crayfish's special attack is an area attack slam with its intrinsic weapon
     * @return area attack action class
     * */
    @Override
    public Action getSpecialAttack(){
        return new AreaAttackAction();
    }


    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slam", 100);
    }



}
