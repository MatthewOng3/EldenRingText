package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.NPC;
import game.util.Status;

import java.util.ArrayList;

/**
 * <h1>Attack behaviour</h1>
 * Attack behaviour decides what attack action to return based on certain conditions, if no attack possible return null
 *
 * @author Matt
 * @version 1.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Attribute of the enemy that is attacking
     */
    private NPC attackingNPC;

    /**
     * Constructor that takes in an instance of Enemy interface in order to utilise the getAttackStrategy method
     * @param NPC, Instance of classes implementing the Enemy interface
     */
    public AttackBehaviour(NPC NPC){
        this.attackingNPC = NPC;
    }

    /**
     * Returns the possible attacks on actors if nearby
     * If no attack is possible, returns null.
     *
     * @param npc the npc enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    public Action getAction(NPC npc, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        //Retrieves the surrounding tiles of the actor
        for (Exit exit : map.locationOf(npc).getExits()) {
            Location destination = exit.getDestination();
            //Get target actor instance
            Actor target = destination.getActor();
            //If there is an actor , retrieve the attack strategy of the current Enemy
            if(destination.containsAnActor() && !destination.getActor().hasCapability(Status.NOT_ATTACKABLE)){
                actions.add(this.attackingNPC.getAttackForm(target, exit.getName()));
            }
        }

        //Return first non-null action, else return null
        for(Action action : actions){
            if(action != null){
                return action;
            }
        }
        return null;
    }
}
