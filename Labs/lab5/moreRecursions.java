public class moreRecursions {
    
    public static int fib(int n) {
        /*
         * recursively generate the nth integer in the revised Fibonacci sequence
         * that starts with 1, with multiple returns
         */
        if (n == 0 || n == 1) {
            return 1;
        } else {
            int prev1 = fib(n - 2);
            int prev2 = fib(n - 1);
            return prev1 + prev2;
        }
    }
    
    public static int fibRevised(int n) {
        /*
         * recursively generate the nth integer in the revised Fibonacci sequence
         * that starts with 1, with only one return
         */
        int additionResult = 0;
        if (n == 0 || n == 1) {
            additionResult = 1;
        } else {
            int prev1 = fib(n - 2);
            int prev2 = fib(n - 1);
            additionResult = prev1 + prev2;
        }

        return additionResult;
    }

    public static int factorial(int n) {
        /*
         * takes in an integer n and returns the factorial of that integer. 
         * If n is zero, return 1.
         */
        if (n == 1) {
            return 1;
        } 
        int factorRest = factorial(n-1);
        return n * factorRest;
    }

    public static int countN(int n, int[] arr, int index) {
        /*
         * determine the number of times that the integer n appears 
         * in the portion of the array of arr that goes from the specified index to the end of the array.
         * 
         * To process an array recursively, 
         * an parameter of index is rather helpful to focus on a specific portion of array
         */
        if (arr == null || index < 0) {
            throw new IllegalArgumentException();
        }
        if (index == arr.length) {
            return 0;
        } 
        int ocrRest = countN(n, arr, index+1);
        if (n == arr[index]) {
            return 1 + ocrRest;
        } return ocrRest;
    }

    public static int min(int[] arr, int index) {
        /* returns the smallest integer in the array */
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        // index = 0;  // since each call resets index to 0, an infinite recusion is incurred.
        if (index == arr.length - 1) {
            return arr[index];
        } 
        int minRest = min(arr, index+1);
        if (arr[index] < minRest) {
            return arr[index];
        } return minRest;
    }

    public static int commonDenominator(int m, int n) {
        /* 
         * returns the greatest common integer denominator of m and n, 
         * using Euclid’s algorithm
         */
        
        int remainder = m % n;
        if (m < n) {
            remainder = n % m;
        }
        if (remainder == 0) {
            return n;
        }
        int rest = commonDenominator(n, remainder);
        return rest;
    }

    public static void main(String[] args) {
        // Testing for factorial()
	    System.out.println(factorial(5) == 120);  // true

        // Testing for countN()
        int[] a = {5, 3, 5, 2, 7, 3, 5};
        System.out.println(countN(5, a, 0) == 3);  // true
        System.out.println(countN(5, a, 1) == 2);  // true
        System.out.println(countN(5, a, 3) == 1);  // true

        // Testing for min()
        int[] b = {3, 7, 9, 10, 12, 5, 14, 8};
        System.out.println(min(a, 0) == 2);  // true
        System.out.println(min(b, 0) == 3);  // true

        // Testing for commonDenominator()
        System.out.println(commonDenominator(106, 16) == 2);  // true
        System.out.println(commonDenominator(16, 106) == 2);  // true
        System.out.println(commonDenominator(1220, 516) == 4);  // true
        
    }


}
