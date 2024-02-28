package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemy;
import game.factory.EnemyFactory;
/**
 * <h1>Environment</h1>
 * Parent class of the special grounds that spawn enemies
 * @author Matt
 *
 */
public abstract class Environment extends Ground {
    /**
     * Factory attribute
     * */
    protected EnemyFactory factory;

    /**
     * Constructor with factory instance
     * @param displayChar, display char of environment
     * @param factory, EnemyFactory instance
     * */
    public Environment(char displayChar, EnemyFactory factory){
        super(displayChar);
        this.factory = factory;
    }

    /**
     * Constructor without factory instance
     * @param displayChar, display char of environment
     * */
    public Environment(char displayChar){
        super(displayChar);
    }

    /**
     * Method that runs each game turn, having a chance to spawn the relevant enemies and checks if there are spaces in the surrounding area to spawn them
     * @param location, location of environment on game map
     * */
    @Override
    public void tick(Location location) {
        Enemy enemy = spawn();
        if(enemy != null){
            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                //If current exit does not already have an actor on it, spawn
                if (destination.canActorEnter(enemy)){
                    destination.addActor(enemy);
                    break;
                }
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Spawn method that either defines the enemy to be spawned by the environment or calls the enemy factory create method that creates the correct
     * instance of the enemy that is meant to be spawned based on region.
     * @return new instance of enemy that spawns according to enemy factory
     * */
    public abstract Enemy spawn();
}
