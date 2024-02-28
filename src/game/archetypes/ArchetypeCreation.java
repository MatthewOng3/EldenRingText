package game.archetypes;

import game.archetypes.Archetype;
import game.archetypes.BanditArchetype;
import game.archetypes.SamuraiArchetype;
import game.archetypes.WretchArchetype;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * <h1>ArcheType Creation</h1>
 * Ask for user input in creating arche type
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Everyone
 *
4 */
public class ArchetypeCreation{

    public static ArchetypeCreation instance = null;

    /**
     * Public Static method to retrieve an instance of ArchetypeCreation if there are no other instances yet.
     * @return static instance attribute, either the current instance or the newly created instance using the private constructor
     * */
    public static ArchetypeCreation getInstance() {
        if (instance == null) {
            instance = new ArchetypeCreation();
        }
        return instance;
    }

    /**
     * Private constructor
     * */
    private ArchetypeCreation() {}

    /**
     * Public method to create arche type by receiving input from the console
     * @return Archetype instance that was created
     * */
    public Archetype createArchetype(){
        //Choose Class
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        int choice;
        Archetype chosenArcheType = null;
        do {
            System.out.println("Please choose a class:");
            System.out.println("1. Samurai");
            System.out.println("2. Bandit");
            System.out.println("3. Wretch");
            System.out.println("4. Astrologer");
            try {
                choice = scanner.nextInt();  // Read user input

                switch (choice) {
                    case 1:
                        chosenArcheType = new SamuraiArchetype();
                        break;
                    case 2:
                        chosenArcheType = new BanditArchetype();
                        break;
                    case 3:
                        chosenArcheType = new WretchArchetype();
                        break;
                    case 4:
                        chosenArcheType = new AstrologerArchetype();
                        break;
                    default:
                        System.out.println("Invalid class choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner
                choice = 0; // Reset choice to an invalid value
            }
        } while (choice < 1 || choice > 4);

        return chosenArcheType;
    }
}
