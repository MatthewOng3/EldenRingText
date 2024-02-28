package game.managers;

import game.grounds.SiteOfLostGrace;

import java.util.ArrayList;
import java.util.List;
/**
 * <h1>Fast Travel Manager</h1>
 * A class that keeps track of the site of lost graces that you can fast travel to
 * @author Matt
 */
public class FastTravelManager {
    /**
     * Static list to maintain lists of sites that can be fast traveled to
     * */
    private List<SiteOfLostGrace> restedSites = new ArrayList<>();

    /**
     * Static instance attribute
     * */
    private static FastTravelManager instance = null;

    /**
     * Returns itself and implements itself as singleton class
     *
     * @return instance an Instance of itself a ResetManager.
     */
    public static FastTravelManager getInstance(){
        if( instance == null) {
            instance = new FastTravelManager();
        }
        return instance;
    }

    /**
     * Method to add sites to the static list of sites that you can fast travel to
     * @param site instance of site of lost grace
     */
    public void addTravelSite(SiteOfLostGrace site){
        restedSites.add(site);
    }
    /**
     * Method to retrieve the entire rested site list
     * @return return restedSites attribute
     */
    public List<SiteOfLostGrace> getAllowedTravelList(){
        return restedSites;
    }
}
