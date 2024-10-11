public class ps2Pr2Task0 {
    public static void main(String[] args) {

        String original = "Boston";
        String other = "Boooooooston";

        System.out.println("The length of String original is " + original.length());
        System.out.println("The last character is " + original.charAt(original.length()-1));
        System.out.println("The sliced result is " + original.substring(3));
        System.out.println("The capitalized result is " + original.toUpperCase());
        System.out.println("The decapitalized result is " + original.toLowerCase());
        System.out.println("Does String original contain 'os'? " + original.contains("os"));
        System.out.println("The index of character 'B' is " + original.indexOf('B'));
        System.out.println("The index of 'ton' is " + original.indexOf("ton"));
        System.out.println("Does String original equal String other? " + original.equals(other));

        System.out.println(original.substring(3).toUpperCase());   // Does the order of methods matter?
        System.out.println(original.toUpperCase().substring(3));

        System.out.println(original.replace('t', 'a').substring(3));  // .replace()
        System.out.println(original.replace('t', 'a').charAt(3));
        System.out.println(original.substring(3).replace('t', 'a'));

    }
}