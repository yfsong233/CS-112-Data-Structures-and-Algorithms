/* 
 * Problem5.java - PS6, pr5
 * 
 * Finding the union of two arrays
 * 
 * Created by Yufeng Song (jyfsong@bu.edu)
 */

import java.util.*;

public class Problem5 {
    
    /* 
     * union() uses an approach based on merging to create and return a reference to 
     * a new array containing the union of the values in a1 and a2 
     * – i.e., all values that are found in one or both of the original arrays
     * The result should be in sorted order, following by an equal nunmber of 0s if there are any duplicates.
     */
    public static int[] union(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException();
        }
        
        int[] union = new int[a1.length + a2.length];  // remember to include the new operator!
        Sort.mergeSort(a1);  // when to call a static method of another class, ClassName.methodname()
        Sort.mergeSort(a2);
        
        int i = 0;  // index into the sorted a1
        int j = 0;  // index into the sorted a2
        int k = 0;  // index into union
        int numZero = 0;  // the number of duplicates
        
        while (i < a1.length && j < a2.length) {  // including the last index?
            if (a1[i] < a2[j]) {
                union[k] = a1[i];
                i++;
    
            } else if (a1[i] == a2[j]) {
                union[k] = a1[i];
                numZero++;

                while (i+1 < a1.length && a1[i] == a1[i+1]) {
                    i++; numZero++;
                }
                i++;
                while (j+1 < a2.length && a2[j] == a2[j+1]) {
                    j++; numZero++;
                }
                j++;

            } else {
                union[k] = a2[j];
                j++;
            }
            
            k++;
        }

        while (i < a1.length) {
            union[k] = a1[i];
            i++;
            if (k == 0 || union[k] != union[k-1]) {  // in case that either a1 or a2 has a length of 0
                k++;
            } else {
                numZero++;
            }
        }

        while (j < a2.length) {
            union[k] = a2[j];
            j++;
            if (k == 0 || union[k] != union[k-1]) {  // in case that either a1 or a2 has a length of 0
                k++;
            } else {
                numZero++;
            }
        }

        for (int left = 0; k < union.length && left < numZero; left++) {
            union[k] = 0; 
            k++;
        }

        return union;
    }

    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = union(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));
        // result1: [4, 5, 7, 9, 10, 15, 0, 0, 0, 0, 0, 0, 0]

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = union(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));
        // result2: [-4, 0, 2, 6, 8, 10, 12, 0, 0, 0]

        int[] a5 = {6, 9, 3, 7, 8, 0};
        int[] a6 = {0};
        int[] result3 = union(a5, a6);
        System.out.println("result3: " + Arrays.toString(result3));
        // result 3: [0, 3, 6, 7, 8, 9, 0]

        int[] a7 = {100, 43, 29, 33, 0, 0, 80};
        int[] a8 = {29, 0, 0, 0, 44, 81, 101, 34};
        int[] result4 = union(a7, a8);
        System.out.println("result4: " + Arrays.toString(result4));
        // result 4: [0, 29, 33, 34, 43, 44, 80, 81, 100, 101, 0, 0, 0, 0, 0]

        // int[] a9 = null;
        // int[] a10 = {9};
        // int[] result5 = union(a9, a10);
        // System.out.println("result5: " + Arrays.toString(result5));
        // // IllegalArgumentException

        int[] a11 = {9, 8, 7, 6, 6, 6, 5, 5};
        int[] a12 = {};
        int[] result6 = union(a11, a12);
        System.out.println("result6: " + Arrays.toString(result6));
        // result6: [5, 6, 7, 8, 9, 0, 0, 0]

        int[] a13 = {};
        int[] a14 = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        int[] result7 = union(a13, a14);
        System.out.println("result7: " + Arrays.toString(result7));
        // result6: [1, 2, 3, 4, 0, 0, 0, 0, 0, 0]
    }
 }
