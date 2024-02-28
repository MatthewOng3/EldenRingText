package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.SiteOfLostGrace;
import game.util.FancyMessage;

/**
 * <h1>Activate Site Action</h1>
 * An action to represent the activating of a site
 * @author Kenny
 * Modified by: Matt
 */
public class ActivateSiteAction extends Action {
    /**
     * Attribute to store site of lost grace instance
     */
    private SiteOfLostGrace site;
    /**
     * Constructor
     */
    public ActivateSiteAction(SiteOfLostGrace site){
        this.site = site;
    }

    /**
     * When executed, call the method to activate site of lost grace
     * @param actor The actor performing the action
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. that the site has been activated
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Activate lost grace
        this.site.activateLostGrace();
        //Print site discovered message
        for (String line : FancyMessage.SITE_DISCOVERED.split("\n")) {
            new Display().println(line);
        }

        return actor + " activated " + this.site.getSiteName();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " touches " + this.site.getSiteName() + " to activate site of lost grace";
    }
}
