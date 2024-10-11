public class RomanNumeral {
    private String romanNumeral;
    private int decimal;

    public RomanNumeral(String initialNumeral) {
        // error-cheching goes here
        this.romanNumeral = initialNumeral;
        this.decimal = RomanNumeralStatic.convert(this.romanNumeral);
    }

    public String toString() {
        return this.romanNumeral;
    }

    public boolean equals(RomanNumeral other) {
        return (other != null && this.romanNumeral.equals(other.romanNumeral) 
        && this.decimal == other.decimal);   // use == to compare primitive types
    }  // can be done only comparing the numerical values of both

    public int add(RomanNumeral other) {
        return RomanNumeralStatic.add(this.romanNumeral, other.romanNumeral);
        // or simply adding the numerical values of both
    }

    public String getRomanNumeral() {
        return this.romanNumeral;
    }

    public int getDecimal() {
        return this.decimal;
    }


    public static void main(String[] args) {
        RomanNumeral r1 = new RomanNumeral("X");
        RomanNumeral r2 = new RomanNumeral("IX");

        System.out.println(r1);
        System.out.println(r2);

        System.out.print( "Testing for equality: the objects are " );
        if ( !r1.equals(r2) )
             System.out.print( "not " );
        System.out.println( "equal!" );

        // Invoke the add method to add two RomanNumeral objects.
        // Note that the output should be a decimal integer.
        System.out.println( r1.add(r2) );   
    }
}
