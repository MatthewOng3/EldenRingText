package game.grounds;

import game.enemies.Enemy;
import game.factory.EnemyFactory;
import game.util.RandomNumberGenerator;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.SkeletalBandit;

/**
 * <h1>Graveyard</h1>
 * A class that represents the graveyard environment that spawns HumanSkeletalBandit and Skeletal Bandit
 * Created by: Matthew
 * @author Matthew
 * Modified by: Matthew
 */
public class Graveyard extends Environment {
    /**
     * Constructor to set the displayed char representation of graveyard
     * */
    public Graveyard(EnemyFactory factory) {
        super('n', factory);
    }

    /**
     * Spawn method that calls the create skeleton method of the factory and spawns one of the skeletons
     * @return new instance of enemy that spawns according to enemy factory
     * */
    @Override
    public Enemy spawn() {
        return this.factory.createSkeleton();
    }
}
