package labs_examples.objects_classes_methods.labs.oop.C_blackjack;

import java.util.Scanner;

public class BlackjackController {
    /**
     * This method is the controller of the Blackjack program.
     *
     * @param args The controller will take in the playBlackJack method, which ill be used to run
     *             the Blackjack program.
     */
    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController();
        controller.playBlackJack();

    }

    /**
     * This method is used to play a game of Blackjack between a user player and a computer player.
     */
    public void playBlackJack() {
        // This is where the game begins, and the player inputs their name
        System.out.println("Hello player, please enter your name.");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.next();

        // user's Player object and Computer's Player object are created
        Player user = new Player(playerName);
        Player computer = new Player("Computer");
        // setting booleans for playAgain and firstGame to true
        boolean playAgain = true;
        boolean firstGame = true;

        // this nested while loop handles the hands, bets, and play of the
        // players during the initial and subsequent games
        while (playAgain) {
            // user and computer are dealt hands for game after initial game
            user.getHand().newHand();
            computer.getHand().newHand();
            // user is prompted for their bet
            handleBets(user, firstGame);
            // cont booleans set to true to allow the player and user to continue until neither want cards
            user.cont = true;
            computer.cont = true;

            // initial deck of cards is created
            Deck deck = new Deck();
            // The initial game starts and initial cards are dealt
            System.out.println("Let the game begin.");
            dealInitialCards(deck, user, computer);

            // inner while loop that checks the cont boolean of the players to see if they continue
            while (user.cont || computer.cont) {
                // user is prompted to get more cards or stay
                promptUserPlay(deck, user, computer);
                // computer ai is checked to see if computer wants more cards
                promptComputerPlay(deck, user, computer);

            }
            // the winner of the game is determined
            determineWinner(user, computer);
            // firstGame boolean set to false after first game has been played
            firstGame = false;
            // playAgain boolean is set to ask the player how they want to continue
            playAgain = promptPlayAgain(user);
        }


    }

    /**
     * This method prompts the player to see if they would like to play again.
     *
     * @param user This is the user being prompted.
     * @return boolean true if the user wants to play again, false if the user is out of money
     * or does not want to play again.
     */
    private boolean promptPlayAgain(Player user) {
        Scanner scanner = new Scanner(System.in);

        // checks to see if the player has enough cash to keep playing
        if (user.getPotValue() == 0) {
            System.out.println("You have lost all your cash, the game is over!");
            return false;
        }

        // prompts the user to play again
        System.out.println("Would you like to play again? y/n");

        String response = scanner.next();

        // p
        if (response.equalsIgnoreCase("y")) {
            user.printCurrentPot();
            return true;
        } else {
            System.out.println("Thank you for playing, good bye!");
            return false;
        }
    }

    /**
     * This method is used to prompt the computer player's actions based on the initial cards dealt during a game.
     *
     * @param deck     This is a collection of playing cards.
     * @param computer This is the computer player.
     * @param user     This is the user player.
     */
    private void promptComputerPlay(Deck deck, Player computer, Player user) {
        if (computer.cont) {
            if (computer.computerAI()) {
                deck.deal(computer);
                computer.printHand();
                if (computer.getHand().bust()) {
                    System.out.println("The computer has busted!");
                    user.cont = false;
                    computer.cont = false;
                    computer.getHand().busted = true;

                }

            } else {
                computer.cont = false;
            }
        }
    }

    /**
     * This method is used to prompt the next play of the user after the initial cards have been dealt.
     *
     * @param deck     This is a collection of playing cards.
     * @param user     This is the user player.
     * @param computer This is the computer player.
     */
    private void promptUserPlay(Deck deck, Player user, Player computer) {
        Scanner scanner = new Scanner(System.in);
        if (user.cont) {
            System.out.println("Would you like another card? y/n");

            String response = scanner.next();

            if (response.equalsIgnoreCase("y")) {
                deck.deal(user);
                user.printHand();
                if (user.getHand().bust()) {
                    System.out.println("You have busted!");
                    user.cont = false;
                    computer.cont = false;
                    user.getHand().busted = true;

                }
            } else {
                user.cont = false;
            }
        }
    }

    /**
     * This method is used to deal the first two cards of the game to the user and the computer.
     *
     * @param deck     This is a collection of playing cards.
     * @param user     This is the user player.
     * @param computer This is the computer player.
     */
    private void dealInitialCards(Deck deck, Player user, Player computer) {
        deck.deal(user);
        deck.deal(computer);
        deck.deal(user);
        deck.deal(computer);

        user.printHand();
        computer.printHand();
    }

    /**
     * This method is ued to set the initial starting cash pot of the game,
     * as well as check the bet of the player during each hand of Blackjack.
     *
     * @param user      This is the user player.
     * @param firstGame This is the boolean that signals the first game of Blackjack.
     */
    private void handleBets(Player user, boolean firstGame) {
        Scanner scanner = new Scanner(System.in);

        if (firstGame) {
            System.out.println("How much money would you like to start with?");
            int potVal = scanner.nextInt();
            user.setPotValue(potVal);
        }

        boolean validInput = true;
        do {
            System.out.println("How much would you like to bet?");
            int betVal = scanner.nextInt();

            if (betVal <= user.getPotValue()) {
                user.setBetValue(betVal);
                validInput = true;
            } else {
                System.out.println("Your bet is too high, please bet less than your pot value!");
                validInput = false;
            }
        } while (validInput == false);
    }


    /**
     * This method is used to determine the winner of the Blackjack game.
     *
     * @param user     This is the user player.
     * @param computer This is the computer player.
     */
    public void determineWinner(Player user, Player computer) {
        if (user.getHand().busted && !computer.getHand().bust()) {
            System.out.println("The computer has won!");
            user.loseBet();

        } else if (computer.getHand().busted && !user.getHand().bust()) {
            System.out.println("You have won!");
            user.winBet();
            return;

        } else if (computer.getHand().busted && user.getHand().busted) {
            System.out.println("You and the computer have busted, you both lose!");
            user.loseBet();
            return;

        } else if (user.getHand().getScore() > computer.getHand().getScore() && !user.getHand().bust()) {
            System.out.println("You have won!");
            user.winBet();
            return;

        } else if (user.getHand().getScore() < computer.getHand().getScore() && !computer.getHand().bust()) {
            System.out.println("The computer has won!");
            user.loseBet();
            return;

        } else if (user.getHand().getScore() == computer.getHand().getScore()) {
            System.out.println("It's a stalemate!");
            user.staleBet();
            return;

        }
    }
}


