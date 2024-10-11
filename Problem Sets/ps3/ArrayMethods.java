/*
 * PS3, Pr4 - Array-processing methods
 * 
 * completed by: Yufeng Song (jyfsong@bu.edu)
 */

import java.util.*;

public class ArrayMethods {
    public static final String[] DAY_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    /*
     * getDayNum() returns the index of String day in the array
     * referred to by the class constant DAY_NAMES
     * 
     * if the parameter is null or not found in DAY_NAMES, return -1;
     * this method is not case-sensitive.
     */
    public static int getDayNum(String day) {
        if (day == null) {
            return -1;
        }
        for (int i = 0; i < DAY_NAMES.length; i++) {
            if (DAY_NAMES[i].equalsIgnoreCase(day) == true) {
                return i;
            }
        } return -1;

    }


    /*
     * swapNeighbors() takes a reference to an array of ints
     * and swaps pairs of elements that are "neighbors" of each other
     * 
     * if the parameter is null, throw an IllegalArgumentException;
     * in an odd-length array, the last element should not be moved.
     */
    public static void swapNeighbors(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException();
        }

        if (values.length % 2 == 0) {
            int eachDiff = 0;
            for (int i = values.length - 1; i > 0; i--) {
                if (i % 2 == 1) {
                    eachDiff = values[i-1]-values[i];
                    values[i] = values[i-1];
                    values[i-1] -= eachDiff;
                }
            }
        } else {
            int eachDiff = 0;
            for (int i = values.length - 2; i > 0; i--) {
                if (i % 2 == 1) {
                    eachDiff = values[i-1]-values[i];
                    values[i] = values[i-1];
                    values[i-1] -= eachDiff;
                }
            }
        }
    }
    
    /*
     * copyWithCeiling() takes a reference to an array of ints values
     * and an integer, and that creates and returns a new array
     * based on values in which all elements greater than ceiling
     * are replaced by the value ceiling
     * 
     * must not modify the original array;
     * If the first parameter is null, 
     * the method should throw an IllegalArgumentException.
     */
    public static int[] copyWithCeiling(int[] values, int ceiling) {
        if (values == null) {
            throw new IllegalArgumentException(); // the brackets matter
        }
        
        int[] replaced = new int[values.length];   // how to create a new array of a fixed length
        for (int i = 0; i < values.length; i++) {
            if (values[i] > ceiling) {
                replaced[i] = ceiling;
            } else {
                replaced[i] = values[i];
            }
        } return replaced;   // a string representation of an array
    }

    /*
     * mostOccur() takes a reference to a sorted array of ints
     * and returns a value that occurs most often in the array
     * 
     * If two or more values tie for the most occurrences, 
     * return the one that comes first;
     * If all of the values occur exactly once, or if there is only one element, 
     * the first element should be returned;
     * must not modify the original array;
     * If the parameter is null or has a length of 0,
     * the method should throw an IllegalArgumentException.
     */
    public static int mostOccur(int[] arr) {
        int mostOccurredInt = 0;
        int mostTimes = 0;
        int eachTimes = 1;   // beaware of the initialized count

        // System.out.println("variable initialized");
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        } 
        // System.out.println("Enter the loop");
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]) {
                // System.out.println("true block 1");
                eachTimes += 1;
            } else {
                // System.out.println("false block 1");
                if (eachTimes > mostTimes) {
                    // System.out.println("true block 2");
                    mostOccurredInt = arr[i];
                    mostTimes = eachTimes;
                    eachTimes = 1;  // beaware of the value reset!!!
                } else {
                    // System.out.println("false block 2");
                    eachTimes = 1;
                }
            }
        }
        // System.out.println("Loop ends");
        // System.out.println("mostTimes is" + mostTimes);

        if (eachTimes > mostTimes) {
            mostOccurredInt = arr[arr.length-1];   // I ignored the situation of arr9
        }
         
        if (arr.length == 1 || eachTimes == arr.length) {
            mostOccurredInt = arr[0];
        }
        
        return mostOccurredInt;   

    }

    /*
     * find() takes two arrays containing sequences of integers and that returns 
     * the index of the first occurrence of the first sequence in the second sequence,
     * or -1 if the first sequence does not appear in the second sequence.
     * 
     * If either parameter is null or has a length of 0,
     * the method should throw an IllegalArgumentException.
     */
    public static int find(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            throw new IllegalArgumentException();
        }
        if (arr2 == null || arr2.length == 0) {
            throw new IllegalArgumentException();
        }
       
        // System.out.println("Error-cheching done");

        if (arr1.length > arr2.length) {
            return -1;
        } 
        
        // System.out.println("arr1.length <= arr2.length");

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] == arr1[0]) {   // IndexOutOfBoundary: arr1[i]
                int perfectMatch = 1;
                if (arr2.length - i < arr1.length) {
                    return -1;
                }
                for (int j = 1; j <= arr1.length; j++) {
                    if (arr1[j] != arr2[i+j]) {    // IndexOutOfBoundary: arr1[i+j]
                        break;
                    } else {
                        perfectMatch += 1;
                    }
                    if (perfectMatch == arr1.length) {
                        return i;
                    }
                }
            }
        } 
        return -1;
    }


    public static void main(String[] args) {
        
        // test for getDayNum
        // System.out.println(getDayNum("Sunday") == 0);   // true
        // System.out.println(getDayNum("Wednesday") == 3);  // true
        // System.out.println(getDayNum(null));  // error message
        // System.out.println(getDayNum("SaTurDAY") == 6);  // true
        // System.out.println(getDayNum("THHHHHHUusday"));  // -1

        
        // test for swapNeighbors()
        // int[] a1 = {0, 2, 4, 6, 8, 10};  
        // swapNeighbors(a1);
        // System.out.println(Arrays.toString(a1));  // [2, 0, 6, 4, 10, 8]

        // int[] a2 = {1, 2, 3, 4, 5, 6, 7};
        // swapNeighbors(a2);
        // System.out.println(Arrays.toString(a2));   // [2, 1, 4, 3, 6, 5, 7]

        // int[] mixOrder = {2, 4, 5, 3, 6, 4, 7, 8, 10};
        // swapNeighbors(mixOrder);
        // System.out.println(Arrays.toString(mixOrder));  // [4, 2, 3, 5, 4, 6, 8, 7, 10]

        // int[] single = {11};
        // swapNeighbors(single);
        // System.out.println(Arrays.toString(single));  // [11]

        // int[] nullTestSwap = new int[5];
        // swapNeighbors(nullTestSwap);
        // System.out.println(Arrays.toString(nullTestSwap));


        // test for copyWith Ceiling()
        // int[] a3 = {2, 5, 6, 3, 7, 4, 1};
        // int[] a4 = ArrayMethods.copyWithCeiling(a3, 4);
        // System.out.println(Arrays.toString(a4));  // [2, 4, 4, 3, 4, 4, 1]
        // int[] nullTestCeiling = new int[5];
        // swapNeighbors(nullTestCeiling);
        // System.out.println(Arrays.toString(nullTestCeiling));


        // test for mostOccur()
        // int[] arr = {1, 2, 3, 3, 8, 8, 8, 8, 11, 11, 11, 14, 19, 19};
        // System.out.println(mostOccur(arr) == 8);  // true
        // int[] arr2 = {1, 1, 2, 2, 3, 3, 8, 8, 11, 11, 14, 14, 19, 19};
        // System.out.println(mostOccur(arr2) == 1);  // true
        // int[] arr3 = {2, 3, 8, 11, 14, 19};
        // System.out.println(mostOccur(arr3));  // true 2
        // int[] arr4 = {11};
        // System.out.println(mostOccur(arr4));  // true 11
        // int[] arr5 = {14, 14, 14, 14, 14, 14, 14};
        // System.out.println(mostOccur(arr5));  // true 14
        // int[] arr6 = new int[6];
        // System.out.println(mostOccur(arr6));
        // int[] arr7 = {};
        // System.out.println(mostOccur(arr7));
        // System.out.println(mostOccur(null));
        int[] arr8 = {1, 2, 2, 3, 3, 4};
        System.out.println(mostOccur(arr8));  // 2
        int[] arr9 = {1, 1, 1, 2, 4, 4, 4, 4};
        System.out.println(mostOccur(arr9));  // 4


        // test for find()  // index out of bounds
        // int[] list1 = {1, 3, 6};
        // int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};
        // int[] list3 = {12, 1, 3, 6};
        // int[] list4 = {};
        // System.out.println(find(list1, list2) == 8);  // true 
        // System.out.println(find(list3, list2) == -1);  // true
        // System.out.println(find(list3, list1) == -1);  // true
        // System.out.println(find(list4, list2));  // IllegalArgumentException
        
    }
}