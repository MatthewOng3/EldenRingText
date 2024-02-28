package game.attackforms;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actors.NPC;
/**
 * <h1>Attack Form</h1>
 * Abstract attack form class that subclasses can extend and implement their own attack form logic
 * @author Matt
 */
public abstract class AttackForm {
    /**
     * Integer chance attribute to execute certain actions
     */
    protected int chance;
    /**
     *Constructor
     */
    public AttackForm(int chance){
        this.chance = chance;
    }
    /**
     * Method to implement in subclasses that defines what sort of attack to return
     * @param attacker NPC attacker
     * @param target target of the attack
     * @param direction direction of the attack
     */
    public abstract Action createAttackForm(NPC attacker, Actor target, String direction);
}
