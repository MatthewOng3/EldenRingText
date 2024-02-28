package game.util;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class RandomNumberGenerator {
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }

    /**Method to calculate percentage chance, returns true if its <= chance , false otherwise
     * @param chance, the percentage chance that is generated
     * @return boolean value success
     * */
    public static boolean calculateChance(int chance){
        boolean success = false;
        int randomInt = getRandomInt(1, 100);
        if(randomInt <= chance){
            success = true;
        }
        return success;
    }
}
