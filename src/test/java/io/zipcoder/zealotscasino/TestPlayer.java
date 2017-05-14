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
    Player player2;
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

    @Test
    public void printWalletSet(){
        //given
        player2 = new Player();
        player2.setWallet(88);
        String expected = "Wallet: $88.0";

        //when
        String actual = player2.printWallet();

        //then
        assertEquals("Shoud be 88", expected, actual);
    }
}
