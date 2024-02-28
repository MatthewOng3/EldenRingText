package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Faction;
import game.util.RangedAttackGenerator;
import game.util.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Ranged Weapon</h1>
 * Class that represents ranged weapons in the game
 * @author Matt
 */
public abstract class RangedWeapon extends WeaponItem {

    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        this.addCapability(Status.RANGED);
    }

    /**
     * Tick override that adds attack actions to actor if it is the player using playable faction(explained in REQ 2 A3 rationale of why we used playable faction),
     * and uses ranged attack generator to return possible attack actions on actors within the range
     * @param currentLocation current location of actor
     * @param actor actor holding the weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(actor.hasCapability(Faction.PLAYABLE)){
            //Remove all previously added actions
            List<Action> toRemove = new ArrayList<>();
            for(Action action: this.getAllowableActions()) {
                toRemove.add(action);
            }

            for(Action removingaction: toRemove) {
                this.removeAction(removingaction);
            }

            //Add all attack actions to the weapon allowable actions
            List<Action> actions =  RangedAttackGenerator.getRangedAttacks(actor,2, currentLocation, this);
            for(Action attackAction: actions) {
                this.addAction(attackAction);
            }
        }
    }

    /**
     * Method that retrieves the range of weapons that extend ranged weapons
     * @return integer range value
     */
    public abstract int getRange();
}
