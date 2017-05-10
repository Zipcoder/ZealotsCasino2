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

    @Test
    public void testExamineHandValue_HandHasCards_HandValueIncreases(){
        //Given
        int expected = 4;
        Hand hand = new Hand();
        Card card1 = new Card("TWO", "S");
        Card card2 = new Card("TWO", "H");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        player.setHand(hand);
        //When
        int actual = blackJackDealer.examineHandValue(player.getHand());
        //Then
        Assert.assertEquals("Checking to see if player receives proper hand value", expected, actual);

    }

}
