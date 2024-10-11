

import java.util.*;

public class Wordle2 {
        // the name of a file containing a collection of English words, one word per line
        public static final String WORD_FILE = "words.txt";

        /*
         * printWelcome - prints the message that greets the user at the beginning of the game
         */  
        public static void printWelcome() {
            System.out.println();   
            System.out.println("Welcome to Wordle!");
            System.out.println("The mystery word is a 5-letter English word.");
            System.out.println("You have 6 chances to guess it.");
            System.out.println();
        }
        
        /*
         * initWordList - creates the WordList object that will be used to select
         * the mystery work. Takes the array of strings passed into main(),
         * since that array may contain a random seed specified by the user 
         * from the command line.
         */
        public static WordList initWordList(String[] args) {
            int seed = -1;
            if (args.length > 0) {
                seed = Integer.parseInt(args[0]);
            }
    
            return new WordList(WORD_FILE, seed);
        }
    
        /*
         * readGuess - reads a single guess from the user and returns it
         * inputs:
         *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
         *   console - the Scanner object that will be used to get the user's inputs
         */
        public static String readGuess(int guessNum, Scanner console) {
            String guess;
            do {
                System.out.print("guess " + guessNum + ": ");
                guess = console.next();
            } while (! isValidGuess(guess));
    
            return guess.toLowerCase();
        }
    
        /**** ADD YOUR METHODS FOR TASK 1 HERE ****/
         public static boolean includes(String s, char c) {  // CharSequence c?
            /* 
             * returns the boolean literal true if c is found somewhere in s,
             * and false otherwise. 
             */
    
             for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    return true;
                }
             } return false;
             
         }
    
    
         public static boolean isAlpha(String s) {
            
            /* 
             * returns true if all of the characters in s are letters of the alphabet,
             * and false otherwise. 
             */
    
            for (int i = 0; i < s.length(); i++) {
                if (Character.isAlphabetic(s.charAt(i)) == false) {
                    return false;
                }
            }
            return true;
         }
    
    
         public static int numOccur(char c, String s) {
            
            /* 
             * count and return the number of times that c occurs in s 
             */
    
            int timesOccurred = 0;
            for (int i = 0; i < s.length(); i++) {
                if (c == s.charAt(i)) {
                    timesOccurred += 1;
                }
            }
            return timesOccurred;
         }
    
    
         public static int numInSamePosn(char c, String s1, String s2) {
             
            /* 
             * count and return the number of times
             * that c occurs in the same position in both s1 and s2.
             * (Assume s1 and s2 are of the same length.) 
             */
    
            int PosnInCommon = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (c == s1.charAt(i) && s1.charAt(i) == s2.charAt(i)) {  // char is of primitive type, so == operator applies.
                    PosnInCommon += 1;
                }
            }
            return PosnInCommon;
         }
     
         
    
    
        /*
         * TASK 2: Implement this method
         * 
         * isValidGuess -  takes an arbitrary string guess and returns true
         * if it is a valid guess for Wordle, and false otherwise
         */
        public static boolean isValidGuess(String guess) {
            if (guess.length() == 5) {
                if (isAlpha(guess) == true) {
                    return true;
                } else {
                    System.out.println("Your guess must only contain letters of the alphabet.");
                    return false;
                }
            } else {
                System.out.println("Your guess must be 5 letters long.");
                return false;
            }
            
        }
    
    
        /**** ADD YOUR METHOD FOR TASKS 3 and 5 HERE. ****/
        public static boolean processGuess(String guess, String mystery) {
            /*
             * The method should process the input string guess and:
             * a) provide feedback to the user about how guess compares to mystery
             *    by printing the appropriate sequence of characters
             * b) return true if guess is equal to mystery and false otherwise.
             */
        
            String feedback = " " + " ";
            int numPerfectMatch = 0;
            int eachNumTimes = 0;  // each guess's character's occurrence in mystery
            int eachSamePosn = 0;


            //processGuess("loyal", "towel")
            
            for (int i = 0; i < guess.length(); i++) {
                char eachChar = guess.charAt(i);
                if (includes(mystery, eachChar) == true) {
                    eachNumTimes = numOccur(eachChar, mystery);
                    eachSamePosn = numInSamePosn(eachChar, guess, mystery);
                    if (eachNumTimes > eachSamePosn) {
                        if (eachChar == mystery.charAt(i)) {
                            feedback += "" + eachChar + " ";
                            numPerfectMatch += 1;
                        } else {
                            if (numOccur(eachChar, guess.substring(0,i)) >= eachNumTimes) {
                                // System.out.println("the third and fourth n should hit here");
                                feedback += "_ ";

                            } else {
                                feedback += "[" + eachChar + "] ";
                            }
                        }
                    } else {
                        if (eachChar == mystery.charAt(i)) {
                            feedback += "" + eachChar + " ";
                            numPerfectMatch += 1;
                        } else {
                            feedback += "_ ";
                        }  
                    }
                } else {
                    feedback += "_ ";
                }
            }
            System.out.println(feedback);
            System.out.println();
    
            if (numPerfectMatch == 5) {
                return true;
            } return false;
        }
    
    
        
        public static void main(String[] args) {
            Scanner console = new Scanner(System.in);
            
            printWelcome();
    
            // Create the WordList object for the collection of possible words.
            WordList words= initWordList(args);
    
            // Choose one of the words as the mystery word.
            String mystery = words.getRandomWord();
            
            /*** TASK 4: Implement the rest of the main method below. ***/
    
            int win = 0;
    
            for (int i = 1; i <= 6; i++) {
                String myGuess = readGuess(i, console);
                boolean validOrNot = processGuess(myGuess, mystery);
                if (validOrNot == true) {
                    System.out.println("Congrats! You guessed it!");
                    win += 1;
                    break;
                } 
            }
    
            if (win != 1) {
                System.out.println("Sorry! Better luck next time!");
                System.out.println("The word was " + mystery + ".");
            }
    
            console.close();
        }
    }

