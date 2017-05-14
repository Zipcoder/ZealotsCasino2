package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by stephenpegram on 5/11/17.
 */
public class WheelBet {

    private int betAmount;
    private String locationOnWheel;

    public WheelBet(int betAmount, String locationOnWheel) {
        this.betAmount = betAmount;
        this.locationOnWheel = locationOnWheel;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public String getLocationOnWheel() {
        return locationOnWheel;
    }
}