package io.zipcoder.zealotscasino;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by aaronlong on 5/12/17.
 */
public class TestBet {

    Player player;
    Bet bet;

    @Before
    public void initialize() {
        player = new Player(1000);
        bet = new Bet();
    }

    @Test
    public void makeBetTest_BetOf100_PlayersWallet900(){
        double expected = 900;

        //When
        bet.makeBet(100, player);
        double actual = player.getWallet();

        //Then
        assertEquals("Player's wallet should correctly be debited 100", expected, actual, 0);
    }

    @Test
    public void makeBetTest_BetOf100_BetValueBecomes100(){
        double expected = 100;

        //When
        bet.makeBet(100, player);
        double actual = bet.getBetValue();

        //Then
        assertEquals("A bet of 100 should be recorded in the Bet's bet field", expected, actual, 0);
    }
}
