/*
 * Lab 2 - Array methods
 * 
 * Yufeng Song (jyfsong@bu.edu)
 */

import java.util.*;

public class ArrayPractice {

    public static boolean equals(int[] list1, int[] list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        if (list1.length == list2.length) {
            int perfectMatch = 0;
            for (int i = 0; i < list1.length; i++) {
                if (list1[i] == list2[i]) {
                    perfectMatch++;
                }
            } if (perfectMatch == list1.length) {
                return true;
            }
        } return false;
    }
   
    public static void square(int[] a1) {
        if (a1 == null) {
            throw new IllegalArgumentException();
        }
       for (int i = 0; i < a1.length; i++) {
           a1[i] *= a1[i];
       }
    }

    public static int shiftLeft(int[] list1) {
        if (list1 == null || list1.length == 0) {
            throw new IllegalArgumentException();
        }
        
        int originalFirst = list1[0];
        for (int i = 0; i < list1.length - 1; i++) {
            list1[i] = list1[i+1];
        }
        list1[list1.length - 1] = 0;
        return originalFirst;
    }

    public static int replace(int[] list1, int val1, int val2) {
        if (list1 == null) {  // only check null for reference-type variables
            throw new IllegalArgumentException();
        }
        int numReplaced = 0;
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] == val1) {
                list1[i] = val2;
                numReplaced++;
            }
        } 
        return numReplaced;
    }

    public static int[] interleave(int[] list1, int[] list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        int smaller = list1.length;
        int larger = list2.length;
        boolean change = false;
        if (larger < smaller) {
            smaller = list2.length;  // an simple way to check a smaller number
            larger = list1.length;
            change = true;
        }
        int[] list3 = new int[smaller + larger];
        for (int i = 0; i < smaller; i++) {
            list3[i*2] = list1[i];
            list3[i*2+1] = list2[i];
        }
        int diff = larger - smaller;
        if (diff == 0) {
            return list3;
        } else {
            if (change == true) {
                for (int j = diff; j > 0; j--) {
                    list3[list3.length - j] = list2[list2.length - j];  
                    // relative position vs real index
                }
            } else {
                for (int j = diff; j > 0; j--) {
                    list3[list3.length - j] = list1[list1.length - j];
                }
            }
        } return list3;

    }

    // /*
    //  * interChange - this method interchanges the elements
    //  * from the two input arrays into a third array
    //  * which the method returns.
    //  *
    //  * Note the use of int[] as the return type indicating
    //  * that a reference to an array of integers is being
    //  * returned.
    //  */
    // public static int[] interChange(int[] a, int[] b) {
    //     if (a == null || b == null)
    //         throw new IllegalArgumentException();

    //     int smallest = a.length;

    //     // Only interchange as many elements as contained
    //     // int he smallest of the two arrays.
    //     if (b.length < smallest)
    //         smallest = b.length;

    //     // the array containing the interchanged elements
    //     // must be double the size of the smallest array.
    //     int[] c = new int[smallest * 2];

    //     // the loop uses two control variables,
    //     // one to index into the two arrays and one
    //     // to index into the array that will contain
    //     // the interchanged elements.
    //     for (int i = 0, j = 0; i < smallest; i++) {
    //         c[j] = a[i];
    //         j++;
    //         c[j] = b[i];
    //         j++;
    //     }

    //     return c;
    // }

    public static boolean isMirror(int[] l1, int[] l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException();
        }
        if (l1.length != l2.length) {
            return false;
        } else {
            int perfectMatch = 0;  // consider use a boolean variable instead
            for (int i = 0; i < l1.length; i++) {
                if (l1[i] == l2[l1.length-1-i]) {
                    perfectMatch++;
                }
            }
            if (perfectMatch == l1.length) {
                return true;
            } return false;
        }
    }

    // /*
    //  * isMirror - this method determines if the arrays
    //  * are mirror images and returns true or false accordingly.
    //  *
    //  * version 1: use a boolean variable for the return value,
    //  * and adjust it as needed based on the two arrays.
    //  */
    // public static boolean isMirror(int[] a, int[] b) {
    //     if (a == null || b == null)
    //         throw new IllegalArgumentException();

    //     boolean mirror = false;

    //     // As with equality, if the arrays are of different
    //     // sizes they cannot be mirror images.
    //     //
    //     if (a.length == b.length) {
    //         mirror = true;
    //         for (int i = 0, j = b.length - 1; i < b.length && mirror; i++, j--) {
    //             if (a[i] != b[j])
    //                 mirror = false;
    //         } // for
    //     } // if

    //     return mirror;
    // }

    public static boolean isMirror(String[] l1, String[] l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException();
        }
        if (l1.length != l2.length) {
            return false;
        } else {
            int perfectMatch = 0; 
            for (int i = 0; i < l1.length; i++) {
                if (l1[i].equals(l2[l1.length-1-i])) {  
                // if check not equal here, no need for a counter
                    perfectMatch++;
                }
            }
            if (perfectMatch == l1.length) {
                return true;
            } return false;
        }
    }

    public static void main(String[] args) {
        // int[] a = {2, 4, 6, 8};
        // System.out.println(a);  // print a reference of Array a
        // System.out.println(Arrays.toString(a)); // print the actual content of Array a 
        // // beware to prepend the class name correctly;

        // int[] a1 = {1, 2, 3, 4};
        // int[] a2 = {1, 2, 3, 4};
        // System.out.println(a1 == a2); // false; different references & not looking at inside
        // System.out.println(equals(a1, a2));  // true
        // System.out.println(Arrays.equals(a1, a2)); // compare the content using a built-in equals(), equal

        // square(a1);
        // System.out.println( Arrays.toString(a1) );  // [1, 4, 9, 16]

        // int[] a3 = {1, 2, 3, 4, 5, 6};
        // System.out.println( shiftLeft(a3) );  // 1
        // System.out.println( Arrays.toString(a3) );  // [2, 3, 4, 5, 6, 0]

        // int[] a4 = {1, 2, 3, 2, 2, 6};
        // int numReplaced;
        // // first test
        // numReplaced = replace(a4, 2, 9);
        // System.out.println( Arrays.toString(a4) );  // [1, 9, 3, 9, 9, 6]
        // System.out.println( "numReplaced = " + numReplaced );  // numReplaced = 3
        // // second test
        // numReplaced = replace(a4, 2, 9);
        // System.out.println( Arrays.toString(a4) );  // [1, 9, 3, 9, 9, 6]
        // System.out.println( "numReplaced = " + numReplaced);  // numReplaced = 0
        // // third test
        // replace(a4, 2, 9);  // make the changes but the return value gets thrown away
        // System.out.println( replace(a4, 2, 9) );  // 0  // the return value gets printed

        int[] a5 = {1, 2, 3, 4, 5};
        int[] a6 = {6, 7, 8, 9, 10, 11, 12};
        int[] a7 = interleave(a5, a6);
        System.out.println( Arrays.toString(a7) );  // [1, 6, 2, 7, 3, 8, 4, 9, 5, 10, 11]

        // int[] a8 = {1, 2, 3, 4, 5};
        // int[] a9 = {5, 4, 3, 2, 1};
        // System.out.println( isMirror(a8, a9) );  // true
        // int[] a10 = {1, 4, 5, 2, 1};
        // System.out.println( isMirror(a8, a10) );  // false

        // String[] s1 = { "abc", "def", "ghi" };
        // String[] s2 = new String[3];
        // s2[0] = new String("ghi");
        // s2[1] = new String("def");
        // s2[2] = new String("abc");
        // System.out.println( isMirror(s1, s2) );  // true

   }
    
}
