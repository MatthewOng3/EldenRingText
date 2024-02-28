package game.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GreatKnife;

/**
 * <h1>BanditArchetype</h1>
 * A class that represents the bandit archetype
 * @author Kenny
 */
public class BanditArchetype implements Archetype {

    /**
     * Return hitpoint value of 414
     */
    @Override
    public int getStartingHitPoints() {
        return 414;
    }
    /**
     * Return a GreatKnife Weapon
     */
    @Override
    public WeaponItem getStartingWeapon() {
        return new GreatKnife();
    }
}