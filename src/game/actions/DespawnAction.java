package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * <h1>Despawn Action</h1>
 * An Action that despawns an actor
 * @author Matt
 * @version 1.0
 *
 */
public class DespawnAction extends Action {
    public DespawnAction(){}

    /**
     * Remove the actor from the map when execute is called
     * @param actor, actor to be removed
     * @param map, the map the actor is on
     * */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        String result="";
        result += menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is removed from the map";
    }
}
