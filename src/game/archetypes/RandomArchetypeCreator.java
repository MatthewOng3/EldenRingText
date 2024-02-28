package game.archetypes;

import game.util.RandomNumberGenerator;
/**
 * <h1>Random Archetype Creator</h1>
 * Randomly create any of the archetypes available in the game
 * @author  Matt
 *
4 */
public class RandomArchetypeCreator{

    public static RandomArchetypeCreator instance = null;

    /**
     * Public Static method to retrieve an instance of ArchetypeCreation if there are no other instances yet.
     * @return static instance attribute, either the current instance or the newly created instance using the private constructor
     * */
    public static RandomArchetypeCreator getInstance() {
        if (instance == null) {
            instance = new RandomArchetypeCreator();
        }
        return instance;
    }

    /**
     * Private constructor
     * */
    private RandomArchetypeCreator() {}
    /**
     * Public method to create a random archetype
     * @return Archetype instance that was created
     * */
    public Archetype createArchetype() {
        int randomChoice = RandomNumberGenerator.getRandomInt(1,4);

        switch (randomChoice) {
            case 1:
                return new SamuraiArchetype();
            case 2:
                return new BanditArchetype();
            case 3:
                return new WretchArchetype();
            case 4:
                return new AstrologerArchetype();
            default:
                throw new IllegalStateException("Unexpected random value: " + randomChoice);
        }
    }
}
