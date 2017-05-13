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
        player.setHand(new BlackJackHand());
        blackJackDealer.dealCardTo(player);

        // Then
        Assert.assertTrue("Check if new card is added",  player.getHand().getCards().size() > 0);
    }

    @Test
    public void testDealHandTo_DealerGivesHand_PlayerCardIncrease() {

        // When
        player.setHand(new BlackJackHand());
        blackJackDealer.dealHandTo(player);

        // Then
        Assert.assertEquals("Checking if hand is added", player.getHand().getCards().size(), 2);
    }

    @Test
    public void testPay_PlayerGetsPaid_PlayerWalletIncrease(){

        //When
        blackJackDealer.pay(player, 10.0);

        //UserInput.display(Card.CardValue.valueOf("THREE").ordinal());

        //Then
        Assert.assertEquals("Checking if player is paid correctly", player.getWallet(), 10.0, 0);
    }

    @Test
    public void testAssertBlackJack_PlayerHasBlackJack_PlayerIsPaidProperly() {
        //Given
        player.setHand(new BlackJackHand());
        blackJackDealer.initializeHands(player);
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("TEN", "Spades");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card1);
        currentHand.receiveCard(card2);
        BlackJackHand hand = (BlackJackHand) player.getHand();
        blackJackDealer.setPlayerHandValue(hand.examineHandValue());
        player.setWallet(300);
        player.makeBet(300);
        double expected = 900;

        //When
        blackJackDealer.assertBlackJack(player);

        //Then
        Assert.assertEquals("Checking to see if player receives proper amount", expected, player.getWallet(), 0);
    }

}
