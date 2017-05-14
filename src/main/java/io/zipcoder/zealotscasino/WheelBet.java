package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by stephenpegram on 5/11/17.
 */
public class WheelBet extends Bet {

    //private double betAmount;
    private String locationOnWheel;

    public WheelBet(Player player, double theBetAmount, String wheelLocation) {
        super.makeBet(theBetAmount, player);
        locationOnWheel = wheelLocation;
    }


    public String getLocationOnWheel() {
        return locationOnWheel;
    }
}
