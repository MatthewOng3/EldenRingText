package game.grounds;

import game.enemies.Dog;
import game.enemies.Enemy;
import game.enemies.HeavySkeletalSwordsman;
import game.util.RandomNumberGenerator;
/**
 * <h1>Cage</h1>
 * A class that represents the cage that spawns dogs which are the pets of godrick soldiers
 * @author Matt
 */
public class Cage extends Environment {
    /**
     * Constructor
     * */
    public Cage(){
        super('<');
    }
    /**
     * Spawn method that returns the dog class
     * @return new instance of Dog
     * */
    @Override
    public Enemy spawn() {
        if(RandomNumberGenerator.calculateChance(37)){
            return new Dog();
        }
        return null;
    }
}
