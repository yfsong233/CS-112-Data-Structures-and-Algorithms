/*
 * Animal.java
 *
 * A simple class representing an animal.
 */

public class Animal {
    private String name;
    private int numLegs;

    public Animal(String name, int numLegs) {
        this.name = name;
        this.numLegs = numLegs;
    }

    public String getName() {
        return this.name;
    }

    public int getNumLegs() {
        return this.numLegs;
    }

    public boolean isSleeping(int hour, int minute) {
        // error-checking for time
        if (hour > 24 || hour < 0 || minute > 60 || minute < 0) {
            throw new IllegalArgumentException("invalid time specified");
        }

        return (hour >= 22 || hour <= 5);
    }

    public String toString() {
        return this.name;
    }

    public static void printAnimalName(Animal an) {
	// Output the name of the animal
        System.out.println(an);
    }
}
