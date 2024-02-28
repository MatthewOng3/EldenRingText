package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.attackforms.WeaponAttackForm;

import game.archetypes.Archetype;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

import game.gameplay.Resettable;
import game.managers.ResetManager;
import game.util.Status;

/**
 * <h1>Ally</h1>
 * NPC Class representing the allies spawned by using the summon sign
 * @author Matt
 * @version 1.0
 * */
public class Ally extends NPC implements Resettable {
    /**
     * Constructor.
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param archetype   The archetype class that is randomly given
     */
    public Ally(String name, char displayChar, Archetype archetype) {
        super(name, displayChar, archetype.getStartingHitPoints());
        //Add behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour(true));
        //Add relevant capabilities
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FOLLOWABLE);
        //Set faction
        this.setFaction(Faction.ALLIES);
        //Add starting weapon of archetype to inventory
        this.addWeaponToInventory(archetype.getStartingWeapon());
        //Set attack form
        this.setAttackForm(new WeaponAttackForm(50, this.getWeapon()));

        ResetManager.getInstance().registerResettableOnDeath(this);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(11, "punch", 90);
    }

    @Override
    public void reset(GameMap map) {
        map.removeActor(this);
    }
}
