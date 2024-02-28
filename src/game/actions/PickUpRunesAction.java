package game.actions;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.DroppedRunes;
import game.managers.RunesManager;

/**
 * <h1>PickUpRunesAction</h1>
 * PickUpRunesAction extends PickUpAction to allow actor to pick up runes.
 *
 * @author Kenny
 * @version 1.0
 */
public class PickUpRunesAction extends PickUpAction {

    private final DroppedRunes runes;
    /**
     * Constructor.
     *
     * @param  runes the DroppedRunes to pick up
     */
    public PickUpRunesAction(DroppedRunes runes) {
        super(runes);
        this.runes = runes;
    }

    /**
     * Add the runes to the Actor and remove the runes from the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     * @see PickUpAction
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        //do the rune
        int addAmount = runes.getRunes();
        RunesManager.getInstance().addRunes(actor,addAmount);
        // super.execute wil remove it from map
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the dropped runes : " + runes.getRunes();
    }
}
