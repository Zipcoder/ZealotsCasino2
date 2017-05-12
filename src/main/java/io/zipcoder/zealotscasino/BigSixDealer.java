package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Collections;

import static io.zipcoder.zealotscasino.UserInput.getDoubleInput;
import static io.zipcoder.zealotscasino.UserInput.getStringInput;

/**
 * Created by stephenpegram on 5/11/17.
 */
public class BigSixDealer {

    private ArrayList<String> wheelDenominations = new ArrayList<>();
    private ArrayList<WheelBet> wheelBets = new ArrayList<>();

    public ArrayList<String> getWheelDenominations() {
        return wheelDenominations;
    }

    public void setWheelDenominations(ArrayList<String> wheelDenominations) {
        this.wheelDenominations = wheelDenominations;
    }


    public void initializeWheelDenominations() {
        for (int i = 0; i < 24; i++) wheelDenominations.add("1");
        for (int i = 0; i < 15; i++) wheelDenominations.add("2");
        for (int i = 0; i < 7; i++) wheelDenominations.add("5");
        for (int i = 0; i < 4; i++) wheelDenominations.add("10");
        for (int i = 0; i < 2; i++) wheelDenominations.add("20");

        wheelDenominations.add("JOKER");
        wheelDenominations.add("CASINO");
    }

    public String spinWheel() {
        Collections.shuffle(wheelDenominations);
        String winningDenomination = wheelDenominations.get(0);
        return winningDenomination;
    }

    public int checkIfWon(String winningDenomination) {
        int payOutRatio;
        switch (winningDenomination) {
            case "JOKER":
                payOutRatio = 40;
                break;
            case "CASINO":
                payOutRatio = 40;
                break;
            case "1":
                payOutRatio = 1;
                break;
            case "2":
                payOutRatio = 2;
                break;
            case "5":
                payOutRatio = 5;
                break;
            case "10":
                payOutRatio = 10;
                break;
            case "20":
                payOutRatio = 20;
                break;
            default:
                payOutRatio = 0;
        }
        return payOutRatio;
    }

    public void payOut(Player player, int payOutRatio, double betAmount) {
        player.collectWinnings(betAmount + betAmount * payOutRatio);
    }

    public void playRound(Player player) {
        takeBetsAndDenominations(player);
        String winningDenomination = spinWheel();
        int payOutRatio = checkIfWon(winningDenomination);
        if (payOutRatio == 0){
            System.out.println("You must choose one of the denominations on the wheel");
            playRound(player);
        }

        for (WheelBet wheelBet : wheelBets) {
            if (wheelBet.getLocationOnWheel().equalsIgnoreCase(winningDenomination)) {
                payOut(player, payOutRatio, wheelBet.getBetAmount());
                System.out.println("Your bet of " + wheelBet.getBetAmount() + " on " + wheelBet.getLocationOnWheel() + " netted " + (wheelBet.getBetAmount() * payOutRatio));
            } else {
                System.out.println("Your bet of " + wheelBet.getBetAmount() + " on " + wheelBet.getLocationOnWheel() + " did not hit");
            }
        }
    }


    public void play(Player player) {
            initializeWheelDenominations();
            playRound(player);
            System.out.println("Wallet : " + player.getWallet());
            askPlayAgain(player);
    }


    public void takeBetsAndDenominations(Player player) {
        String denominationChoice;
        double amount;
        do {
            System.out.println("Enter a denomination on the wheel to determine your pay out ratio\n1 : Bet * 1\n2 : Bet * 2\n5: Bet * 5\n10 : Bet * 10\n20 : Bet * 20\nJOKER : Bet * 40\nCASINO : Bet * 40\n");
            denominationChoice = UserInput.getStringInput("Which denomination would you like to place a bet on? (or n if done placing bets)");
            if (denominationChoice.equalsIgnoreCase("n"))
                break;

                try {
                    player.makeBet(getDoubleInput("How much would you like to bet?"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Insufficient Funds.");
                    playRound(player);
                    return;
                } catch (SecurityException e) {
                    System.out.println("Minimum bet is $20.");
                    playRound(player);
                    return;
                }

                WheelBet wheelbet = new WheelBet(player.getBet(), denominationChoice);
                wheelBets.add(wheelbet);


            }
            while(!denominationChoice.equalsIgnoreCase("n"));
        }


    public void askPlayAgain(Player player) {
        if (player.getWallet() > player.getMinimumBet()) {
            String choice = getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit big six)");
            if (choice.equalsIgnoreCase("Y")) {
                wheelBets.clear();
                play(player);
            } else System.out.println("Thanks for playing!\n\n");
        }
        else{ System.out.println("You can do a lot with $" + player.getWallet() + ". Just not in here.\n");}
    }
}
