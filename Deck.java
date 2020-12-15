package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the Constructor for the Deck Class.
 */
public class Deck {
    public static char[] suits = {'♠', '♦', '♥', '♣'};
    private Card[] deck = new Card[52];
    private ArrayList<Integer> usedCards = new ArrayList<Integer>();

    public Deck() {
        createDeck();
    }

    /**
     * This method is used to create a fully populated deck of cards with their suit and point value.
     *
     * @return deck
     */
    public void createDeck() {
        int cardCt = 0;
        for (char suit = 0; suit <= 3; suit++) {
            for (int i = 1; i <= 13; i++) {
                deck[cardCt] = new Card(suits[suit], i);
                cardCt++;

            }
        }
    }

    /**
     * This method is used to deal a random card from a deck of cards and remove it from the deck.
     *
     * @param player
     */
    public void deal(Player player) {
        Random r = new Random();
        int randomNum;

        do {
            randomNum = r.nextInt(52);
        } while (usedCards.contains(randomNum));

        usedCards.add(randomNum);
        player.getHand().getCards().add(deck[randomNum]);

    }
}

