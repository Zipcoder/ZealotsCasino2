package io.zipcoder.zealotscasino;

/**
 * Created by aaronlong on 5/12/17.
 */
public class Bet {

    private double bet;
    public final static int MINIMUM_BET = 20;

    public boolean makeBet(double betValue, Player player) {
        boolean flag = true;
        while(flag) {
            if (MINIMUM_BET > betValue) {
                displayMinimumBet();
                return false;
            } else if (player.getWallet() < betValue) {
                UserInput.display("You do not have enough chips!\n" + player.printWallet());
                return false;
            }
        }
        this.bet = betValue;
        player.setWallet(player.getWallet() - betValue);
        return true;
    }

    public double getBetValue() {
        return this.bet;
    }

    public void doubleBet(){
        this.bet *= 2;
    }

    public static void promptBet() {
        UserInput.display("How much would you like to bet?");
    }

    public static void displayMinimumBet() {
        UserInput.display("Minimum bet is " + MINIMUM_BET);
    }

}