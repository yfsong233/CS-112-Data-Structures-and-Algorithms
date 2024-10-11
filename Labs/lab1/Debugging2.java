/* 
 * Lab 1 - Debugging Exercise #2
 * 
 * Practice debugging a static method
 *
 * name: Yufeng Song
 * email: jyfsong@bu.edu
 *
 * Fix all the bugs in this file! Then run it and add some extra tests
 * to make sure everything works
 *
 */

public class Debugging2 {
    /*
     * triArea - computes and returns the area of a triangle 
     * with base b and height h (both of which are integers).
     * The area is returned as a floating-point number, and 
     * the result should be as precise as possible.
     */

    public static double triArea(int b, int h) {
        double area = (double)b * h / 2;
        return area;
    }
	
    public static void main(String[] args) {
        System.out.println("Testing the triArea method");
        double triangleA = triArea(9, 3);
        System.out.println("triArea(9, 3) is " + triangleA);
    }
}
