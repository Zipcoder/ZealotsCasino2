package io.zipcoder.zealotscasino;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by andrewwong on 5/10/17.
 */
public class WarDealer implements CardDealer {
    private Deck deck;

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
        try{
            player.makeBet(getDoubleInput("Place a bet"));
        }catch(IllegalArgumentException e){
            System.out.println("Insufficient Funds.");
            player.makeBet(getDoubleInput("Place a bet"));
        }catch(SecurityException e){
            System.out.println("Minimum bet is $20.");
            player.makeBet(getDoubleInput("Place a bet"));
        }

        //deals hand, compares cards, returns win/lose/tie, player discards hand
        String outcome = playRound(player);
        player.getHand().remove(0);

        //collect payout, lose bet, or continue playing if tie
        processDeterminedOutcome(outcome, player);

    }

    public void processTie(Player player) {
        String choice = getStringInput("Bet again? (Push  'Y' to double bet, any other key to surrender and receive half of bet");
        if (choice.equalsIgnoreCase("Y")) {
            try{
                //collect bet and deal another card
                player.makeBet(player.getBet());
                //deals hand, compares cards, returns win/lose/tie
                String outcome = playRound(player);
                processTieRound(outcome, player);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to double bet due to insufficient funds.");
                pay(player, player.getBet() / 2);
                System.out.println("You receive half of your original bet (" + player.getBet() / 2 + ")\nWallet : " + player.getWallet() + "\n");
            }

        } else {
            //player gets half their original bet back
            pay(player, player.getBet() / 2);
            System.out.println("You receive half of your original bet (" + player.getBet() / 2 + ")\nWallet : " + player.getWallet() + "\n");
        }
    }

    public void processTieRound(String outcome, Player player) {
        if (outcome.equals("win")){
            pay(player, player.getBet() * 3);
            System.out.println("Your card is higher! You win your original bet!\nWallet : " + player.getWallet() + "\n");
        }
        else if (outcome.equals("tie")){
            pay(player, player.getBet() * 4);
            System.out.println("It's a tie! You win (double total bet)!\nWallet : " + player.getWallet());
        } else System.out.println("Dealer Wins!\nWallet : " + player.getWallet() + "\n");
    }


    public void askPlayAgain(Player player) {
        String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit war)");
        if (choice.equalsIgnoreCase("Y")) play(player);
        else System.out.println("Thanks for playing!\n\n");
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
        if (outcome.equals("win")){
            pay(player, player.getBet() * 2);
            System.out.println("Your card is higher! You win!\nWallet : " + player.getWallet() + "\n");
        }
        else if (outcome.equals("tie")) processTie(player);
        else {
            System.out.println("Dealer wins!\nWallet : " + player.getWallet() + "\n");
        }
        askPlayAgain(player);

    }

    public String playRound(Player player) {
        //deal cards
        dealHandTo(player);
        System.out.println("Your card : " + player.getHand().getCards().get(0));

        Card dealerCard = deck.surrenderCard();
        System.out.println("Dealer's card : " + dealerCard + "\n");

        //compare cards
        int dealerCardValue = evaluateCardValue(dealerCard);
        int playerCardValue = evaluateCardValue(player.getHand().getCards().get(0));

        //determine winner
        return determineOutcome(playerCardValue, dealerCardValue);
    }

}
