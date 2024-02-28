package game.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.archetypes.Archetype;
import game.weapons.Uchigatana;
/**
 * <h1>Samurai Archetype</h1>
 * A class that represents the Samurai archetype
 * @author Kenny
 */
public class SamuraiArchetype implements Archetype {
    /**
     * Return hitpoint value of 455
     */
    @Override
    public int getStartingHitPoints() {
        return 455;
    }
    /**
     * Return a Uchigatana Weapon
     */
    @Override
    public WeaponItem getStartingWeapon() {
        return new Uchigatana();
    }
}