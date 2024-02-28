package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.SiteOfLostGrace;
import game.managers.ResetManager;

/**
 * <h1>Reset Action</h1>
 * ResetAction class extends Action and calls ResetManager to reset all resettable.
 *
 * @author Kenny
 * @version 1.0
 */
public class ResetAction extends Action {
    /**
     * Attribute to store the site of lost grace instance
     */
    private SiteOfLostGrace resetSite;
    /**
     * Constructor.
     */
    public ResetAction(SiteOfLostGrace resetSite) {
        this.resetSite = resetSite;
    }

    /**
     * Set the new spawn point of player and call ResetManager to reset resettable.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened when action is executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Set site to rested and register most recent rest site
        this.resetSite.setRested();
        ResetManager.getInstance().registerSpawnSite(this.resetSite.getSiteLocation());
        //Reset specific resettables
        ResetManager.getInstance().siteReset(map);
        return "Map has been reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at the " + this.resetSite.getSiteName() + " Site of Lost Grace";
    }
}
