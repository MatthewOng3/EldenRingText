package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateSiteAction;
import game.actions.UpgradeMaxHpAction;
import game.actors.Faction;
import game.managers.FastTravelManager;
import game.managers.ResetManager;
import game.util.Status;
import game.actions.ResetAction;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>SiteOfLostGrace</h1>
 * A class that represents the ground where the player can rest to reset enemies and health
 * @author Kenny
 * Modified by: Matt
 */
public class SiteOfLostGrace extends Ground {
    /**
     * Attribute to store the name of the site of lost grace
     * */
    private String name;
    /**
     * Attribute to store the location of the site instance
     * */
    private Location siteLocation;
    /**
     * Boolean value to indicate if site is rested or not
     * */
    private boolean rested = false;

    /**
     * Static integer variable to store the price of upgrading hp
     * */
    private static int upgradeHitpointPrice = 200;
    /**
     * Constructor
     */
    public SiteOfLostGrace(String name, Location siteLocation) {
        super('U');
        this.name = name;
        this.siteLocation = siteLocation;
    }

    /**
     * Pass a reset action for the actor if site is activated, else pass a activate site action
     * @param actor     actor interacting with ground
     * @param location  location of ground
     * @param direction direction of ground from actor
     * @see Ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        if(this.hasCapability(Status.CAN_REST)){
            //Allow actor to reset
            actions.add(new ResetAction(this));
            //Add move action to all rested sites
            if(this.rested){
                for(SiteOfLostGrace site: FastTravelManager.getInstance().getAllowedTravelList()){
                    //Using engine equals method to check if the locations are different
                    if(!this.getSiteLocation().equals(site.getSiteLocation())){
                        actions.add(new MoveActorAction(site.getSiteLocation(),"to " + site.getSiteName()));
                    }
                }
                actions.add(new UpgradeMaxHpAction());
            }
        }else{
            actions.add(new ActivateSiteAction(this));
        }
        return actions;
    }

    /**
     * Method to set the rested boolean attribute to true indicating site has been rested on so allow fast travel
     */
    public void setRested(){
        this.rested = true;
    }

    /**
     * Method to return location of the site instance
     * @return location class instance
     */
    public Location getSiteLocation(){
        return this.siteLocation;
    }

    /**
     * Method to add capability so tarnished can rest and add this to fast travel sites list
     */
    public void activateLostGrace(){
        this.addCapability(Status.CAN_REST);
        FastTravelManager.getInstance().addTravelSite(this);
    }

    /**
     * Getter to retrieve the name of the site
     * @return String name of site
     */
    public String getSiteName(){
        return this.name;
    }

    /**
     * Static method to increase the upgrade price of the hp upgrade
     */
    public static void increaseHpUpgradePrice(){
        upgradeHitpointPrice += 100;
    }

    /**
     * Static method to return the upgrade hit points price
     * @return static attribute upgradeHitpointPrice
     */
    public static int getHpUpgradePrice(){
        return upgradeHitpointPrice;
    }

    /**
     * Allow an actor that is hostile to enemies pass through the site
     * @param actor     actor interacting with ground
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Faction.PLAYABLE);
    }

}

