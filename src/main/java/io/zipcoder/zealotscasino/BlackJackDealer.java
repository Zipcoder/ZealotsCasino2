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
    private boolean gameRunning;
    
    private Hand hand;
    private Deck deck;

    public BlackJackDealer() {
        deck = new Deck();
        deck.buildDeck();
    }

    public void initializeHands(Player player){
        Hand hand = new Hand();
        player.setHand(hand);
        initializePlayerHandValue();
        initializeDealerHandValue();
    }

    public void initializeDealerHandValue() {
        Random generator = new Random();
        dealerHandValue = generator.nextInt(7) + 14;
    }



    public void initializePlayerHandValue(){
        playerHandValue = 0;
    }

    private void displayLoseGame() {
        System.out.println("Busted!");
    }

    private void displayBlackJack() {
        System.out.println("BlackJack!");
    }

    public void determinePlayerHandValue(Hand hand) {
        playerHandValue = examineHandValue(hand);

    }

    private int examineHandValue(Hand hand) {
        int handValue = 0;
        for(Card card: hand.getCards()) {
            if(Card.CardValue.valueOf(card.getFaceValue()).ordinal() == 12){
                if(handValue > 10){
                    handValue++;
                }else{
                    handValue += 11;
                }
            }
            if(Card.CardValue.valueOf(card.getFaceValue()).ordinal() > 8 && Card.CardValue.valueOf(card.getFaceValue()).ordinal() < 12){
                handValue += 10;
            }else if(Card.CardValue.valueOf(card.getFaceValue()).ordinal() <= 8){
                handValue += Card.CardValue.valueOf(card.getFaceValue()).ordinal() + 2;
            }
        }
        for(Card card: hand.getCards()){
            if(Card.CardValue.valueOf(card.getFaceValue()).ordinal() == 12 && handValue > 21){
                handValue -= 10;
            }
        }
        return handValue;
    }


    @Override
    public void dealCardTo(Player player) {
        Card card = deck.surrenderCard();
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card);
        determinePlayerHandValue(currentHand);
    }

    @Override
    public void dealHandTo(Player player) {
        for (int i = 0; i < 2; i++) dealCardTo(player);
        userDisplayHand(player);
    }

    public void clearPlayerHand(Player player){
        for(int i = 0; i < player.getHand().getCards().size(); i++ ){
            player.getHand().remove(i);
        }
    }

    @Override
    public void play(Player player) {
        gameRunning = true;
        do {
            if(player.getWallet() < 20){
                System.out.println("You only have $" + player.getWallet() + " left. You'll need that for the bus.");
                break;
            }
            takeTurn(player);
            String playAgain = UserInput.getStringInput("Play again? Yes / No");
            if((playAgain.equalsIgnoreCase("yes"))){
                gameRunning = true;
            }

        } while (gameRunning);

    }

    @Override
    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void takeHit(Player player){
        dealCardTo(player);
        userDisplayHand(player);
        }

    public void takeTurn(Player player) {
        initializeHands(player);
        dealHandTo(player);
        determinePlayerHandValue(player.getHand());
        while(true) {
            try {
                player.makeBet(UserInput.getDoubleInput("Place your bet (Minimum $20.00) "));
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid number. ");
            }
        }
        double bet = player.getBet();
        boolean hit = checkIfPlayerHit();
        while(hit){
            takeHit(player);
            hit = checkStatus(player, bet);
        }
        if(gameRunning == true) {
            decideWinner(player, bet);
            gameRunning = false;
        }

    }

    public void userDisplayHand(Player player) {
        StringBuilder outPut = new StringBuilder(1000);
        ArrayList<Card> cards = player.getHand().getCards();
        for (Card card : cards) {
            outPut.append(card);
            outPut.append(" ");
        }
        outPut.append("\nTotal Player value: " + playerHandValue);
        System.out.println(outPut);
    }

    public boolean checkIfPlayerHit(){
        UserInput in = new UserInput();
        String hit = in.getStringInput("If you would like another card, enter HIT ");
        if(hit.equalsIgnoreCase("hit")){
            return true;
        }
        return false;
    }

    private boolean checkBust(){
        if(playerHandValue > 21){
            return true;
        }
        return false;
    }

    private boolean checkBlackJack(){
        if(playerHandValue == 21){
            return true;
        }
        return false;
    }

    public void decideWinner(Player player, double bet){ 
        if((dealerHandValue - playerHandValue) > 0){
            System.out.println("Dealer wins with a hand value of: " + dealerHandValue);
        } else if(dealerHandValue == playerHandValue){
            System.out.println("Tie!");
            pay(player, bet);
        } else{
            System.out.println("Player wins, dealer hand was " + dealerHandValue);
            pay(player, bet*2);
        }
    }

    public boolean checkStatus(Player player, double bet){
        boolean bust = checkBust();
        boolean blackJack = checkBlackJack();
        if(bust){
            displayLoseGame();
            gameRunning = false;
            return false;

        }
        if(blackJack){
            displayBlackJack();
            pay(player, bet*2);
            gameRunning = false;
            return false;

        }
        boolean hit = checkIfPlayerHit();

        return hit;
    }



}
