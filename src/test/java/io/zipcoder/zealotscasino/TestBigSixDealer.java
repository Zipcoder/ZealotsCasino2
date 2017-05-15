package io.zipcoder.zealotscasino;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by stephenpegram on 5/11/17.
 */
public class TestBigSixDealer {
    BigSixDealer dealer;
    Player player;

    @Before
    public void setUp() {
        player = new Player();
        dealer = new BigSixDealer();
    }

    @Test
    public void initializeWheelDenominations_NumberOfDenominations_Returns54() {
        //Given
        int expected = 54;

        //When
        dealer.initializeWheelDenominations();
        int actual = dealer.getWheelDenominations().size();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs1_ReturnsPayOutOf1() {
        //Given
        String winningDenomination = "1";
        int expected = 1;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs2_ReturnsPayOutOf2() {
        //Given
        String winningDenomination = "2";
        int expected = 2;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs5_ReturnsPayOutOf5() {
        //Given
        String winningDenomination = "5";
        int expected = 5;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs10_ReturnsPayOutOf10() {
        //Given
        String winningDenomination = "10";
        int expected = 10;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIs20_ReturnsPayOutOf20() {
        //Given
        String winningDenomination = "20";
        int expected = 20;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIsJOKER_ReturnsPayOutOf40() {
        //Given
        String winningDenomination = "JOKER";
        int expected = 40;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfWon_WinningDenominationIsCASINO_ReturnsPayOutOf40() {
        //Given
        String winningDenomination = "CASINO";
        int expected = 40;

        //When
        int actual = dealer.checkIfWon(winningDenomination);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void payOut_PlayerWinsOn20Denomination_PlayersWalletIncreasesBy20() {
        //Given
        Bet bet = new Bet();
        player.setWallet(100);
        bet.makeBet(20, player);
        int payOutRatio = 20;
        double expected = 500;
        //When
        dealer.pay(player, bet.getBetValue() + payOutRatio * bet.getBetValue());
        double actual = player.getWallet();
        //Then
        assertEquals(expected, actual, 0);
    }

    @Test
    public void payOut_PlayerDoesNotWinOnAnyDenomination_PlayersWalletDoesNotIncrease() {
        //Given
        Bet bet = new Bet();
        player.setWallet(100);
        bet.makeBet(20, player);
        int payOutRatio = -1;
        double expected = 80;
        //When
        dealer.pay(player, bet.getBetValue() + payOutRatio * bet.getBetValue());
        double actual = player.getWallet();
        //Then
        assertEquals(expected, actual, 0);
    }


}