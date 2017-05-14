package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by andrewwong on 5/12/17.
 */
public class TestPlayer {
    Player player;
    @Before
    public void initPlayer(){
        player = new Player(500);
    }

    @Test
    public void printWalletTest(){
        //Given
        String expected = "Wallet: $500.0";

        //When
        String actual = player.printWallet();

        //Then
        assertEquals("Should be $500", expected, actual);
    }
}
