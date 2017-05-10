package io.zipcoder.zealotscasino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by denniskalaygian on 5/10/17.
 */
public class TestBlackJackDealer {
    private Player player;
    private BlackJackDealer blackJackDealer;

    @Before
    public void setUp() {
        player = new Player();
        blackJackDealer = new BlackJackDealer();

    }

    @Test
    public void testDealCardTo_DealerGivesCard_PlayersCardIncrease() {
        // Given
        // When
        blackJackDealer.dealCardTo(player);

        // Then
        Assert.assertTrue("Check if new card are added",  player.getHand().getCards().size() > 0);
    }

    @Test
    public void testDealHandTo_DealerGivesHand_PlayerCardIncrease() {

        // When
        blackJackDealer.dealHandTo(player);

        // Then
        Assert.assertEquals("Checking if hand is added", player.getHand().getCards().size(), 2);
    }

    @Test
    public void testPay_PlayerGetsPaid_PlayerWalletIncrease(){

        //When
        blackJackDealer.pay(player, 10.0);

        //System.out.println(Card.CardValue.valueOf("THREE").ordinal());

        //Then
        Assert.assertEquals("Checking if player is paid correctly", player.getWallet(), 10.0, 0);
    }

}
