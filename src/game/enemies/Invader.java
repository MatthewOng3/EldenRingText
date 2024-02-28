package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Faction;
import game.attackforms.WeaponAttackForm;
import game.archetypes.Archetype;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;
import game.util.Status;
/**
 * <h1>Invader/h1>
 * Enemy NPC Class representing the invaders spawned by using the summon sign
 * @author Matt
 * @version 1.0
 * */
public class Invader extends Enemy{
    /**
     * Constructor.
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param archetype   The archetype class that is randomly given
     */
    public Invader(String name, char displayChar, Archetype archetype){
        super(name, displayChar, archetype.getStartingHitPoints());
        //Set behaviours
        this.behaviours.put(1, new AttackBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour(true));
        //Set faction
        this.setFaction(Faction.INVADER);
        //Set death runes
        this.addCapability(Status.CAN_GIVE_RUNES);
        this.setDeathRunesAmount(1358, 5578);
        //Set starting weapon
        this.addWeaponToInventory(archetype.getStartingWeapon());
        //Set attack form
        this.setAttackForm(new WeaponAttackForm(50, this.getWeapon()));

        //Register resettable
        ResetManager.getInstance().registerResettableOnDeath(this);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(11, "punch", 90);
    }
}
