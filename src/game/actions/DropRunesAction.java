package game.actions;



import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.DroppedRunes;
import game.managers.RunesManager;

/**
 * <h1>DropRunesAction</h1>
 * DropRunesAction class extends DropAction and allows for droppedRunes to be placed on the map.
 *
 * @author Kenny
 * @version 1.0
 * modified by: Matt
 */
public class DropRunesAction extends Action {
    /**
     * Attribute to store the location instance of the place to drop
     */
    private final Location locationToDrop;
    /**
     * Constructor.
     * @param locationToDrop
     */
    public DropRunesAction(Location locationToDrop) {
        this.locationToDrop = locationToDrop;
    }

    /**
     * Drop the runes, by removing the runes from actor inventory using rune manager and creating new dropped runes item
     *
     * @param actor The actor dropping the runes
     * @param map The map the actor is on
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Retrieve the runes amount of the actor that is dropping them
        int runes = RunesManager.getInstance().getRunesAmount(actor);
        //Remove the runes from the dropping actor's inventory
        RunesManager.getInstance().removeRunes(actor, runes);
        //Create new instance of dropped runes and add item to the location
        DroppedRunes droppedRunes = new DroppedRunes(this.locationToDrop, runes);
        locationToDrop.addItem(droppedRunes);
        return actor + "drops runes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops runes";
    }

}
