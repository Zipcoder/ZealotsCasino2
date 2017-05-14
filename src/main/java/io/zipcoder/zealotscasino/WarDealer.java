
package io.zipcoder.zealotscasino;

import static io.zipcoder.zealotscasino.UserInput.display;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer implements Dealer {
    private Deck deck;
    private Bet bet;

    public WarDealer() {
        deck = new Deck();
        bet = new Bet();
        deck.buildDeck();
    }

    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void play(Player player) {

        Bet bet = new Bet();
        boolean betValidation;

        checkIfDeckIsEmpty(deck);
        Bet.displayMinimumBet();
        do {
            betValidation = bet.makeBet(UserInput.getDoubleInput("Place a bet."), player);
        } while (!betValidation);
        setBet(bet);

        Card playersCard = deck.surrenderCard();
        Card dealersCard = deck.surrenderCard();

        String outcome = playRound(playersCard, dealersCard);

        double winnings = processDeterminedOutcome(outcome);
        if (outcome.equals("tie")) {
            String tieChoice = getStringInput("You tied! Bet again? (Push  'Y' to double bet, any other key to surrender and receive half of bet");
            if (tieChoice.equalsIgnoreCase("y")) {
                Card playersTieCard = deck.surrenderCard();
                Card dealersTieCard = deck.surrenderCard();
                winnings = processTieOutcome(playRound(playersTieCard, dealersTieCard));
            }
        }

        UserInput.display("YOU " + outcome.toUpperCase() + "!");
        pay(player, winnings);
        UserInput.display(player.printWallet());
        if (player.getWallet() >= Bet.MINIMUM_BET) {
            askPlayAgain(player);
        } else {
            UserInput.display("I suggest hitting Leon's Street Casino around the corner, you're too broke for us.");
        }
    }

    @Override
    public Bet getBet() {
        return null;
    }


    protected double processTieOutcome(String outcome) {

        switch (outcome) {
            case "win":
                return bet.getBetValue() * 2;
            case "lose":
                return -bet.getBetValue();
            case "tie":
                return bet.getBetValue() * 3;
            default:
                return 22;
        }
    }


    protected void askPlayAgain(Player player) {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit war)");
        if (choice.equalsIgnoreCase("Y")) play(player);
        else UserInput.display("Thanks for playing!\n\n");
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }


    protected int evaluateCardValue(Card theCard) {
        return Card.CardValue.valueOf(theCard.getFaceValue()).ordinal() + 2;
    }

    protected double processDeterminedOutcome(String outcome) {

        switch (outcome) {
            case "win":
                return bet.getBetValue() * 2;
            case "lose":
                return 0;
            case "tie":
                return bet.getBetValue() / 2;
            default:
                return 22;
        }

    }

    protected String playRound(Card playersCard, Card dealersCard) {
        UserInput.display("Your Card: " + playersCard);
        UserInput.display("Dealer's Card: " + dealersCard);
        return determineOutcome(evaluateCardValue(playersCard), evaluateCardValue(dealersCard));
    }

    protected String determineOutcome(int playerCardValue, int dealerCardValue) {
        if (playerCardValue > dealerCardValue) {
            return "win";
        } else if (playerCardValue < dealerCardValue) {
            return "lose";
        } else return "tie";
    }

    protected Deck getDeck() {
        return deck;
    }

    protected void checkIfDeckIsEmpty(Deck deck) {
        if (deck.getDeckQue().size() < 2) {
            deck.buildDeck();
        }
    }


}