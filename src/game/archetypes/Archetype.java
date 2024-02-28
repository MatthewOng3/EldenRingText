package game.archetypes;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * <h1>Archetype</h1>
 * A class that represents an archetype
 * @author Kenny
 */
public interface Archetype {
    int getStartingHitPoints();
    WeaponItem getStartingWeapon();
}
