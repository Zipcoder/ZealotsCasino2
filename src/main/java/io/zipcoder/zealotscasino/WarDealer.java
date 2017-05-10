package io.zipcoder.zealotscasino;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer implements CardDealer {
    private Deck deck;
    private Hand hand;

    public WarDealer() {
        deck = new Deck();
        deck.buildDeck();
    }

    public void dealCardTo(Player player) {
        Hand playersHand = player.getHand();
        Card cardDealt = deck.surrenderCard();
        playersHand.receiveCard(cardDealt);
    }

    public void dealHandTo(Player player) {
        dealCardTo(player);
    }

    public void takeTurn() {

    }

    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void play(Player player) {
        //get bet
        player.makeBet(getDoubleInput("Place a bet"));

        //deal cards
        dealHandTo(player);
        Card dealerCard = deck.surrenderCard();

        //compare cards
        int dealerCardValue = evaluateCardValue(dealerCard);
        int playerCardValue = evaluateCardValue(player.getHand().getCards().get(0));
        String outcome = determineOutcome(playerCardValue, dealerCardValue);


        //determine winner
        //collect payout or lose bet
        //if tie, player can quit or break tie
        //collect tie bet
        //burn 3 cards and deal
        //compare cards
        //determine winner
        //payout

    }

    public void processDeterminedOutcome(String outcome, Player player) {
        String choice = "";
        if (outcome.equals("win")) pay(player, player.getBet() * 2);
        else if (outcome.equals("tie")) {
            choice = getStringInput("Bet again? (Push  'Y' to double bet, 'N' to surrender and receive half of bet");
            //processTie
        } else {
            askPlayAgain(player);
        }

    }

    //public void processTie

    public void askPlayAgain(Player player) {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit");
        if (choice.equals("Y")) play(player);
    }


    public int evaluateCardValue(Card theCard) {
        return Card.CardValue.valueOf(theCard.getFaceValue()).ordinal() + 2;
    }

    public String determineOutcome(int playerCardValue, int dealerCardValue) {
        if (playerCardValue > dealerCardValue) {
            return "win";
        } else if (playerCardValue < dealerCardValue) {
            return "lose";
        } else return "tie";
    }
}
