package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by denniskalaygian on 5/10/17.
 */
public class BlackJackDealer implements Dealer {

    private boolean gameRunning;
    private double insuranceValue;
    private Bet bet;

    private BlackJackHand playerHand;
    private BlackJackHand dealerHand;
    private Deck deck;

    public BlackJackDealer() {
        deck = new Deck();
        bet = new Bet();
        dealerHand = new BlackJackHand();
        playerHand = new BlackJackHand();
        deck.buildDeck();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    @Override
    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public BlackJackHand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(BlackJackHand playerHand) {
        this.playerHand = playerHand;
    }

    public BlackJackHand getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(BlackJackHand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void play(Player player) {
        setGameRunning(true);
        while(gameRunning){
            if(checkIfPlayerIsBroke(player)){
                tellPlayerToLeave(player);
                break;
            }
            displayPlayerWallet(player);
            takeTurn(player);
            String playAgain = UserInput.getStringInput("Play again? Yes / No");
            if((playAgain.equalsIgnoreCase("yes"))){
                setGameRunning(true);
                deck.buildDeck();
            }
            playerHand.remove();
        }
    }

    public boolean checkIfPlayerIsBroke(Player player){
        if(player.getWallet() < 20){
            return true;
        }
        return false;
    }

    public void tellPlayerToLeave(Player player){
        UserInput.display("You only have $" + player.getWallet() + ", you should probably save that money for the bus.");
    }

    public void takeTurn(Player player) {
        protectedBetProcess(player);
        initializeHands();
        buildPlayerHand();
        if(checkIfSplit(player)){
            split(player);
        }else {
            assertBlackJack(player);
            if (playerHand.getHandValue() != 21) {
                buildDealerHand();
                if(dealerHand.getCards().get(0).getFaceValue().equals("ACE")){
                    protectedInsuranceRequest(player);
                }
                hitProcess(player);
                checkIfInsurancePays(player);
                checkIfDealerHit();
                evaluateResult(player);
            }
        }
    }

    public void checkIfInsurancePays(Player player){
        if(dealerHand.getHandValue() == 21){
            player.setWallet(player.getWallet() + insuranceValue);
        }
    }

    private void protectedBetProcess(Player player){
        while(true) {
            try {
                bet.makeBet(UserInput.getDoubleInput("Place your bet! (Minimum $20.00) "), player);
                break;
            } catch (Exception e) {
                UserInput.display("Enter a valid number. ");
            }
        }
    }

    public void buildPlayerHand(){
        dealHandToPlayer();
        playerHand.updateHandValue();
    }

    public void buildDealerHand(){
        dealHandToDealer();
        dealerHand.updateHandValue();
    }

    public void initializeHands() {
        playerHand.remove();
        dealerHand.remove();
        playerHand = new BlackJackHand();
        dealerHand = new BlackJackHand();
        playerHand.resetHandValue();
        dealerHand.resetHandValue();
    }

    public void dealCardToPlayer() {
        Card card = deck.surrenderCard();
        playerHand.receiveCard(card);
        playerHand.updateHandValue();
    }

    public void dealCardToDealer() {
        Card card = deck.surrenderCard();
        dealerHand.receiveCard(card);
        dealerHand.updateHandValue();
    }

    public void dealHandToPlayer() {
        for (int i = 0; i < 2; i++) {
            dealCardToPlayer();
        }
        userDisplayHand();
    }

    public void dealHandToDealer() {
        for(int i = 0; i < 2; i++){
            dealCardToDealer();
        }
        displayDealerCardUp();
    }

    @Override
    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }

    public void takeHit(){
        dealCardToPlayer();
        userDisplayHand();
    }

    private void userDisplayHand() {
        StringBuilder outPut = new StringBuilder(1000);
        ArrayList<Card> cards = playerHand.getCards();
        for (Card card : cards) {
            outPut.append(card);
            outPut.append("\n");
        }
        outPut.append("\nTotal Player value: " + playerHand.getHandValue());
        UserInput.display(outPut);
    }

    private void displayDealerCardUp(){
        UserInput.display("Exposed card of dealer: " + dealerHand.getCards().get(0));
    }

    private void displayLoseGame() {
        UserInput.display("Busted!");
    }

    private void displayBlackJack() {
        UserInput.display("BlackJack!");
    }

    public void displayPlayerWallet(Player player){
        UserInput.display("You have $" + player.getWallet() + " remaining.");
    }

    private boolean checkIfPlayerHit(){
        UserInput in = new UserInput();
        String hit = in.getStringInput("If you would like another card, enter HIT ");
        if(hit.equalsIgnoreCase("hit")){
            return true;
        }
        return false;
    }

    public void decideWinner(Player player){
        if((dealerHand.getHandValue() - playerHand.getHandValue()) > 0 && dealerHand.getHandValue() < 22){
            displayDealerWins();
        } else if(dealerHand.getHandValue() == playerHand.getHandValue()){
            executeTie(player);
        } else{
            executePlayerWins(player);
        }
    }

    public void displayDealerWins(){
        UserInput.display("Dealer wins with a hand value of: " + dealerHand.getHandValue());
    }

    public void executeTie(Player player){
        UserInput.display("Tie!");
        pay(player, bet.getBetValue());
    }

    public void executePlayerWins(Player player){
        UserInput.display("Player wins, dealer hand was " + dealerHand.getHandValue());
        pay(player, bet.getBetValue() * 2);
    }

    public boolean checkStatus(Player player, double bet){
        boolean bust = playerHand.checkIfBust();
        boolean blackJack = playerHand.checkIfBlackJack();
        if(bust){
            displayLoseGame();
            setGameRunning(false);
            return false;
        }
        if(blackJack){
            executePlayerWins(player);
        }
        boolean hit = checkIfPlayerHit();
        return hit;
    }

    public void assertBlackJack(Player player) {
        if (playerHand.getHandValue() == 21) {
            displayBlackJack();
            pay(player, bet.getBetValue() * 3);
            setGameRunning(false);
        }
    }

    private boolean requestInsuranceValue(){
        String answer = UserInput.getStringInput("Take insurance? Enter Yes if you would like to. " );
        if(answer.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }

    private void hitProcess(Player player){
        boolean hit = checkIfPlayerHit();
        while(hit){
            takeHit();
            hit = checkStatus(player, bet.getBetValue());
        }
    }

    public void evaluateResult(Player player){
        if(dealerHand.getHandValue() == 21){
            payPlayer(player);
        }else {
            checkIfDealerHit();
            payPlayer(player);
        }
    }

    public void checkIfDealerHit() {
        while(dealerHand.getHandValue() < 17) {
            dealCardToDealer();
        }
    }

    // We should definitely move this out
    private void payPlayer(Player player){
        if(dealerHand.getHandValue() == 21 && getInsuranceValue() != 0){
            UserInput.display("Dealer had BlackJack - good call! ");
            pay(player, insuranceValue);
        }
        if(gameRunning) {
            decideWinner(player);
            setGameRunning(false);
        }
    }

    private void protectedInsuranceRequest(Player player){
        boolean response = requestInsuranceValue();
        while(response){
            try {
                setInsuranceValue(UserInput.getDoubleInput("How much would you like to put on it?"));
                double postInsuranceWallet = player.getWallet() - getInsuranceValue();
                player.setWallet(postInsuranceWallet);
                break;
            }catch(Exception e){
                UserInput.display("Please enter a valid number.");
            }
        }
    }

    public boolean checkIfSplit(Player player){
        if(playerHand.getCards().get(0).getFaceValue().equals(playerHand.getCards().get(1).getFaceValue())){
            String splitDecision = UserInput.getStringInput("Type YES if you would like to split. ");
            if(splitDecision.equalsIgnoreCase("yes")){
                return true;
            }
            return false;
        }
        return false;
    }

    public void split(Player player){
        player.setWallet(player.getWallet() - bet.getBetValue());
        ArrayList<Card> cards = playerHand.getCards();
        for(Card card : cards){
            initializeHands();
            playerHand.receiveCard(card);
            dealCardToPlayer();
            assertBlackJack(player);
            if(playerHand.getHandValue() < 21) {
                buildDealerHand();
                hitProcess(player);
                evaluateResult(player);
                dealerHand.setHandValue(0);
            }
        }
    }
}