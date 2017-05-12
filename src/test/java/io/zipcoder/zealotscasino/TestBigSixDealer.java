package io.zipcoder.zealotscasino;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by stephenpegram on 5/11/17.
 */
public class TestBigSixDealer {

    @Test
    public void initializeWheelDenominations_NumberOfDenominations_Returns54() {
        //Given
        BigSixDealer wheelDealer = new BigSixDealer();
        int expected = 54;

        //When
        wheelDealer.initializeWheelDenominations();
        int actual = wheelDealer.getWheelDenominations().size();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs20_ReturnsPayOutOf20() {
        //Given
        BigSixDealer wheelDealer = new BigSixDealer();
        String winningDenomination = "20";
        int expected = 20;

        //When
        int actual = wheelDealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void payOut_PlayerWinsOn20Denomination_PlayersWalletIncreasesBy20() {
        //Given
        BigSixDealer wheelDealer = new BigSixDealer();
        Player player = new Player();
        player.setWallet(100);
        player.makeBet(20);
        int payOutRatio = 20;
        double expected = 500;

        //When
        wheelDealer.payOut(player, payOutRatio, player.getBet());
        double actual = player.getWallet();

        //Then
        assertEquals(expected, actual,0);
    }
}
