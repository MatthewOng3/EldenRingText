package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PickUpRunesAction;
import edu.monash.fit2099.engine.positions.Location;
import game.gameplay.Resettable;
import game.managers.ResetManager;
import game.managers.RunesManager;

/**
 * <h1>DroppedRunes</h1>
 * A DroppedRunes class that extends items stores runes and can be picked up
 * @author Kenny
 * modified by : Matt
 */
public class DroppedRunes extends Item implements Resettable {
    /**
     * Integer representing runes stored
     * */
    private int droppedRunesAmount;
    /**
     * Location instance of the location where the runes were dropped
     * */
    private Location droppedLocation;

    /**
     * Constructor
     * @param droppedLocation
     * @param droppedRunesAmount
     */
    public DroppedRunes(Location droppedLocation, int droppedRunesAmount) {
        super("Dropped Runes", '$', false);
        this.droppedRunesAmount = droppedRunesAmount;

        //Add pick up action to the dropped runes
        this.addAction(new PickUpRunesAction(this));
        //Store location of the dropped runes
        this.droppedLocation = droppedLocation;
        ResetManager.getInstance().registerResettableOnDeath(this);
    }

    /**
     * Getter for the amount of runes that this dropped runes store
     * @return droppedRunesAmount integer
     */
    public int getRunes(){return this.droppedRunesAmount;}

    /**
     * Reset method called in reset manager to remove item from the location
     */
    @Override
    public void reset(GameMap map) {
        droppedLocation.removeItem(this);
    }
}
