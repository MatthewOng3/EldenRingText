package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.util.Status;
/**
 * <h1>GoldenFogDoor</h1>
 * GoldenFogDoor class that extends ground
 * used to pass a move actor action to the player that when executed will move them to the new location
 *
 * @author Kenny
 * */

public class GoldenFogDoor extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    private String destination;
    private Location moveTo ;

    public GoldenFogDoor(GameMap map , int x , int y,String destination) {
        super('D');
        this.moveTo = map.at(x,y);
        this.destination = destination;
    }
    /**
     * Allows tarnished if nearby to move to different maps
     * */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        actions.add(new MoveActorAction(moveTo,"to " + destination));
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
