package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Runes Manager</h1>
 * A class that manages the runes of actors.
 * Created by:
 * - Tsun Law
 * Modified by:
 */
public class RunesManager {
    private static RunesManager instance = new RunesManager();
    /**
     * Hash map storing actors as keys and integer values for the number of runes
     * */
    private static Map<Actor, Integer> actorRunesMap = new HashMap<>();

    private RunesManager(){}

    /**
     * Get the singleton instance of RunesManager
     * @return the singleton instance of RunesManager
     */
    public static RunesManager getInstance() {
        return instance;
    }

    /**
     * Transfer runes from one Actor to another, not being used yet but added for A3 in case if you can transfer
     * runes between actors
     * @param from the Actor to transfer runes from
     * @param to the Actor to transfer runes to
     * @param amount the amount of runes to transfer
     * @return true if the transfer is successful, false otherwise
     */
    public boolean transferRunes(Actor from, Actor to, int amount) {
        if (actorRunesMap.containsKey(from) && actorRunesMap.get(from) >= amount) {
            actorRunesMap.put(from, actorRunesMap.get(from) - amount);
            actorRunesMap.put(to, actorRunesMap.getOrDefault(to, 0) + amount);
            return true;
        }
        return false;
    }
    /**
     * Gain runes from enemy when enemy is killed by player
     * @param gainingActor , actor that is gaining the runes
     * @param enemy, the enemy instance that the gaining actor is gaining runes from
     */
    public void gainRunesFromEnemy(Actor gainingActor, Actor enemy){
        int currentAmount = actorRunesMap.get(gainingActor);
        int enemyRunes = actorRunesMap.get(enemy);
        actorRunesMap.put(gainingActor, currentAmount + enemyRunes);
        System.out.print(gainingActor + " gained " + enemyRunes + " runes from " + enemy + " \n");
        actorRunesMap.remove(enemy);
    }
    /**
     * Remove the actor from the hasmap
     * @param actor, actor to be removed
     */
    public void removeActorEntry(Actor actor){
        this.actorRunesMap.remove(actor);
    }

    /**
     * add runes an Actor with the given amount of runes
     * @param actor the Actor to credit
     * @param amount the amount of runes to credit
     */
    public void addRunes(Actor actor, int amount) {
        actorRunesMap.put(actor, actorRunesMap.getOrDefault(actor, 0) + amount);
    }

    /**
     * remove runes an Actor with the given amount of runes
     * @param actor the Actor to debit
     * @param amount the amount of runes to debit
     * @return true if the Actor has enough runes to be debited, false otherwise
     */
    public boolean removeRunes(Actor actor, int amount) {
        if (actorRunesMap.containsKey(actor) && actorRunesMap.get(actor) >= amount) {
            actorRunesMap.put(actor, actorRunesMap.get(actor) - amount);
            return true;
        }
        return false;
    }

    /**
     * Get the number of runes an Actor has
     * @param actor the Actor to get the number of runes from
     * @return the number of runes the Actor has
     */
    public int getRunesAmount(Actor actor) {
        return actorRunesMap.getOrDefault(actor, 0);
    }
}