package io.zipcoder.zealotscasino;

import java.text.NumberFormat;

/**
 * Created by luisgarcia on 5/9/17.
 */
public class Player {

    private int wallet;
    public static final int MINIMUM_BUYIN = 20;

    public Player() {
        wallet = 0;
    }

    public Player(int initialWallet){
        if(initialWallet < MINIMUM_BUYIN){
            this.wallet = initialWallet;
        }
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void collectWinnings(int winnings) {
        wallet += winnings;
    }

    public String printWallet(){
        return ("Wallet: $" + wallet);
    }
 }
