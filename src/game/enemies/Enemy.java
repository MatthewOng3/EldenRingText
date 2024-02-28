package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DeathAction;
import game.actors.NPC;
import game.managers.ResetManager;
import game.managers.RunesManager;
import game.util.RandomNumberGenerator;
import game.gameplay.Resettable;
import game.util.Status;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;


/**
 * <h1>Enemy</h1>
 * Enemy class that further extends NPC that represents enemies in the game
 *
 * @author Matt
 * @version 1.0
 */
public class Enemy extends NPC implements Resettable {

    /**
     * Boolean attribute that is true if the current npc instance is following the player
     * */
    private boolean following = false;

    /**
     * The random amount of runes dropped upon enemy's death
     * */
    private int deathRunesAmount;

    /**
     * Constructor for enemy classes
     * @param displayChar, char representation of enemy character
     * @param name, name of enemy
     * @param hitPoints,
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CAN_GIVE_RUNES);
    }

    /**
     * This method returns the actions that can be performed on the  subclasses of Enemy class. Also checks if otherActor can be followed
     *
     * @param otherActor the Actor that might be performing attack, usually player
     * @param direction  String representing the direction of the other Actor, set in the location exits and passed into processActorTurn in world game loop
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //If the other actor in proximity have followable status and is not already following add follow behaviour
        if(otherActor.hasCapability(Status.FOLLOWABLE) && !this.following){
            this.behaviours.put(400, new FollowBehaviour(otherActor));
            this.following = true;
        }

        //If actor has the capability of choosing weapons add attack action foreach weapon
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //Loop through the otherActor's weapon inventory, create a new attack action for each one
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                actions.add( new AttackAction(this, direction, weapon));
                //If weapon has a skill then check if its targeted skill or not and use the relevant get skill methods
                if(weapon.hasCapability(Status.WEAPON_SKILL)){
                    if(weapon.hasCapability(Status.TARGETED_SKILL)){
                        actions.add(weapon.getSkill(this, direction));
                    }
                    else{
                        actions.add(weapon.getSkill(otherActor));
                    }
                }
            }
            //Let other actor attack with intrinsic weapon
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    /**
     * Setter to randomly set the death runes amount based on lower and upper bound values
     * @param lowerBound, lower bound value possible for death runes
     * @param upperBound, upper bound value possible for death runes
     */
    public void setDeathRunesAmount(int lowerBound, int upperBound){
        this.deathRunesAmount = RandomNumberGenerator.getRandomInt(lowerBound, upperBound);
        RunesManager.getInstance().addRunes(this,this.deathRunesAmount);
    }

    /**
     * Getter to retrieve the amount of runes enemies drop upon death
     * @return deathRunesAmount attribute
     */
    public int getDeathRunesAmount(){
        return this.deathRunesAmount;
    }

    @Override
    public void reset(GameMap map) {
        map.removeActor(this);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.POISON)){
            this.hurt(10);
        }


        if(!this.isConscious()){
            return new DeathAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
