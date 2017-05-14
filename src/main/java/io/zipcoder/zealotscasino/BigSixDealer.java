package io.zipcoder.zealotscasino;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by stephenpegram on 5/11/17.
 */
public class BigSixDealer implements Dealer {

    private ArrayList<String> wheelDenominations = new ArrayList<>();
    private ArrayList<WheelBet> wheelBets = new ArrayList<>();

    public ArrayList<String> getWheelDenominations() {
        return wheelDenominations;
    }

    private Bet bet;


    public void play(Player player) {
        initializeWheelDenominations();
        playRound(player);
        UserInput.display(player.printWallet());
        askPlayAgain(player);
        bet = new Bet();
    }

    @Override
    public Bet getBet() {
        return bet;
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

    public void takeBetsAndDenominations(Player player) {
        boolean quit = false;

        while (!quit){
            UserInput.display("Enter a denomination on the wheel to determine your pay out ratio\n1 : You win your bet * 1\n2 : You win your bet * 2\n5: You win your bet * 5\n10 : You win your bet * 10\n20 : You win your bet * 20\nJOKER : You win your bet * 40\nCASINO : You win your bet * 40\n");
            String denominationChoice = UserInput.getStringInput("Which denomination would you like to place a bet on? (or n if done placing bets)");

            if (denominationChoice.equalsIgnoreCase("n")) {quit = true;}
            else if (denominationChoice.equals("1") || denominationChoice.equals("2") || denominationChoice.equals("5") || denominationChoice.equals("10") || denominationChoice.equals("20") || denominationChoice.equalsIgnoreCase("JOKER") || denominationChoice.equalsIgnoreCase("CASINO"))
            {
                if(bet.makeBet(UserInput.getIntInput("How much would you like to bet?"), player)){
                    wheelBets.add(new WheelBet(player, bet.getBetValue(), denominationChoice));
                    UserInput.display("\n" + player.printWallet() + "\n");
                }
            }
            else {UserInput.display("You must enter a valid denomination.");}
        }
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
                payOutRatio = -1;
        }
        return payOutRatio;
    }

    public void pay(Player player, double payOut) {
        player.collectWinnings(payOut);
    }


    public void playRound(Player player) {
        takeBetsAndDenominations(player);
        String winningDenomination = spinWheel();
        int payOutRatio = checkIfWon(winningDenomination);
        for (WheelBet wheelBet : wheelBets) {
            if (wheelBet.getLocationOnWheel().equalsIgnoreCase(winningDenomination)) {
                pay(player, payOutRatio * wheelBet.getBetValue());
                UserInput.display("Your bet of $" + wheelBet.getBetValue() + " on denomination " + wheelBet.getLocationOnWheel() + " netted " + (wheelBet.getBetValue() * payOutRatio) + "!!");
            } else {
                UserInput.display("Your bet of $" + wheelBet.getBetValue() + " on denomination " + wheelBet.getLocationOnWheel() + " did not hit...");
            }
        }
    }


    public void askPlayAgain(Player player) {
        if (player.getWallet() > Bet.MINIMUM_BET) {
            String choice = UserInput.getStringInput("Would you like to play again? (Push 'Y' to play again, 'Any other key' to quit big six)");
            if (choice.equalsIgnoreCase("Y")) {
                wheelBets.clear();
                play(player);
            } else UserInput.display("Thanks for playing!\n\n");
        } else {UserInput.display("You can do a lot with $" + player.getWallet() + ". Just not in here.\n");}
    }
}
