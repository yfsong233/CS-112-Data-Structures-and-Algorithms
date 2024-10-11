/* 
 * Lab1 - Pr2 (User Input && Conditional Execution)
 * 
 * name: Yufeng Song
 * email: jyfsong@bu.edu
 * 
 */

import java.util.*;

public class Greeting {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);   // the constructor's name should be new Scanner
        System.out.print("Please enter your first name: ");
        String name = console.next();

        System.out.println("Hello " + name + ", Welcome to CS112!!!");
        System.out.print("How old are you " + name + " ? ");

        int age = console.nextInt(); // an error: Scanner age = new Scanner(System.in)
        
        String insult = "Wow " + name + "! " ;
    
        if (age <= 0) {
            insult += "You are an idiot who does not pay attention to directions!";
        } else if (age <= 10) {
            insult += "You are sweet!";
        } else if (age <= 17) {  // beaware that a semicolon CANNOT follow an if/else if/else statement 
            insult += "You are a dweeb!";
        } else if (age <= 20) {
            insult += "You are counting down to legal age!";
        } else if (age == 21) {
            insult += "You just made it to the legal age!";
        } else if (age <= 29) {
            insult += "You are counting down to 30!";
        } else if (age <= 40) {
            insult += "You are a suffering adult!";
        } else if (age < 50) {
            insult += "You are a miserable adult!";
        } else {
            insult += "You are speechless!";
        }

        System.out.println(insult);
        
    }

}
