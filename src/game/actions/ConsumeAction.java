package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * <h1>ConsumeAction </h1>
 * ConsumeAction class that executes the consume method of consumable interfaces
 *
 * @author Kenny
 * @version 1.0
 */

public class ConsumeAction extends Action {
    /**
     * Consumable item that is about to be consumed
     * */
    private Consumable consumable;
    /**
     * Constructor.
     *
     * @param consumable
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Executes the consume method of consumable
     *
     * @param actor The actor performing the consume action
     * @param map The map the actor is on.
     * @return the resulting string displaying on the console after the consume method
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = this.consumable.consume(actor);
        return result;
    }

    /**
     * Describes the consume action on the menu UI
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + this.consumable;
    }
}
