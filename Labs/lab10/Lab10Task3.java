/*
 * Lab 10, Task 3
 * CS 112
 */

public class Lab10Task3 {
    /*
     * numDigits - takes an integer and returns the number of digits
     * that it has
     */ 
    public static int numDigits(int val) {
        String valString = Integer.toString(Math.abs(val));
        return valString.length();
    }
    
    /* 
     * divideByLength - takes an array of integers between 0 and 999 
     * and returns a List (either an ArrayList or LLList) 
     * in which all of the one-digit numbers come first, 
     * followed by all of the two-digit numbers, followed by all of the three-digit numbers. 
     * In the returned List, the numbers in a given subgroup (e.g., the one-digit numbers), 
     * are in the same order with respect to each other as they were in the original array.
     */
    public static List divideByLength(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();  // OR return null; ?
        }
        
        List reordered = new ArrayList(values.length);
        Queue<Integer> one = new LLQueue<Integer>();
        Queue<Integer> two = new LLQueue<Integer>();
        Queue<Integer> three = new LLQueue<Integer>();

        for (int i = 0; i < values.length; i++) {
            int digit = numDigits(values[i]);
            if (digit == 1) {
                one.insert(values[i]);
            } else if (digit == 2) {
                two.insert(values[i]);
            } else {
                three.insert(values[i]);
            }
        }
        
        int position = 0;
        while (! one.isEmpty()) {
            reordered.addItem(one.remove(), position);
            position++;
        }
        while (! two.isEmpty()) {
            reordered.addItem(two.remove(), position);
            position++;
        }
        while (! three.isEmpty()) {
            reordered.addItem(three.remove(), position);
            position++;
        }

        return reordered;
    }

    public static void main(String[] args) {
        /* Test on divideByLength */
        int[] vals = {7, 300, 55, 3, 213, 24, 78, 8, 411};
        List results = Lab10Task3.divideByLength(vals);
        System.out.println( results );  // {7, 3, 8, 55, 24, 78, 300, 213, 411}
    }
}
