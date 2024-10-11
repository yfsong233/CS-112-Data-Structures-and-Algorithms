/*
 * DriverTester.java
 *
 * A simple test program for the Animal class
 */

public class DriverTester {
    public static void main(String[] args) {
        Cat c = new Cat("Kitty", false);
        System.out.println(c);  // Kitty (long-haired)
        System.out.println(c.isSleeping(24, 0));

        Abyssinian a = new Abyssinian("Abby");
        System.out.println(a.isExtroverted());
        System.out.println(a.getName());
        // Abyssinian also inherits animals' methods and fields as Cat's constructor initializes it.

        // System.out.println(Animal.printAnimalName(c)); 
        // cannot print a method whose entirety is a print
        Animal.printAnimalName(c);
        Animal.printAnimalName(a);
    }
}
