package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.attackforms.AttackForm;
import game.behaviours.Behaviour;
import game.util.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>NPC</h1>
 * NPC parent class that represents the generally non playable characters in the game that can take their turns using behaviours
 * @author Matt
 */
public class NPC extends Actor {
    /**
     * Faction enum to identify each subclass FACTION
     * */
    private Faction faction;
    /**
     * Attribute to store the instance of attack form
     * */
    private AttackForm attackForm;
    /**
     * Hash table to store possible behaviours Enemy can have, the lower the integer the key the higher the priority
     * */
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    public NPC(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
    }

    /**
     * At each turn, select a valid action to perform based on behaviours
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(this.hasCapability(Status.POISON)){
            this.hurt(10);
        }

        //For each behaviour in the behaviour hashmap, get its associated action
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }


    /**
     * Setter to set the FACTION value of the subclass
     * @param faction, FACTION enum
     * @return
     */
    public void setFaction(Faction faction){
        this.addCapability(faction);
        this.faction = faction;
    }

    /**
     * Getter to retrieve the FACTION attribute
     * @return instance FACTION attribute
     */
    public Enum<Faction> getFaction(){return this.faction;}

    /**
     * Retrieve first weapon in weapon inventory
     * @return first weapon in the actor's weapon inventory
     */
    public WeaponItem getWeapon(){
        return this.getWeaponInventory().get(0);
    }

    /**
     * Method that can be override in subclasses to retrieve the attack strategy, which means either a basic attack or special attack in that turn
     *
     * @param target,   instance of the target the enemy will attack
     * @param direction
     */
    public Action getAttackForm(Actor target, String direction) {
        return this.attackForm.createAttackForm(this, target, direction);
    }

    /**
     * Method that sets the attack form type to be returned in get attack form
     * @param attackForm
     */
    public void setAttackForm(AttackForm attackForm){
        this.attackForm = attackForm;
    }

    /**
     * Method that can be overriden in enemies that have an intrinsic special attack and not from a weapon, if none then default returns null
     * @return Action class if enemy has a special attack
     * */
    public Action getSpecialAttack(){return null;}

    /**
     * Checks if the npc has at least one weapon or none
     * @return boolean true if there is a weapon, false otherwise
     */
    public boolean hasWeapon(){
        return !this.getWeaponInventory().isEmpty();
    }
}
