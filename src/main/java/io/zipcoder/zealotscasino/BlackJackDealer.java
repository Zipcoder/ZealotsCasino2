package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by denniskalaygian on 5/10/17.
 */
public class BlackJackDealer implements CardDealer {

    private int dealerHandValue;
    private int playerHandValue;

    private Deck deck;

    public BlackJackDealer() {
        deck = new Deck();
        deck.buildDeck();
    }

    public int getDealerHandValue() {
        return dealerHandValue;
    }

    public void setDealerHandValue() {
        Random generator = new Random();
        dealerHandValue = generator.nextInt(7) + 14;
    }

    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public void determinePlayerHandValue(Hand hand) {
        playerHandValue = examineHandValue(hand);
    }

    private int examineHandValue(Hand hand) {
        int handValue = 0;
        for(Card card: hand.getCards()) { // King Queen Jack worth 10
            if(Card.CardValue.valueOf(card.getFaceValue()).ordinal() == 14){
                if(handValue > 7){
                    handValue++;
                }
            }
            handValue += (Card.CardValue.valueOf(card.getFaceValue()).ordinal() + 2);
        }
        return handValue;
    }


    @Override
    public void dealCardTo(Player player) {
        Card card = deck.surrenderCard();
        player.getHand().receiveCard(card);
    }

    @Override
    public void dealHandTo(Player player) {
        for (int i = 0; i < 2; i++) dealCardTo(player);
    }

    @Override
    public void play(Player player) {
        do {
            takeTurn(player);
            String playAgain = UserInput.getStringInput("Play again? Yes / No");
            if(!(playAgain.equalsIgnoreCase("yes"))){
                break;
            }
        } while (true);

    }

    public void takeTurn(Player player) {
        deck.buildDeck();
        dealHandTo(player);
        // Display the hand
        determinePlayerHandValue(player.getHand());
        // Display the hand value
        takeHit(player);
    }

    @Override
    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void takeHit(Player player){
        boolean hit = checkIfPlayerHit();
        if(hit){
            dealCardTo(player);
        }
    }

    public boolean checkIfPlayerHit(){
        UserInput in = new UserInput();
        String hit = in.getStringInput("HIT / STAY");
        if(hit.equalsIgnoreCase("hit")){
            return true;
        }
        return false;
    }






}
