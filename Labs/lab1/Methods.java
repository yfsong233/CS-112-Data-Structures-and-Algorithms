/*
 * CS112
 *
 * Lab 1: Simple Methods
 *
 * name: Yufeng
 * email: jyfsong@bu.edu
 *
 *
 * Note: Because this class is simply a collection of static methods 
 * and it does not have a main method, you cannot run it.
 * 
 * You will need to write a main method that makes calls to each of the
 * methods to test them. Follow the instructions in the lab.
 */

public class Methods {
    /*
     * print3Times - takes a string s and prints it 3 times
     */
    public static void print3Times(String s) {
        for (int i = 0; i < 3; i++) {
            System.out.println(s);
        }
    }

    public static void printNTimes(int times, String word) {  // the construction line should not be followed by a semicolon
        int i;
        for (i = 0; i < times; i++) {
            System.out.println(word);
        }
    }

    public static void main(String[] args) {
        /*
         * String test = print3Times("hello");   // cannot assign a method without return value to a variable
         * System.out.println(test); // cannot print a method whose output is printing
         */
        print3Times("Hello");   // A correct call to a method without a return value
        System.out.println();
        printNTimes(6, "Magis!");
    }
    
}
