package game.grounds;

import game.enemies.Enemy;
import game.util.RandomNumberGenerator;
import game.factory.EnemyFactory;
import game.enemies.GiantDog;
import game.enemies.LoneWolf;
/**
 * <h1>Gust OF Wind</h1>
 * A class that represents the gust of wind environment that spawns Lone wolf and Giant dog
 * Created by: Matthew
 * @author Matthew
 * Modified by: Matthew
 */
public class GustOfWind extends Environment{

    /**Constructor to set the displayed char representation of gust of wind
     * */
    public GustOfWind(EnemyFactory factory) {
        super('&', factory);
    }

    /**
     * Spawn method that calls the create canine method that spawns one of the canines based on regions
     * @return new instance of enemy that spawns according to enemy factory
     * */
    @Override
    public Enemy spawn() {
        return this.factory.createCanine();
    }
}

