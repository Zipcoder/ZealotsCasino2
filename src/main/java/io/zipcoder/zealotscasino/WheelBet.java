package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by stephenpegram on 5/11/17.
 */
public class WheelBet extends Bet {

    private String locationOnWheel;

    public boolean makeWheelBet(String location, double betValue, Player player) {
        if (super.makeBet(betValue, player)) {
            locationOnWheel = location;
            return true;
        }
        return false;
    }

    public String getLocationOnWheel() {
        return locationOnWheel;
    }
}