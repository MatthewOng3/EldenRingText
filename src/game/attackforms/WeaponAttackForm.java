package game.attackforms;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actors.NPC;
import game.util.RandomNumberGenerator;
import game.util.Status;

/**
 * <h1>Weapon Attack Form</h1>
 * Class that represents if npc can perform special attack based on chance, or just input 0 chance if they can only do regular intrinsic weapon attacks
 * @author Matt
 */
public class WeaponAttackForm extends AttackForm {
    /**
     * Weapon that is being used in the attack
     * */
    private WeaponItem weapon;

    public WeaponAttackForm(int chance, WeaponItem weapon){
        super(chance);
        this.weapon = weapon;
    }

    /**
     * Method to calculates chance to perform the attacker's weapon's skill if there is , or regular attack with weapon
     * @param attacker NPC attacker
     * @param target target of the attack
     * @param direction direction of the attack
     */
    public Action createAttackForm(NPC attacker, Actor target, String direction) {

        if(RandomNumberGenerator.calculateChance(chance) && weapon.hasCapability(Status.WEAPON_SKILL) && !target.hasCapability(attacker.getFaction())){
            if(weapon.hasCapability(Status.TARGETED_SKILL)){
                return weapon.getSkill(target, direction);
            }
            return weapon.getSkill(attacker);
        }
        else if(!target.hasCapability(attacker.getFaction())){
            return new AttackAction(target, direction, weapon);
        }
        return null;
    }
}
