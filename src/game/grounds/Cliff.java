package game.grounds;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.util.Status;

/**
 * <h1>Cliff</h1>
 * Cliff class that if player steps on it, instantly dies, npcs and other enemies are allowed to walk over it
 * @author Matt
 * @version 1.0
 * */
public class Cliff extends Ground {

    /**
     * Constructor
     * */
    public Cliff() {
        super('+');
    }

    /**
     * If current actor on it has the capability to be affected by the cliff, hurt actor by max value
     * */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor() && location.getActor().hasCapability(Status.AFFECTED_BY_CLIFF)){
            location.getActor().hurt(Integer.MAX_VALUE);
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.AFFECTED_BY_CLIFF);
    }
}
