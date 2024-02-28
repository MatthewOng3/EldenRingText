package game.factory;

import game.enemies.Enemy;
import game.grounds.Environment;

/**
 * <h1>Enemy Factory</h1>
 * Abtract enemy factory
 * @author Matt
 */
public abstract class EnemyFactory {
    /**
     * Method to be defined in East and West Enemy factory which skeleton instance to spawn
     * @return Skeleton instance relevant to the region
     * */
    public abstract Enemy createSkeleton();
    /**
     * Method to be defined in East and West Enemy factory which crab instance to spawn
     * @return Shellfish instance relevant to the region
     * */
    public abstract Enemy createShellfish();
    /**
     * Method to be defined in East and West Enemy factory which canine instance to spawn
     * @return Canine instance relevant to the region
     * */
    public abstract Enemy createCanine();
}
