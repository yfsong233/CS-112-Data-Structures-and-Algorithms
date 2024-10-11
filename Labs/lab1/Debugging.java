/* 
 * Lab 1 - Debugging Exercise
 * 
 * name: Yufeng Song
 * email: jyfsong@bu.edu
 *
 * Fix all the bugs in this file! 
 */

public class Debugging {       // Public to public; the class name has to be exactly the same as the file's name

    public static void main(String[] args) {  // public static void main(String[] args) {

	/*
         * The following code segment is intended to calculate
         * the area of a triangle.
         */
	double base = 3.5;
        int height = 2;
        System.out.println("Running Debugging.java");   // System.out.print() vs System.out.println()
	double area = height*(base/2);
        System.out.print("Area is: " + area);
    
     }   // method main()

} // class Debugging
