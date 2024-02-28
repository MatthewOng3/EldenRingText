package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.SiteOfLostGrace;
import game.managers.RunesManager;
/**
 * <h1>Upgrade Max Hp Action</h1>
 * Action to upgrade the max hp of tarnished and increase the price each time
 * @author Matt
 */
public class UpgradeMaxHpAction extends Action {

    /**
     * Upgrade the max hp of tarnished and increase the price each time
     *
     * @param actor The actor upgrading the hp
     * @param map The map the actor is on
     * @return A description of what happened when action is executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (RunesManager.getInstance().removeRunes(actor, SiteOfLostGrace.getHpUpgradePrice())) {
            actor.increaseMaxHp(48);
            SiteOfLostGrace.increaseHpUpgradePrice();

            return actor + " purchased hp upgrade for " + SiteOfLostGrace.getHpUpgradePrice() + " runes";
        }
        return actor + " does not have enough runes to upgrade hp";

    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades max hp  by 48 for " + SiteOfLostGrace.getHpUpgradePrice() + " runes";
    }
}
