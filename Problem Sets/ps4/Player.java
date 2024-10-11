/*
 * Player.java
 * 
 * Computer Science 112, Boston University
 * 
 * A blueprint for objects that represent a single CardMatch player
 * 
 * Completed by Yufeng Song (jyfsong@bu.edu)
 */

import java.util.*;

public class Player {
    /* the player's name */
    private String name;

    /* an array to hold the cards in the player's hand */
    private Card[] hand;

    /* how many cards are currently in the player's hand */
    private int numCards;

    /* 
     * a constructor that takes a single parameter for the name of the player.
     * It should initialize all of the fields, 
     * create the array that will store the cards,
     * and make the collection big enough 
     * to store the maximum number of cards in a given hand (10).
     */
    public Player(String namePlayer) {
        if (namePlayer.equals(null)) {
            throw new IllegalArgumentException();
        }
        this.name = namePlayer;
        this.hand = new Card[CardMatch.MAX_CARDS];
        this.numCards = 0;
    }

    /* an accessor named getName that returns the player’s name */
    public String getName() {
        return this.name;
    }

    /* 
     * an accessor named getNumCards 
     * that returns the current number of cards in the player’s hand.
     */
    public int getNumCards() {
        return this.numCards;
    }

    /* a toString method that just returns the player’s name. */
    public String toString() {
        return this.name;
    }

    /* 
     * a mutator named addCard that takes a Card object as a parameter 
     * and adds the specified card to the player’s hand,
     * filling the array from left to right.
     * 
     * It should throw an IllegalArgumentException, if the parameter is null,
     * or if the player already has the maximum number of cards.
     */
    
    public void addCard(Card newCard) {  
        /* if a non-static method (except constructor) doesn't need to return, make sure to put a void */
        if (newCard == null || this.numCards == this.hand.length) {  // be sure to use length, not MAX_NUM
            throw new IllegalArgumentException();  
            // when to add sth in to an array, make sure to check for null
        }
        this.hand[this.numCards] = newCard;
        this.numCards++;
        // for (int i = 0; i < this.hand.length; i++) {  
        //     if (this.hand[i] == null) {
        //         this.hand[i] = newCard;
        //         break;
        //     }
        // }
    }

    /*
     * an accessor named getCard that takes an integer index as a parameter
     * and returns the Card at the specified position in the player’s hand,
     * without actually removing the card from the hand.
     * 
     * If the specified index does not correspond to one of the cards in the hand,
     * the method should throw an IllegalArgumentException.
     */
    public Card getCard(int indexCard) {
        if (indexCard < 0 || indexCard >= this.numCards) {
            throw new IllegalArgumentException();
        }
        return this.hand[indexCard];
    }

    /* 
     * an accessor method named getHandValue 
     * that computes and returns the total value of the player’s current hand
     * (i.e., the sum of the values of the individual cards, plus an additional 20-point penalty
     * if the hand has the maximum number of cards.)
     * 
     * Use the class constants given in CardMatch.java for the maximum number of cards
     * and for the penalty associated with having that many cards.
     */
    public int getHandValue() {
        int totalScore = 0;
        for (int i = 0; i < this.numCards; i++) {
            totalScore += this.hand[i].getValue();
        }
        if (this.numCards == CardMatch.MAX_CARDS) {
            totalScore += CardMatch.MAX_CARDS_PENALTY;
        }
        return totalScore;
    }

    /*
     * an accessor method named displayHand 
     * that prints the current contents of the player’s hand, 
     * preceded by a heading that includes the player’s name. 
     * Each card should be printed on a separate line, 
     * preceded by the index of its position in the hand.
     * 
     * Note that the spacing matters.
     */
    public void displayHand() {
        System.out.println(this.name + "'s hand:");
        for (int i = 0; i < this.numCards; i++) {
            System.out.println("  " + i + ": " + this.hand[i].toString());
        }
    }

    /*
     * a mutator method named removeCard that takes an integer index as a parameter 
     * and both removes and returns the Card at that position of the player’s hand. 
     * If the specified index does not correspond to one of the cards in the hand, 
     * the method should throw an IndexOutOfBoundsException.
     * 
     * If the card being removed is NOT the rightmost card in the hand,
     * the rightmost card must be moved into the position of the card that is being removed.
     */
    public Card removeCard(int indexCard) {
        if (indexCard < 0 || indexCard >= this.numCards) {
            throw new IllegalArgumentException();
        }
        
        Card cardToRemove = this.hand[indexCard];

        if (indexCard == this.numCards - 1) {
            this.hand[indexCard] = null;
            this.numCards--;
            return cardToRemove;
        } 
        
        this.hand[indexCard] = this.hand[this.numCards - 1];
        this.numCards--;
        return cardToRemove;
    }

    /*
     * an accessor method named getPlay 
     * that determines and returns the number corresponding to the player’s next play:
     * either -1 if the player wants to draw a card, 
     * or the number/index of the card that the player wants to discard from his/her hand.
     * 
     * The method should take two parameters: 
     * a Scanner object that can be used to read from the console, 
     * and a Card object representing the card that is currently at the top of the discard pile.
     * 
     * If the number entered is invalid (i.e., if it is neither -1 nor an index of one of the cards in the hand), 
     * the method should continue asking for a new value until the player enters a valid one.
     * The player never enters a non-integer.
     * Because this version of the method is for a human player, 
     * it can ignore the second parameter (the card at the top of the discard pile).
     */
    public int getPlay(Scanner console, Card atopDiscardPile) {
        while (true) {
            System.out.print(this.name + ": " + "number of card to play (-1 to draw)? ");
            
            int indexCardToDiscard = console.nextInt();
            if (indexCardToDiscard == -1) {
                return -1;
            } else if (indexCardToDiscard >= 0 && indexCardToDiscard < this.numCards) {
                return indexCardToDiscard;
            } 
        }

    }


}
