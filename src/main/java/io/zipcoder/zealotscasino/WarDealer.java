
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
        deck.buildDeck();
    }

    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void play(Player player) {

        Bet bet = new Bet();

        //replace with a method that checks if deck is empty
        if (deck.getDeckQue().size() == 0) {
            deck.buildDeck();
        }

        Bet.displayMinimumBet();
        double betValue = UserInput.getDoubleInput("Place a bet.");
        bet.makeBet(betValue, player);
        setBet(bet);

        Card playersCard = deck.surrenderCard();
        Card dealersCard = deck.surrenderCard();

        String outcome = playRound(playersCard, dealersCard);

        double winnings = processDeterminedOutcome(outcome);
        if (outcome.equals("tie")) {
            UserInput.display("You tied!");
            String tieChoice = getStringInput("Bet again? (Push  'Y' to double bet, any other key to surrender and receive half of bet");
            if (tieChoice.equalsIgnoreCase("y")) {
                Card playersTieCard = deck.surrenderCard();
                Card dealersTieCard = deck.surrenderCard();
                String tieOutcome = playRound(playersTieCard, dealersTieCard);
                winnings = processTieOutcome(tieOutcome);
            } else {
                winnings = betValue / 2;
            }
        }
        if(outcome.equals("win")){
            UserInput.display("You won!");
        }
        else if(outcome.equals("lose")){
            UserInput.display("Dealer wins! womp womp");
        }
        pay(player, winnings);
        UserInput.display(player.printWallet());

        askPlayAgain(player);
    }

    @Override
    public Bet getBet() {
        return null;
    }


    public double processTieOutcome(String outcome) {

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


    public void askPlayAgain(Player player) {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit war)");
        if (choice.equalsIgnoreCase("Y")) play(player);
        else UserInput.display("Thanks for playing!\n\n");
    }



    public void setBet(Bet bet) {
        this.bet = bet;
    }


    //DONE


    public int evaluateCardValue(Card theCard) {
        return Card.CardValue.valueOf(theCard.getFaceValue()).ordinal() + 2;
    }
    public double processDeterminedOutcome(String outcome) {

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

    public String playRound(Card playersCard, Card dealersCard) {
        UserInput.display("Your Card: " + playersCard);
        UserInput.display("Dealer's Card: " + dealersCard);
        return determineOutcome(evaluateCardValue(playersCard), evaluateCardValue(dealersCard));
    }

    public String determineOutcome(int playerCardValue, int dealerCardValue) {
        if (playerCardValue > dealerCardValue) {
            return "win";
        } else if (playerCardValue < dealerCardValue) {
            return "lose";
        } else return "tie";
    }

}