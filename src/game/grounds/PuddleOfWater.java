package game.grounds;

import game.enemies.Enemy;
import game.factory.EnemyFactory;
import game.util.RandomNumberGenerator;
import game.enemies.GiantCrab;
import game.enemies.GiantCrayfish;

/**
 * <h1>Puddle Of Water</h1>
 * A class that represents the puddle of water environment that spawns Giant Crab and Giant crayfish
 * Created by: Matthew
 * @author Matthew
 * Modified by: Matthew
 */
public class PuddleOfWater extends Environment {
    /**Constructor to set the displayed char representation of Puddle of water
     * */
    public PuddleOfWater(EnemyFactory factory) {
        super('~', factory);
    }
    /**
     * Spawn method that calls the create shellfish method of factory and returns the enemy defined based on region
     * @return new instance of enemy that spawns according to enemy factory
     * */
    @Override
    public Enemy spawn() {
        return this.factory.createShellfish();
    }
}

