package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrewwong on 5/10/17.
 */
public class TestWarDealer {
    WarDealer dealer;
    Player player;

    @Before
    public void initWarDealer(){
        dealer = new WarDealer();
        player = new Player();
    }
    @Test
    public void dealHandTo_DealerHasCard_PlayerReceivesCard(){
        //Given
        Player player = new Player();
        int expectedSizeOfHand = 1;

        //When
        dealer.dealHandTo(player);

        //Then
        assertEquals("Player did not receive card", expectedSizeOfHand, player.getHand().getCards().size());
    }

    @Test
    public void pay_PlayerHasWallet_PlayerReceivesPayout() {
        //Given
        player.setWallet(0);
        double expectedWallet = 100;

        //When
        dealer.pay(player, 100);
        double actualWallet = player.getWallet();

        //Then
        assertEquals("Player did not receive payout", expectedWallet, actualWallet, 0D);
    }
//    @Test
//    public void takeTurn() {
//
//    }
//

}
