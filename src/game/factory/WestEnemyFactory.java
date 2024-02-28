package game.factory;

import game.enemies.*;
import game.util.RandomNumberGenerator;

/**
 * <h1>West Enemy Factory</h1>
 * West enemy factory in charge of getting the spawns in the west region
 * @author Matt
 *
4 */
public class WestEnemyFactory extends EnemyFactory {


    @Override
    public Enemy createSkeleton() {
        if(RandomNumberGenerator.calculateChance(27)){
            return new HeavySkeletalSwordsman();
        }
        return null;
    }

    @Override
    public Enemy createShellfish() {
        if(RandomNumberGenerator.calculateChance(2)){
            return new GiantCrab();
        }
        return null;
    }

    @Override
    public Enemy createCanine() {
        if(RandomNumberGenerator.calculateChance(33)){
            return new LoneWolf();
        }
        return null;
    }
}
