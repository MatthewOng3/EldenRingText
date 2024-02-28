package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameplay.Resettable;
import game.util.Status;
import game.actions.ConsumeAction;
/**
 * <h1>FlaskOfCrimsonTears</h1>
 * FlaskOfCrimsonTears class that extends item and implements resettable,consumable
 * used to pass a consume action to heal and when reset, usages go to maximum
 * @author Kenny
 * */
public class FlaskOfCrimsonTears extends Item implements Resettable, Consumable {
    /**
     * Store name in string form in order to print to console
     * */
    private String name = "";
    /**
     * Store number of usages the flask has
     * */
    private int flask_usage = 2;
    /**
     * Constructor
     *
     * */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'F', false);
        this.addAction(new ConsumeAction(this));
        this.addCapability(Status.CONSUMABLE);
        this.addCapability(Status.NOT_SELLABLE);
        this.name = "Flask of Crimson Tears";
    }

    /**
     *Reset this flask usage to 2
     *
     * @param map instance of gameMap
     * */
    @Override
    public void reset(GameMap map) {
        this.flask_usage = 2;
    }

    /**
     * Consume the flask and heal, if no usages left, output empty
     * @param consumingActor, Actor doing the consuming
     * @return String text to describe the effect
     */
    public String consume(Actor consumingActor){
        //no uses left, nothing happens
        if (this.getFlask_usage() == 0){
            return "Nothing comes out from the flask, it is empty.";
        }
        //decrement usage count and heal 250 hp
        this.setFlask_usage(this.getFlask_usage()-1);
        consumingActor.heal(250);
        return "You drink from the flask and recover 250 HP";
    }

    /**
     *To string method to display usage
     *
     * */
    @Override
    public String toString(){return name + "( Usages : " +flask_usage + " )";}
    /**
     *Getter
     *
     * */
    public int getFlask_usage(){return flask_usage;}
    /**
     *Setter
     *
     * @param usage integer representing usage of flask
     * */
    public void setFlask_usage(int usage){this.flask_usage = usage ;}
}
