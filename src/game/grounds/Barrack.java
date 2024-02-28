package game.grounds;


import game.enemies.Dog;
import game.enemies.Enemy;
import game.enemies.GodrickSoldier;
import game.util.RandomNumberGenerator;

/**
 * <h1>Barrack</h1>
 * A class that represents the barracks environment that spawns godrick soldiers
 * @author Matt
 */
public class Barrack extends Environment{

    /**
     * Constructor
     * */
    public Barrack(){
        super('B');
    }
    /**
     * Spawn method that returns new instance of enemy that this environment spawns
     * @return Godrick Soldier instance
     * */
    @Override
    public Enemy spawn() {
        if(RandomNumberGenerator.calculateChance(45)){
            return new GodrickSoldier();
        }
        return null;
    }
}
