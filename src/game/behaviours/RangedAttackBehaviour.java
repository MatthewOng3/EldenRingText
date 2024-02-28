package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NPC;
import game.util.RandomNumberGenerator;
import game.util.RangedAttackGenerator;
import game.weapons.RangedWeapon;

import java.util.List;

/**
 * <h1>Ranged Attack Behaviour</h1>
 * Ranged attack behaviour class that uses Ranged Attack Generator getRange method, and allows enemies that have ranged weapons to attack in a range
 * @author Matt
 */
public class RangedAttackBehaviour implements Behaviour{
    /**
     * Ranged weapon
     * */
    private RangedWeapon rangedWeapon;
    public RangedAttackBehaviour(RangedWeapon rangedWeapon){
        this.rangedWeapon = rangedWeapon;
    }

    /**
     * Returns an attack action on actors in a surrounding range.
     * If no attack is possible, returns null.
     *
     * @param NPC the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(NPC NPC, GameMap map) {
        List<Action> actions =  RangedAttackGenerator.getRangedAttacks(NPC, rangedWeapon.getRange(), map.locationOf(NPC), rangedWeapon);

        //If action list is not empty return a random exit
        if (!actions.isEmpty()) {
            return actions.get(RandomNumberGenerator.getRandomInt(actions.size()));
        }

        return null;
    }
}
