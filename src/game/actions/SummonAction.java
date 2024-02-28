package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Ally;
import game.archetypes.RandomArchetypeCreator;
import game.enemies.Invader;
import game.util.RandomNumberGenerator;

/**
 * <h1>Summon Action</h1>
 * Action that summons the actor that is passed in, to surrounding location grounds
 * @author Matt
 */
public class SummonAction extends Action {
    /**
     * Attribute to store the location of the Summon Sign that activated this summon action
     * */
    private Location summoningGround;
    /**
     * Attribute to store the actor being summoned
     * */
    private Actor summonedActor;
    /**
     * Constructor
     * */
    public SummonAction(Location summoningGround, Actor summonedActor){
        this.summoningGround = summoningGround;
        this.summonedActor = summonedActor;
    }

    /**
     * When executed, check surrounding area of ground and spawn actor accordingly
     * @param actor The actor performing the summon action
     * @param map The map the actor is on.
     * @return the result of the summon in string.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";

        //Check surrounding areas if spawning is possible
        for (Exit exit : this.summoningGround.getExits()) {
            Location destination = exit.getDestination();
            //If current exit does not already have an actor on it, spawn
            if (!destination.containsAnActor()){
                destination.addActor(this.summonedActor);
                result += this.summonedActor + " has entered your realm!";
                break;
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm. ";
    }
}
