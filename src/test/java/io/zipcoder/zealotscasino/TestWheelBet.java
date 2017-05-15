package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by stephenpegram on 5/15/17.
 */
public class TestWheelBet {

    BigSixDealer dealer;
    Player player;

    @Before
    public void setUp() {
        player = new Player(1000);
        dealer = new BigSixDealer();
    }

    @Test
    public void makeWheelBet_CreatesWheelBet_SetsWheelBetTo60() {
        //Given
        double expected = 60;
        String locationOnWheel = "JOKER";

        //When
        WheelBet wb = new WheelBet();
        wb.makeWheelBet(locationOnWheel, 60, player);
        double actual = wb.getBetValue();

        //Then
        assertEquals("makeWheelBet should set the WheelBet.betValue to 60", expected, actual, 0);
    }

    @Test
    public void makeWheelBet_CreatesWheelBet_BetLocationIsJoker() {
        //Given
        String expected = "JOKER";

        //When
        WheelBet wb = new WheelBet();
        wb.makeWheelBet("JOKER", 60, player);
        String actual = wb.getLocationOnWheel();

        //Then
        assertEquals("Bet location should be on JOKER", expected, actual);
    }
}
