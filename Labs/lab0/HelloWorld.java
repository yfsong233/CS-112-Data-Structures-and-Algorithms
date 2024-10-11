import java.util.*;

public class HelloWorld {
    
    public static int compute(int x, int y) {
        x += 3;
        y = 2*y - x;
        System.out.println(x + " " + y);
        return x;
    }

    public static double bmi(int w, int h) {
        double result = (720.0 * w) / (h * h);
        return result;
    }

    public static void main(String[] args) {  // 1
        /* 1. definite loop */
        for (int num = 1; num < 6; num += 2) {
            System.out.println(num);
        }
    
        System.out.println();

        /* 2. Type cast */
        int pointsEarned = 13;
        int possiblePoints = 15;

        double grade;
        grade = (double)pointsEarned / possiblePoints * 100.0;
        System.out.println("The grade is: " + grade);

        System.out.println();

        /* 3. Arithmetic computation in Java (typed operators; ) */
        int x = 23;
        double y = 4;
        int z = 4;
        double n = 14.0;

        System.out.println(x/y);
        System.out.println(x/z);
        System.out.println(x+y);
        System.out.println("x"+"y");
        System.out.println(x%6);
        System.out.println(y == z);
        System.out.println((int)(n / z * z));
        System.out.println((int)n / z * z);
        System.out.println(11 + 2 + "CS");
        System.out.println("CS" + 11 + 2);

        System.out.println();

        /* 4. Scanner and console input */
        Scanner scan = new Scanner(System.in);  // 4
        System.out.print("Enter three numbers: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        // also review other Scanner methods

        if (a <= c) {
            if (c > b && a < 5) {
                System.out.println("Terriers");
            } else {
                System.out.println("Eagles");
            }
            System.out.println("Crimson");
        } else if (b < a) {
            if (b == c) {
                System.out.println("Huskies");
            } else if (b > c) {
                System.out.println("Engineers");
            } else {
                System.out.println("Bears");
            }
            if (a < c) {
                System.out.println("Lions");
            }
        } else {
            System.out.println("Big Green");
            if (a == b || b > 7) {
                System.out.println("Big Red");
            }
            if (!(b > c)) {
                System.out.println("Quakers");
            }
            if (a != c) {
                System.out.println("Bulldogs");
            }
        }
        System.out.println("Let's go!");
    
        System.out.println();

        /* 5. PS1 testing */
        int j = 1;  // 1
        int k = 3;
        System.out.println(j + " " + k);
        j = compute(j, k);
        System.out.println(j + " " + k);
        compute(k, k);
        System.out.println(j + " " + k);
        k = 3 + 4 * compute(k, j);
        System.out.println(j + " " + k);
    
        System.out.println();

        System.out.println(bmi(100,60));  // 2
        System.out.println(bmi(162,72));

        System.out.println();

        // /* 6. println() and print() */
        // System.out.println("average: ");
        // int avg = scan.nextInt();
        // String grade;
        // if (avg >= 90) {
        //     grade = "A";
        // } else {
        //     grade = "Not A";
        // }
        // System.out.print(avg + " = " + grade);
        // System.out.println("Work harder!");  
        // (for line 120 & 121) 10 = Not AWork harder!

        System.out.println();

        /* 7. printing chars with Strings */
        // char a = 't';
        // char b = 'a';
        // String bString = "a";
        // char c = '2';
        // char d = '1';  // cannot assign 10 here
        // System.out.println(a+b+bString);  // 213a
        // System.out.println(a+b+bString+c);  // 213a2
        // System.out.println(a+b+bString+c+d);  // 213a21
        
        // String holiday = "Spring Break";
        // System.out.println(holiday.charAt(0) + holiday.charAt(7));  // 149
        // System.out.println(holiday.charAt(0) + "" +  holiday.charAt(7));  // SB
        // // String yelling = holiday * 3; // doesn't work!
        // System.out.println(holiday.charAt(0) + " Yoo!!");  // S Yoo!!

        System.out.println();

        /* 8. Scanner and array */
        // int[] temp = new int[4];
        // System.out.println("Enter 4 temps: ");
        // temp[0] = scan.nextInt();
        // temp[1] = scan.nextInt();
        // temp[2] = scan.nextInt();
        // temp[3] = scan.nextInt();
        // System.out.println(Arrays.toString(temp));

        System.out.println();
        
        /* 8. More arithmetic computation */
        System.out.println(2 + 2 + 3 + 4);	// 11
        System.out.println("2 + 2 " + 3 + 4);  // 2 + 2 34
        System.out.println(2 + " 2 + 3 " + 4);	// 2 2 + 3 4
        System.out.println(3 + 4 + " 2 + 2");  /* 7 2 + 2 */
        System.out.println("2 + 2 " + (3 + 4));  /* 2 + 2 7 */
        System.out.println("(2 + 2) " + (3 + 4));  // (2 + 2) 7
        System.out.println("hello 34 " + 2 * 4);  // hello 34 8
        System.out.println(2 + "(int) 2.0" + 2 * 2 + 2);  /* 2(int 2.0)42 */
        System.out.println(4 + 1 + 9 + "." + (-3 + 10) + 11 / 3);  // 14.73
        System.out.println(8 + 6 * -2 + 4 + "0" + (2 + 5));	 // 007
        System.out.println(1 + 1 + "8 - 2" + (8 - 2) + 1 + 1);  // 28 - 2611
        System.out.println(5 + 2 + "(1 + 1)" + 4 + 2 * 3);  // 7(1+1)46
        System.out.println("1" + 2 + 3 + "4" + 5 * 6 + "7" + (8 + 9));  // 123430717

        
    }
    

}