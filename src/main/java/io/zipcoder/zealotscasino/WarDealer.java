package io.zipcoder.zealotscasino;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer {
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

        //replace with a method that checks if deck is empty
        if (deck.getDeckQue().size() == 0) {
            deck.buildDeck();
        }
//        //get bet
//        try {
//            player.makeBet(getDoubleInput("Place a bet"));
//        } catch (IllegalArgumentException e) {
//            UserInput.display("Insufficient Funds.");
//            play(player);
//            return;
//        } catch (SecurityException e) {
//            Bet.displayMinimumBet();
//            play(player);
//            return;
//        }
        Card playersCard = deck.surrenderCard();
        Card dealersCard = deck.surrenderCard();


        String outcome = playRound(playersCard, dealersCard);
        double winnings = processDeterminedOutcome(outcome);
        if(winnings == -1){
            String tieChoice = getStringInput("Bet again? (Push  'Y' to double bet, any other key to surrender and receive half of bet");
            processTie(player, tieChoice.toUpperCase());
        }
        else{
            pay(player, winnings);
        }
        askPlayAgain(player);

//        //collect payout, lose bet, or continue playing if tie
//        processDeterminedOutcome(outcome, player);
//        if (player.getWallet() < player.getMinimumBet()) {
//            UserInput.display("Got $20? Nah you broke.");
//            return;
//        }
//        askPlayAgain(player);
//        Hand hand = new Hand();
//        player.setHand(hand);
    }

    public void processTie(Player player, String tieChoice) {
        switch(tieChoice){
            case "Y":
                //double bet
                break;
            case "N":
                break;
            default:

        }
//        if (choice.equalsIgnoreCase("Y")) {
//            try {
//                //collect bet and deal another card
//                player.makeBet(player.getBet().getBetValue());
//                //deals hand, compares cards, returns win/lose/tie
//                String outcome = playRound(player);
//                processTieOutcome(outcome, player);
//            } catch (IllegalArgumentException e) {
//                UserInput.display("Unable to double bet due to insufficient funds.");
//                pay(player, player.getBet().getBetValue() / 2);
//                UserInput.display("You receive half of your original bet (" + player.getBet().getBetValue() / 2 + "\n" + player.printWallet() + "\n");
//            }
//
//        } else {
//            //player gets half their original bet back
//            pay(player, player.getBet().getBetValue() / 2);
//            UserInput.display("You receive half of your original bet (" + player.getBet().getBetValue() / 2 + "\n" + player.printWallet() + "\n");
//        }
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

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public String determineOutcome(int playerCardValue, int dealerCardValue) {
        if (playerCardValue > dealerCardValue) {
            return "win";
        } else if (playerCardValue < dealerCardValue) {
            return "lose";
        } else return "tie";
    }




    //DONE
    public double processDeterminedOutcome(String outcome) {
        switch (outcome) {
            case "win":
                return bet.getBetValue();
            case "lose":
                return 0;
            case "tie":
                return -1;
            default:
                return 22;
        }

    }

    public String playRound(Card playersCard, Card dealersCard) {
        UserInput.display("Your Card: " + playersCard);
        UserInput.display("Your Card: " + dealersCard);
        return determineOutcome(evaluateCardValue(playersCard), evaluateCardValue(dealersCard));
    }

}
