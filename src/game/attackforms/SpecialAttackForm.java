package game.attackforms;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AttackAction;
import game.actors.NPC;
import game.attackforms.AttackForm;
import game.util.RandomNumberGenerator;

/**
 * <h1>Special Attack Form</h1>
 * Class that represents if npc can perform special attack based on chance, or just input 0 chance if they can only do regular intrinsic weapon attacks
 * @author Matt
 */
public class SpecialAttackForm extends AttackForm {

    public SpecialAttackForm(int chance){
        super(chance);
    }
    /**
     * Method to calculates chance to perform the attacker's special attack or just do a regular intrinsic weapon attack
     * @param attacker NPC attacker
     * @param target target of the attack
     * @param direction direction of the attack
     */
    public Action createAttackForm(NPC attacker, Actor target, String direction){
        if(RandomNumberGenerator.calculateChance(chance) && !target.hasCapability(attacker.getFaction())){
            return attacker.getSpecialAttack();
        }
        else if(!target.hasCapability(attacker.getFaction())){
            return new AttackAction(target, direction);
        }
        return null;
    }

}
