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
    private double insuranceValue;

    private Hand dealerHand;
    private Deck deck;

    public BlackJackDealer() {
        deck = new Deck();
        deck.buildDeck();
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public void setDealerHand(Hand hand){
        this.dealerHand = hand;
    };

    public void initializeHands(Player player){
        Hand playerHand = new Hand();
        player.setHand(playerHand);
        Hand dealersHand = new Hand();
        setDealerHand(dealersHand);
        initializePlayerHandValue();
        initializeDealerHandValue();
    }

    public void initializeDealerHandValue() {
        dealerHandValue = 0;
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

    public void determineDealerHandValue(Hand hand){
        dealerHandValue = examineHandValue(hand);
    }

    public int examineHandValue(Hand hand) {
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

    private void dealCardToDealer() {
        Card card = deck.surrenderCard();
        Hand currentHand = dealerHand;
        currentHand.receiveCard(card);
        determineDealerHandValue(currentHand);
    }

    @Override
    public void dealHandTo(Player player) {
        for (int i = 0; i < 2; i++) dealCardTo(player);
        userDisplayHand(player);
    }

    private void dealHandToDealer() {
        for (int i = 0; i < 2; i++) dealCardToDealer();
        System.out.println("Exposed card of dealer: " + dealerHand.getCards().get(0));
        if(dealerHand.getCards().get(0).getFaceValue() == "ACE"){
            boolean response = requestInsuranceValue();
            if(response){
                setInsuranceValue(UserInput.getDoubleInput("How much would you like to put on it?"));
            }

        }
    }

    @Override
    public void play(Player player) {
        gameRunning = true;
        do {

            takeTurn(player);
            String playAgain = UserInput.getStringInput("Play again? Yes / No");
            if((playAgain.equalsIgnoreCase("yes"))){
                gameRunning = true;
                deck.buildDeck();
            }
        } while (gameRunning);

    }

    @Override
    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    private void takeHit(Player player){
        dealCardTo(player);
        userDisplayHand(player);
        }

    public void takeTurn(Player player) {
        initializeHands(player);
        dealHandTo(player);
        dealHandToDealer();
        determinePlayerHandValue(player.getHand());
        determineDealerHandValue(dealerHand);
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
        if(dealerHandValue < 17){
            dealCardToDealer();
        }else if(dealerHandValue == 21 && insuranceValue != 0){
            pay(player, insuranceValue);
        }
        if(gameRunning == true) {
            decideWinner(player, bet);
            gameRunning = false;
        }

    }

    private void userDisplayHand(Player player) {
        StringBuilder outPut = new StringBuilder(1000);
        ArrayList<Card> cards = player.getHand().getCards();
        for (Card card : cards) {
            outPut.append(card);
            outPut.append(" ");
        }
        outPut.append("\nTotal Player value: " + playerHandValue);
        System.out.println(outPut);
    }

    private boolean checkIfPlayerHit(){
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

    private void decideWinner(Player player, double bet){
        if((dealerHandValue - playerHandValue) > 0 && dealerHandValue < 22){
            System.out.println("Dealer wins with a hand value of: " + dealerHandValue);
        } else if(dealerHandValue == playerHandValue){
            System.out.println("Tie!");
            pay(player, bet);
        } else{
            System.out.println("Player wins, dealer hand was " + dealerHandValue);
            pay(player, bet*2);
        }
    }

    private boolean checkStatus(Player player, double bet){
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

    private boolean requestInsuranceValue(){
        String answer = UserInput.getStringInput("Take insurance? Enter Yes / No" );
        if(answer.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }
}
