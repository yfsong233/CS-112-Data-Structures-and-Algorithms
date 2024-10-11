/*
 * Lab 2 - Working with Java Strings
 * 
 * Yufeng Song (jyfsong@bu.edu)
 */



public class StringTest {

    public static String replaceStart(String s1, String s2) {
        /*
         * If the length of s1 (call it s1Len) is less than the length of s2,
         * the method returns a string
         * in which the first s1Len characters of s2 are replaced by s1.
         *  
         * If the length of s1 is greater than or equal to the length of s2,
         * the method just returns s1.
         */

         int s1Len = s1.length();
         int s2Len = s2.length();

         String finalOutput;

         if (s1Len < s2Len) {
            finalOutput = s1 + s2.substring(s1Len);
         } else {
            finalOutput = s1;
         } return finalOutput;
    }


    public static void main(String[] args) {
        String s1 = "Boston";
        String s2 = "University";

        System.out.println(s1);
        System.out.println(s2);

        System.out.println(s1.substring(3, 6));
        System.out.println(s2.substring(0, 3)); // 4(a)
        System.out.println(s2.substring(0, 3).toUpperCase()); // 4(b)
        System.out.println(s2.charAt(3)); // 4(c)
        System.out.println((s1.substring(2)+s2.charAt(4)).toUpperCase()); // 4(d)

        System.out.println( replaceStart("abc", "xxxxxxx") ); // 5 // abcxxxx
        System.out.println( replaceStart("hello", "bye") ); // 5 // hello

        /**
         * 6. Why does the method replaceStart need to be called 
         * from within the println method, rather than on its own line?
         * 
         * The method replaceStart returns the string, however, to display
         * the string we call the method from with the System.out.println statement.
         */

    }
}