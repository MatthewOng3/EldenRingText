package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.WeaponAttackForm;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;
import game.util.Status;
import game.weapons.Scimitar;

/**
 * <h1>Skeletal Bandit</h1>
 *
 * @author Matt
 */
public class SkeletalBandit extends Enemy {
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184);
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        //Set capabilities and weapons
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(Status.REVIVABLE);
        //Set faction
        this.setFaction(Faction.SKELETON);
        //Set attack form
        this.setAttackForm(new WeaponAttackForm(50, this.getWeapon()));
        //Register resettable
        ResetManager.getInstance().registerResettable(this);
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

        //If Skeletal Swordsman has 0 hp, create pile of bones in place
        if(!this.isConscious()){
            Location actorLocation = map.locationOf(this);
            map.removeActor(this);
            //Reset hp before passing skeleton instace into pile of bones
            this.increaseMaxHp(0);
            PileOfBones bones = new PileOfBones(this, actorLocation);
            map.addActor(bones, actorLocation);
            ResetManager.getInstance().registerResettable(bones);
            return new DoNothingAction();
        }

        //For each behaviour in the behaviour hashmap, get its associated action
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(5, "bonks", 80);
    }



}
