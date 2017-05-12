package io.zipcoder.zealotscasino;

import java.util.ArrayList;

/**
 * Created by stephenpegram on 5/11/17.
 */
public class WheelBet {

    private double betAmount;
    private String locationOnWheel;

    public WheelBet(double betAmount, String locationOnWheel) {
        this.betAmount = betAmount;
        this.locationOnWheel = locationOnWheel;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public String getLocationOnWheel() {
        return locationOnWheel;
    }
}
