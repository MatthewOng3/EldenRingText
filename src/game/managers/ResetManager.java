package game.managers;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameplay.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Reset Manager</h1>
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:Kenny
 *
 */
public class ResetManager {

    /**
     * List of  resettables that can be reset both on death and on player reset
     * */
    private List<Resettable> resettables;
    /**
     * List of other resettables that can ONLY be reset on death
     * */
    private List<Resettable> resettablesOnDeath;
    /**
     * Static instance attribute
     * */
    private static ResetManager instance = null;

    private Location spawnSite;
    /***
     * Constructor.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
        this.resettablesOnDeath = new ArrayList<>();
    }

    /***
     * Returns itself and implements itself as singleton class
     *
     * @return instance an Instance of itself a ResetManager.
     */
    public static ResetManager getInstance(){
        if( instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /***
     * Loops through both lists and calls the reset method.
     * @param map current GameMap instance
     */
    public void resetEverything(GameMap map) {
        for (Resettable resettable: resettables) {
            resettable.reset(map);
        }
        for (Resettable resettable: resettablesOnDeath) {
            resettable.reset(map);
        }
    }

    /***
     * Loops through only the regular resettable list to reset
     * @param map current GameMap instance
     */
    public void siteReset(GameMap map){
        for (Resettable resettable: resettables) {
            resettable.reset(map);
        }
    }

    /**
     * Add resettable into the general resettable list
     * @param resettable a resettable interface or implementation
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Add resettable that can only be reset on death into the resettableOnDeath List
     * @param resettable a resettable interface or implementation
     */
    public void registerResettableOnDeath(Resettable resettable) {
        resettablesOnDeath.add(resettable);
    }

    /**
     * Register the location of the most recent rested site of lost grace
     * @param location, location instance
     */
    public void registerSpawnSite(Location location){
        this.spawnSite = location;
    }
    /**
     * Getter for the most recent rested site of lost grace
     * @return location instance
     */
    public Location getSpawnSite(){
        return this.spawnSite;
    }
}
