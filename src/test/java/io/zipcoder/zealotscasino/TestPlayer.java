package io.zipcoder.zealotscasino;

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
        player = new Player();
    }
    @Test
    public void makeBet_PlayerMakesValidBet_WalletLosesBet(){
        //Given
        player.initializeWallet(100);
        double expectedWalletAfterBet = 60;

        //When
        player.makeBet(40);
        double actualWalletAfterBet = player.getWallet();

        //Then
        assertEquals(expectedWalletAfterBet, actualWalletAfterBet, 0);
    }
    @Test
    public void initializeWallet_WalletInitializedTo1000_WalletHas1000(){
        //Given
        double expectedWallet = 1000;
        //When
        player.initializeWallet(1000);
        double actualWallet = player.getWallet();
        //Then
        assertEquals(expectedWallet, actualWallet, 0);
    }
    @Test
    public void collectWinnings_200IsWon_WalletIncreasedBy200(){
        //Given
        player.initializeWallet(800);
        double expectedWallet = 1000;
        //When
        player.collectWinnings(200);
        double actualWallet = player.getWallet();
        //Then
        assertEquals(expectedWallet, actualWallet, 0);
    }
    @Test
    public void printWallet_WalletHas1000_CorrectlyFormattedStringPrinted(){
        //Given
        player.initializeWallet(1000);
        String expected = "Wallet: $1,000.00";
        //When
        String actual = player.printWallet();
        //Then
        assertEquals(expected, actual);
    }
}
