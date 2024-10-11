/* 
 * Ladder.java
 * 
 * A program that allows a user to compute the length of a ladder
 * that is used to reach a certain point outside of one's house.
 * 
 * completed by: Yufeng Song (jyfsong@bu.edu)
 * 
 */

import java.util.*;

public class Ladder {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the height of the point the ladder needs to reach: ");
        int height = console.nextInt();
        System.out.println("Enter the angle in degrees at which the ladder will be positioned: ");
        int angle = console.nextInt();

        double radians = angle * Math.PI / 180.0;

        double length = (double)height / Math.sin(radians);

        System.out.println("The required length is: ");
        System.out.println(length + " feet");
        System.out.println(length/3 + " yards");
        double diff = length - ((int)length/3)*3;     
        System.out.println((int)length/3 + " yards and " + diff + " feet");
        // beaware that operation of division may affect the accuracy of results
        // include notes in the following problem sets
    }   
}
