public class ps5MidtermTester {
    public static void print(int[] arr, int start) {
        // parameter start keeps track of where you are in the array
        // always check for null references first
        
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        if (start < 0 || start >= arr.length) {
            throw new IllegalArgumentException();
        }
        
        if (start == arr.length-1) {
            System.out.println(arr[start]);
            return;
        } else {
            System.out.println(arr[start]);
            print(arr, start+1);
        }
    
    }

    public static void printReverse(int[] arr, int i) {
        /* for full credit: the first call should consider
         * the first element of the array first.
         * i - the index of the element that will be printed at last
         */
        
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        if (i < 0 || i >= arr.length) {
            throw new IllegalArgumentException();
        } 
        if (i == arr.length-1) {
            System.out.println(arr[i]);
            return;
        } else {
            printReverse(arr, i+1);
            System.out.println(arr[i]);
        }
    
    }

    public static int foo(int x, int y) {
        if (y == 1) {
            // System.out.println(x+1);
            return x + 1;
        } else if (x >= y) {
            // System.out.println(y+2);
            return y + 2;
        } else {
            int result1 = foo(x - 1, y - 2);
            int result2 = foo(x + 1, y - 1);
            // System.out.println(result1 + result2);
            return result1 + result2;
        }
    }
    
    /* midterm 1 - pr8 */
    public static String combine(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException();
        }
        String merge = "";
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                merge += s1.substring(i, i+1);  
                /* 
                 * errenous version: merge += s1.charAt(i);
                 * be aware that the addition of two chars is an integer result!!!
                 */
            } else {
                merge += s1.substring(i, i+1) + s2.substring(i, i+1);;  
                // errenous version: merge += s1.charAt(i) + s2.charAt(i);
            }
        }
        return merge;
    }
    
    /* For pr13 in midterm 1
     * Mistakes:
     * 1. getNumSold() should be a static method, but missing "static" in the method header
     * 2. Should numCoffeeSold and numOtherSold be private variables? 
     *    Can static variables be private? 
     *    If so, what's the point?
     */

    public static void main(String[] args) {
        int[] arr1 = {2, 5, 7, 4, 1};
        // print(arr1, 0);
        // System.out.println();
        // print(arr1, 1);
        // System.out.println();
        // print(arr1, 2);
        // System.out.println();
        // print(arr1, 3);
        // System.out.println();
        // print(arr1, 4);
        // System.out.println();
        // // print(arr1, 5);  // IllegalArgumentException
        // System.out.println();

        // printReverse(arr1, 0);
        // System.out.println();
        // printReverse(arr1, 1);
        // System.out.println();
        // printReverse(arr1, 2);
        // System.out.println();
        // printReverse(arr1, 3);
        // System.out.println();
        // printReverse(arr1, 4);

        // System.out.println(foo(4,7));

        System.out.println(combine("sink", "suns"));  // true  // "siunks"
        System.out.println();
        System.out.println(combine("power", "lover"));  // true  // "plowver"
        System.out.println();
        System.out.println(combine("abcde", "ABCDE"));  // true  // "aAbBcCdDeE"
        System.out.println();
        System.out.println(combine("xyz", "xyz"));  // true  // "xyz"
        System.out.println();
        // System.out.println(combine("xyz", "xyzz"));  // IllegalArgumentException



    }
}
