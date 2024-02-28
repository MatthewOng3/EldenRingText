package game.gameplay;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface that can be used to reset resettables in reset manager
 * Created by:
 * @author Matt
 *
 */
public interface Resettable {
    void reset(GameMap map);
}
