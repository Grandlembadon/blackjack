package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.Scanner;

/**
 * Constructs a Player, which holds a name, a hand, a pot value, a bet value,
 * and a boolean to check if the player wishes to continue.
 *
 */
public class Player {
    private String name;
    private Hand hand;
    private int potValue;
    private int betValue;
    boolean cont = true;


    public Player() {
        hand = new Hand();
    }

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getPotValue() {
        return potValue;
    }

    public void winBet() {
        potValue = (betValue * 2) + potValue;
    }

    public void loseBet() {
        potValue = potValue - betValue;
    }

    public void staleBet(){
        potValue = potValue + betValue;
    }

    public void setPotValue(int potValue) {
        this.potValue = potValue;
    }

    public void printCurrentPot(){
        System.out.println("You currently have $" + potValue + " left in your cash pot.");
    }

    /**
     * Method that is used to allow the computer player the ability to know when to hit and stay.
     *
     * @return returns true if the score of the player's hand is less than 16.
     */
    public boolean computerAI() {
        if (hand.getScore() < 16) {
            return true;
        } else {
            System.out.println("The computer will stay.");
            return false;
        }
    }

    public void printHand() {
        System.out.println(name + " has " + hand.toString() + " and their score is " + hand.getScore());
    }


    public void setBetValue(int betValue) {
        this.betValue = betValue;
    }

}

