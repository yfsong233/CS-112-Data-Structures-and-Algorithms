/*
 * Tester.java 
 * 
 * A program that you can use to make test calls to the methods that you 
 * write as part of your Wordle implementation. 
 */

import java.util.*;

public class Tester {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // sample test for the includes method
        System.out.println("\nTesting includes method...");
        boolean result = Wordle.includes("hello", 'e');
        System.out.println("includes(\"hello\", 'e') returns " + result); // this is how to embed a String in a double-quoted environment

        boolean helloAndL = Wordle.includes("hello", 'l');
        System.out.println("includes(\"hello\", 'l') returns " + helloAndL);

        boolean goodbyeAndX = Wordle.includes("goodbye", 'x');
        System.out.println("includes(\"goodbye\", 'x') returns " + goodbyeAndX);

        boolean helloIsAlpha = Wordle.isAlpha("Hello");
        System.out.println("isAlpha(\"Hello\") returns " + helloIsAlpha);

        boolean goodbyeIsAlpha = Wordle.isAlpha("Goodbye!");
        System.out.println("isAlpha(\"Goodbye!\") returns " + goodbyeIsAlpha);

        int helloNumOccur = Wordle.numOccur('l', "hello");
        System.out.println("numOccur('l', \"hello\") returns " + helloNumOccur);

        int helloNumOccurE = Wordle.numOccur('e', "hello");
        System.out.println("numOccur('e', \"hello\") returns " + helloNumOccurE);

        int goodbyeNumOccur = Wordle.numOccur('x', "goodbye");
        System.out.println("numOccur('x', \"goodbye\") returns " + goodbyeNumOccur);

        int applePosn = Wordle.numInSamePosn('p', "apple", "maple");
        System.out.println("numInSamePosn('p', \"apple\", \"maple\") returns " + applePosn);

        int applePosnA = Wordle.numInSamePosn('a', "apple", "maple");
        System.out.println("numInSamePosn('a', \"apple\", \"maple\") returns " + applePosnA);

        int javaPosn = Wordle.numInSamePosn('a', "java", "mama");
        System.out.println("numInSamePosn('a', \"java\", \"mama\") returns " + javaPosn);


        // Add additional tests below to ensure that your methods
        // work correctly.

        console.close();
    }
}
