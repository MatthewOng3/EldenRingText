package game.factory;

import game.enemies.Enemy;
import game.enemies.GiantCrayfish;
import game.enemies.GiantDog;
import game.enemies.SkeletalBandit;
import game.util.RandomNumberGenerator;

/**
 * <h1>East Enemy Factory</h1>
 * East enemy factory in charge of getting the spawns in the east region
 * @author Matt
 *
4 */
public class EastEnemyFactory extends EnemyFactory {


    @Override
    public Enemy createSkeleton() {
        if(RandomNumberGenerator.calculateChance(27)){
            return new SkeletalBandit();
        }
        return null;
    }

    @Override
    public Enemy createShellfish() {
        if(RandomNumberGenerator.calculateChance(1)){
            return new GiantCrayfish();
        }
        return null;
    }

    @Override
    public Enemy createCanine() {
        if(RandomNumberGenerator.calculateChance(4)){
            return new GiantDog();
        }
        return null;
    }
}
