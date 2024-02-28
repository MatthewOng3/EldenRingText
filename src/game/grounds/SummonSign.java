package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.actors.Ally;
import game.archetypes.RandomArchetypeCreator;
import game.enemies.Invader;
import game.util.RandomNumberGenerator;
import game.util.Status;

/**
 * <h1>SummonSign</h1>
 * Special ground that allows player to summon either an ally or an invader, 50% change for either
 * @author Matt
 */
public class SummonSign extends Ground {
    /**
     * Constructor initialising the display char
     * */
    public SummonSign(){
        super('=');
    }

    /**
     * Returns a list of actions that the actor can perform or execute on this ground
     * In this case 50% chance to spawn ally or invader and pass that into summon action to spawn the actor
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return action list of possible actions the actor can perform on this ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        //If actor has summon capability then create ally or invader and pass into summon action
        if(actor.hasCapability(Status.CAN_SUMMON)){
            Actor summonedActor=null;
            if(RandomNumberGenerator.calculateChance(50)){
                summonedActor = new Ally("Ally", 'A', RandomArchetypeCreator.getInstance().createArchetype());
            }
            else{
                summonedActor = new Invader("Invader", 'ඞ', RandomArchetypeCreator.getInstance().createArchetype());
            }
            actions.add(new SummonAction(location, summonedActor));
        }
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
