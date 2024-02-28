package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Faction;
import game.managers.ResetManager;
import game.managers.RunesManager;

/**
 * <h1>Pile of bones</h1>
 * Pile of bones class is what the Skeleton FACTION turns into
 *
 * Created by:
 * @author Matt
 * Modified by:
 *
 */
public class PileOfBones extends Enemy {
    /**
     * Counter attribute to keep track of game turns
     * */
    private int counter;
    /**
     * Store the instance of Skeleton FACTION that turns into a pile of bones as an attribute
     * */
    private Enemy skeleton;
    /**
     * The location on the map of the death point of the SKELETON
     * */
    private Location deathLocation;

    public PileOfBones(Enemy skeleton, Location deathLocation){
        super("Pile of Bones", 'X', 1);
        this.counter = 1;
        this.skeleton = skeleton;
        this.deathLocation = deathLocation;

        //Set faction
        this.setFaction(Faction.SKELETON);
        this.addWeaponToInventory(this.skeleton.getWeapon()); //Add skeleton's weapon to this pile of bones class so it can be dropped on death
        System.out.println(this.skeleton + " turned into " + this);
        //Set death runes amount
        this.setDeathRunesAmount(35,892);
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * After 3 turns if pile of bones is not destroyed revive skeleton
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        counter += 1;
        //If 3 turns have passed, revive heavy skeletal swordsman by creating a new instance of the class on the location
        if(counter % 4 == 0){
            //Reset hp of skeleton instance
            this.skeleton.increaseMaxHp(0);
            //Remove pile of bones entry from runes hashmap
            RunesManager.getInstance().removeActorEntry(this);
            //Remove pile of boens from map and
            map.removeActor(this);
            this.deathLocation.addActor(this.skeleton);
            //Print revive to console
            display.print(this.skeleton + " has been revived from " + this + "\n");
        }
        return new DoNothingAction();
    }

    @Override
    public Action getAttackForm(Actor target, String direction) {
        return new DoNothingAction();
    }


}
