package io.zipcoder.zealotscasino;

/**
 * Created by aaronlong on 5/12/17.
 */
public class Bet {

    private double bet;
    private final static double MINIMUM_BET = 20;

    public boolean makeBet(double betValue) {
        if (MINIMUM_BET >= betValue) {
            return false;
        }
        bet = betValue;
        return true;
    }

    public double betValue() {
        return bet;
    }

    public void promptBet() {
        UserInput.display("How much would you like to bet?");
    }

    public void displayMiniumBet() {
        UserInput.display("Minimum bet is $20.");
    }

}
