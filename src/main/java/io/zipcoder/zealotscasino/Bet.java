package io.zipcoder.zealotscasino;

/**
 * Created by aaronlong on 5/12/17.
 */
public class Bet {

    private double bet;
    private final static double MINIMUM_BET = 20;

    public boolean setBetValue(double betValue) {
        if (MINIMUM_BET > betValue) {
            return false;
        }
        this.bet = betValue;
        return true;
    }

    public double getBetValue() {
        return this.bet;
    }

    public void promptBet() {
        UserInput.display("How much would you like to bet?");
    }

    public void displayMiniumBet() {
        UserInput.display("Minimum bet is $20.");
    }

}
