package game.util;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actors.Faction;

import java.util.ArrayList;
import java.util.List;
/**
 * <h1>Ranged Attack Generator</h1>
 * Ranged attack generator class that allows ranged weapons to call this static method to allow the ranged weapons to define their range
 * and return possible attack actions on actors in the surrounding range area
 * @author Matt
 * */
public class RangedAttackGenerator {

    /**
     * Static method to calculate the range of the weapon being passed in, and return attack actions where possible
     * @param holdingActor, actor holding the ranged weapon
     * @param range, range in integer value of weapon
     * @param startLocation, location instance of the holder of the weapon
     * @param rangedWeapon, instance of the ranged weapon
     * */
    public static List<Action> getRangedAttacks(Actor holdingActor, int range, Location startLocation, WeaponItem rangedWeapon){
        List<Action> actions = new ArrayList<>();

        //Set starting x and y coordinates
        int startX = startLocation.x();
        int startY = startLocation.y();

        GameMap currentMap = startLocation.map();

        //Get holding actor faction
        Faction holdingActorFaction = holdingActor.findCapabilitiesByType(Faction.class).get(0);

        //Loop through surrounding tiles
        for (int x = Math.max(0, startX - range); x <= Math.min(currentMap.getXRange().max(), startX + range); x++) {
            for (int y = Math.max(0, startY - range); y <= Math.min(currentMap.getYRange().max(), startY + range); y++) {
                //Retrieve current location instance
                Location currentLocation = currentMap.at(x, y);
                String coordinates = "(" + x + "," + y + ")";
                //If the current location in the range check if the actor is not of the same faction and there is an actor then add attack action
                if (currentLocation.containsAnActor() && !startLocation.equals(currentLocation) && !currentLocation.getActor().hasCapability(holdingActorFaction)) {
                    //Add attack action if there is an actor
                    actions.add(new AttackAction(currentLocation.getActor(), coordinates, rangedWeapon));
                }
            }
        }

        return actions;
    }
}
