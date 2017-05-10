package io.zipcoder.zealotscasino;

import java.util.ArrayList;

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

    public void determineDealerHandValue(int handValue) {
        this.dealerHandValue = handValue;
    }

    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public void determinePlayerHandValue(int playerHandValue) {
        this.playerHandValue = playerHandValue;
    }

    public int examineHandValue(Hand hand) {
        int handValue = 0;
        for(Card card: hand.getCards()) {
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
    public void takeTurn() {}

    @Override
    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }


    public void checkIfHit(){} // Test to see if handValue DIDN'T increase

    public void takeHit(Card card){} // Test to see if handValue DID increase

    public boolean checkIfPlayerHit(){ return false; } // Test to see if




}
