package io.zipcoder.zealotscasino;

import java.text.NumberFormat;

/**
 * Created by luisgarcia on 5/9/17.
 */
public class Player {
    private Bet bet;
    private double wallet;
    private Hand hand;
    private final static double MINIMUM_BET = 20;

    public static double getMinimumBet() {
        return MINIMUM_BET;
    }

    public Player() {
        hand = new Hand();
        bet = new Bet();
    }

    public Bet getBet() {
        return bet;
    }

    public void makeBet(double bet) {
        if (bet > wallet) {
            throw new IllegalArgumentException("Insufficient Funds");
        }
        if (bet < MINIMUM_BET){
            throw new SecurityException("Minimum bet not achieved");
        }
        this.bet.makeBet(bet);
        this.wallet = wallet - bet;
    }

    public double getWallet() {
        return wallet;
    }

    public void initializeWallet(double initialWallet) {
        if(initialWallet < MINIMUM_BET){
            throw new IllegalArgumentException("Not enough money to play");
        }
        this.wallet = initialWallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void collectWinnings(double winnings) {
        wallet += winnings;
    }

    public String printWallet(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String walletString = formatter.format(wallet);
        return "Wallet: " + walletString;
    }

    public boolean canMakeBet() {
        if (bet.betValue() > wallet || bet.betValue() < MINIMUM_BET) {
            return false;
        }
        return true;
    }
}
