package game.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.AstrologerStaff;
import game.weapons.GreatKnife;

/**
 * <h1>AstrologerArchetype</h1>
 * A class that represents the astrologer archetype
 * @author Matt
 */
public class AstrologerArchetype implements Archetype{
    /**
     * Return hit point value of 396
     */
    @Override
    public int getStartingHitPoints() {
        return 396;
    }
    /**
     * Return an astrologer staff
     */
    @Override
    public WeaponItem getStartingWeapon() {
        return new AstrologerStaff();
    }
}
