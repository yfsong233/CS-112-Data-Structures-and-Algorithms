/*
 * ComputerPlayer.java
 * 
 * Computer Science 112, Boston University
 * 
 * A blueprint for objects that represent a computer player
 * A subclass of Player, which inherits the fileds and methods of that Player
 * 
 * Completed by Yufeng Song (jyfsong@bu.edu)
 */

import java.util.*;

public class ComputerPlayer extends Player{

    /*
     * a constructor that takes the name of the player as a parameter 
     * and calls the constructor of the superclass to do the actual work of 
     * initializing the inherited fields.
     */
    public ComputerPlayer(String computerName) {
        super(computerName);
        // how to initialize the private fields in the superclass?
    }

    /*
     * a displayHand method that overrides the inherited version of that method. 
     * This version of the method should simply print the number of cards in the ComputerPlayer‘s hand.
     * 
     * Use the name given to the player when the object was created 
     * (which may not always be "the computer").
     * If there is only one card, use the word "card" instead of "cards".
     */
    public void displayHand() {
        System.out.println(this.getName() + "'s hand:");  
        // how to access the name of the computer player?
        System.out.print("  " + this.getNumCards());
        if (this.getNumCards() == 1) {
            System.out.print(" card");
        } else {
            System.out.print(" cards");
        }
        System.out.println();
    }
    
    /*
     * a getPlay method that overrides the inherited version of that method. 
     * This version of the method should figure out if the computer has a card 
     * that matches the card at the top of the discard pile 
     * (this card is passed in as the second parameter of the method). 
     * 
     * If the computer doesn’t have a matching card, the method should return -1 
     * so that the computer will end up drawing a card. 
     * If the computer does have one or more matching cards, 
     * the method should return the index of the card that should be played.
     * 
     * This method should take into account the values and/or colors of the cards 
     * when choosing between two or more matching cards.
     */
    public int getPlay(Scanner console, Card atopDiscardPile) {
        int indexMatch = -1;
        int highestValue = -1;
        // int numMatchColor = 0;
        // int maxNumMatchColor = 0;
        for (int i = 0; i < this.getNumCards(); i++) { // don't forget to add a bracket in a method call
            // if (this.getCard(i).matches(atopDiscardPile) == true) {
            //     if (this.getCard(i).getValue() > highestValue) {
            //         highestValue = this.getCard(i).getValue();
            //         indexMatch = i;
            //     }
            //     for (int j = 0; j < this.getNumCards(); j++) {
            //         if (this.getCard(j).getColor() == this.getCard(i).getColor()) {
            //             numMatchColor++;
            //         }
            //     }
            //     if (numMatchColor > maxNumMatchColor) {
            //         maxNumMatchColor = numMatchColor;
            //         numMatchColor = 0;
            //         indexMatch = i;
            //     } 
            // } 
            if (this.getCard(i).matches(atopDiscardPile) == true) {
                if (this.getCard(i).getValue() > highestValue) {
                    highestValue = this.getCard(i).getValue();
                    indexMatch = i;
                }
            }
        } 
        if (indexMatch == -1) {
            return -1;
        } return indexMatch;
        
    }



    
}
