package game.archetypes;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.archetypes.Archetype;
import game.weapons.Club;
/**
 * <h1>Wretch Archetype</h1>
 * A class that represents the Wretch archetype
 * @author Kenny
 */
public class WretchArchetype implements Archetype {
    /**
     * Return hitpoint value of 455
     */
    @Override
    public int getStartingHitPoints() {
        return 414;
    }
    /**
     * Return a Uchigatana Weapon
     */
    @Override
    public WeaponItem getStartingWeapon() {
        return new Club();
    }
}