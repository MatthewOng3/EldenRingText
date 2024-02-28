package game.grounds;

import game.enemies.Enemy;
import game.enemies.PoisonBandit;;
import game.util.RandomNumberGenerator;
/**
 * <h1>ToxicTown </h1>
 * ToxicTown class that extends environment
 * used to spawn a PoisonBandit 
 *
 * @author Kenny
 * */
public class ToxicTown extends Environment{
    /**
     * Constructor
     * */
    public ToxicTown(){
        super(']');
    }
    /**
     * Spawn method that returns the poison bandit class
     * @return new instance of Poison bandit
     * */
    @Override
    public Enemy spawn() {
        if(RandomNumberGenerator.calculateChance(37)){
            return new PoisonBandit();
        }
        return null;
    }
}
