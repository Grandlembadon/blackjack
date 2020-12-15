package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

/**
 * Constructor for a Card, which holds a suit and an int.
 */
public class Card {
    private char suit;
    private int value;


    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    /**
     * This method gets the current point value of a player's hand.
     *
     * @return point value
     */
    public int getPointValue() {
        if (value <= 10)
            return value;
        else
            return 10;
    }

    /**
     * This method returns the face value of a card.
     *
     * @return face value.
     */
    public String getFaceValue() {
        if (value == 1)
            return "Ace";
        else if (value > 1 && value <= 10)
            return String.valueOf(value);
        else if (value == 11)
            return "Jack";
        else if (value == 12)
            return "Queen";
        else
            return "King";
    }

    @Override
    public String toString() {
        return String.valueOf(value) + suit;
    }
}


