package io.zipcoder.zealotscasino;

import java.text.NumberFormat;

/**
 * Created by luisgarcia on 5/9/17.
 */
public class Player {

    private double wallet;
    //public static final int MINIMUM_BUYIN = 20;

    public Player() {
        wallet = 0;
    }

    public Player(double initialWallet){
        if(initialWallet > Bet.MINIMUM_BET){
            this.wallet = initialWallet;
        }
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void collectWinnings(double winnings) {
        wallet += winnings;
    }

    public String printWallet(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String walletString = formatter.format(wallet);
        return ("Wallet: " + walletString);
    }
 }
