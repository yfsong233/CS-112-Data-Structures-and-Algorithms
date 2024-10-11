/* 
 * RecurPalindrome - implementing a recursive method for determining 
 * if a string is a palindrome
 */

import java.util.Scanner;

public class RecurPalindrome {
    public static void main(String[] args) {
        System.out.println("\nWelcome to the Palindrome Test Program!");

        // Reads in one string from the user
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nType in a word and press enter: ");
        String word = userInput.next();
        userInput.close();

        // Call the recursive function here by replacing true with your call.
        boolean isPalindrome = rPalindromeAlternative(word);

        // Printing out the result
        if (isPalindrome) {
            System.out.println("Palindrome!");
        } else {
            System.out.println("Not a palindrome!");
        }
    }

    private static boolean rPalindrome(String s) {
        // YOUR CODE HERE
        // Hints: you will need to construct the base cases
        // and make the appropriate recursive call(s)!
        // Assume that the input string is an English word 
        // without any spaces or special characters.
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.length() <= 1) {
            return true;
        }
        boolean palinRest = rPalindrome(s.substring(1, s.length()-1));  // the slicing range matters!
        if (s.charAt(0) == s.charAt(s.length()-1)) {
            return palinRest;
        } 
        return false;

    }

    public static boolean rPalindromeAlternative(String s) {
        // an implicit base case
        boolean isPal = true;
        if (s.length() > 1) {
            int length = s.length();
            if (s.charAt(0) == s.charAt(length-1)) {
                isPal = rPalindromeAlternative(s.substring(1, length-1));
            } else {
                isPal = false;
            }
        }
        return isPal;
    }
}
