/*
 * Cat.java
 *
 * A simple class representing a cat.
 * 
 */

public class Cat extends Animal {  
    /*
     * Animal: superclass; Cat: subclass
     * Cat inherits all the methods and fields of Animal
     * However, Cat cannot directly access the private fields of Animal
     */ 

    private boolean isShortHaired;

    public Cat(String nameCat, boolean shortHairedOrNot) {
	    super(nameCat, 4);  
        // super(self-set parameter1, self-set parameter2); // no () and . after super
        this.isShortHaired = shortHairedOrNot;  
        // distinguish between the parameter passed into the constructor and the corresponding field
    }

    public boolean isShortHaired() {
        return this.isShortHaired;
    }
    
    public boolean isExtroverted() {
        return false;
    }

    public String toString() {
        String output = this.getName();
        if (isShortHaired) {
            output += " (short-haired)";
        } else {
            output += " (long-haired)";
        } return output;
    }

    public boolean isSleeping(int hour, int minute) {  
        // to override: exact method title, take in exact parameters 

        return true;  // always sleeping
    }

    // compare with the Dog class
}
