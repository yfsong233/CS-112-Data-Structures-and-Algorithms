/*
 * Card.java
 * 
 * A blueprint class for objects that represent a single playing card 
 * for a game in which cards have both colors and numeric values.
 * 
 * starter code: CS 112 Staff (cs112-staff@cs.bu.edu)
 * completed by: Yufeng Song (jyfsong@bu.edu)
 */

public class Card {
    /* The smallest possible value that a Card can have. */
    public static final int MIN_VALUE = 0;   // class variable
    
    /* The possible colors that a Card can have. */
    public static final String[] COLORS = {"blue", "green", "red", "yellow"};

    /* Define the third class constant here. */
    public static final int MAX_VALUE = 9;

    /* Put the rest of your class definition below. */
    
    
    /*
     * isValidColor() take the name of a color as a parameter, 
     * and return true if the specified color is valid
     * (i.e., if it is one of the colors listed in the COLORS array),
     * and false otherwise.
     * 
     * This method is case-sensitive;
     * If the parameter is null or has a length of 0,
     * the method should throw an IllegalArgumentException.
     */
    public static boolean isValidColor(String color){
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return true;
            }
        } return false;
    }

    /* Encapsulate the instance variables */
    private String color;
    private int value;

    
    /*
     * setColor() takes a String representing a color 
     * and sets the value of the Card object’s color field 
     * to the specified color. 
     * 
     * Attempts to assign an invalid color 
     * should produce an IllegalArgumentException.
     */
    public void setColor(String colorChanged) {
        if (isValidColor(colorChanged) == false) {
            throw new IllegalArgumentException();
        }
        this.color = colorChanged;
    }

    /*
     * setValue() takes an integer 
     * and sets the value of the Card object’s value field to the specified number.
     * 
     * Attempts to assign an invalid value 
     * (one that is outside the range specified by the MIN_VALUE and MAX_VALUE constants) 
     * should produce an IllegalArgumentException.
     */
    public void setValue(int valueChanged) {
        if (valueChanged < MIN_VALUE || valueChanged > MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        this.value = valueChanged;
    }

    
    /*
     * The constructor of Card class takes a string for the Card‘s color 
     * and an integer for the Card‘s value (in that order)
     * and initializes the values of the fields.
     */
    public Card(String initColor, int initValue) {
        setColor(initColor);
        setValue(initValue);
    }

    /* the accessor method for the field color */
    public String getColor() {
        return this.color;
    }

    /* the accessor method for the field value */
    public int getValue() {
        return this.value;
    }

    /* toString() returns a String representation of the Card object 
     * that can be used when printing it or concatenating it to a String. 
     */
    public String toString() {
        return this.color + " " + this.value;
    }

    /*
     * This method takes another Card object as a parameter 
     * and returns true if the called Card object matches the color
     * and/or value of the other Card object,
     * and false if it doesn’t match either the color or the value.
     * 
     * If a value of null is passed in for the parameter,
     * the method should return false.
     */
    public boolean matches(Card other) {
        if (other == null) {
            return false;
        }
        
        if (this.color == other.color || this.value == other.value) {
            return true;
        } return false;
    }

    /*
     * This method takes another Card object as a parameter 
     * and determines if it is equivalent to the called object,
     * returning true if it is equivalent and false if it is not equivalent.
     * Two Card objects should be considered equivalent
     * if their color and value are the same.
     * 
     * If a value of null is passed in for the parameter,
     * the method should return false.
     */
    public boolean equals(Card other) {
        if (other == null) {
            return false;
        }
        if (this.color == other.color && this.value == other.value) {
            return true;
        } return false;

    }



}
    
