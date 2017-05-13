package io.zipcoder.zealotscasino;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer implements CardDealer {
    private Deck deck;
    private Hand hand;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

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
        if (deck.getDeckQue().size() == 0) {
            deck.buildDeck();
        }
        //get bet
        try {
            player.makeBet(getDoubleInput("Place a bet"));
        } catch (IllegalArgumentException e) {
            UserInput.display("Insufficient Funds.");
            play(player);
            return;
        } catch (SecurityException e) {
            Bet.displayMinimumBet();
            play(player);
            return;
        }

        //deals hand, compares cgiards, returns win/lose/tie, player discards hand
        String outcome = playRound(player);
        player.getHand().remove(0);

        //collect payout, lose bet, or continue playing if tie
        processDeterminedOutcome(outcome, player);
        if (player.getWallet() < player.getMinimumBet()) {
            UserInput.display("Got $20? Nah you broke.");
            return;
        }
        askPlayAgain(player);
        Hand hand = new Hand();
        player.setHand(hand);
    }

    public void processTie(Player player) {
        String choice = getStringInput("Bet again? (Push  'Y' to double bet, any other key to surrender and receive half of bet");
        if (choice.equalsIgnoreCase("Y")) {
            try {
                //collect bet and deal another card
                player.makeBet(player.getBet().getBetValue());
                //deals hand, compares cards, returns win/lose/tie
                String outcome = playRound(player);
                processTieOutcome(outcome, player);
            } catch (IllegalArgumentException e) {
                UserInput.display("Unable to double bet due to insufficient funds.");
                pay(player, player.getBet().getBetValue() / 2);
                UserInput.display("You receive half of your original bet (" + player.getBet().getBetValue() / 2 + "\n" + player.printWallet() + "\n");
            }

        } else {
            //player gets half their original bet back
            pay(player, player.getBet().getBetValue() / 2);
            UserInput.display("You receive half of your original bet (" + player.getBet().getBetValue() / 2 + "\n" + player.printWallet() + "\n");
        }
    }

    public void processTieOutcome(String outcome, Player player) {
        if (outcome.equals("win")) {
            pay(player, player.getBet().getBetValue() * 3);
            UserInput.display("Your card is higher! You win your original bet!" + "\n" + player.printWallet() + "\n");
        } else if (outcome.equals("tie")) {
            pay(player, player.getBet().getBetValue() * 4);
            UserInput.display("It's a tie! You win (double total bet)!" + "\n" + player.printWallet() + "\n");
        } else UserInput.display("Dealer Wins!\nWallet : " + player.getWallet() + "\n");
    }


    public void askPlayAgain(Player player) {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit war)");
        if (choice.equalsIgnoreCase("Y")) play(player);
        else UserInput.display("Thanks for playing!\n\n");
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

    public void processDeterminedOutcome(String outcome, Player player) {
        switch (outcome) {
            case "win":
                pay(player, player.getBet().getBetValue() * 2);
                UserInput.display("Your card is higher! You win!" + "\n" + player.printWallet() + "\n");
                break;
            case "lose":
                UserInput.display("Dealer wins!" + "\n" + player.printWallet() + "\n");
                break;
            case "tie":
                processTie(player);
                break;
        }
    }

    public String playRound(Player player) {
        //deal cards
        dealHandTo(player);
        UserInput.display("Your card : " + player.getHand().getCards().get(0));

        Card dealerCard = deck.surrenderCard();
        hand.receiveCard(dealerCard);
        UserInput.display("Dealer's card : " + hand.getCards().get(0) + "\n");

        //compare cards
        int dealerCardValue = evaluateCardValue(hand.getCards().get(0));
        int playerCardValue = evaluateCardValue(player.getHand().getCards().get(0));

        //determine winner
        return determineOutcome(playerCardValue, dealerCardValue);
    }

}
