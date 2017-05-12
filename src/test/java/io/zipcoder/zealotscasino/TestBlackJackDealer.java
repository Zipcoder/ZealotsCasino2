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
        Assert.assertTrue("Check if new card is added",  player.getHand().getCards().size() > 0);
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
    public void testExamineHandValue_PlayerHasHand_ValueIsReturned(){
        //Given
        blackJackDealer.initializeHands(player);
        blackJackDealer.dealHandTo(player);

        //When
        int returnValue = blackJackDealer.examineHandValue(player.getHand());

        //Then
        Assert.assertTrue("Checking if player received a value for their hand", returnValue > 0);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_CorrectValueIsReturned(){
        //Given
        blackJackDealer.initializeHands(player);
        Card card = new Card("TWO", "Spades");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card);
        int expectedValue = 2;

        //When
        int returnValue = blackJackDealer.examineHandValue(player.getHand());

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", returnValue, expectedValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_CorrectValueIsReturnedForAce(){
        //Given
        blackJackDealer.initializeHands(player);
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("ACE", "Hearts");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card1);
        currentHand.receiveCard(card2);
        int expectedValue = 12;

        //When
        int returnValue = blackJackDealer.examineHandValue(player.getHand());

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", returnValue, expectedValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_AceIsDynamic(){
        //Given
        blackJackDealer.initializeHands(player);
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("NINE", "Hearts");
        Card card3 = new Card("TEN", "Spades");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card1);
        currentHand.receiveCard(card2);
        currentHand.receiveCard(card3);
        int expectedValue = 20;

        //When
        int returnValue = blackJackDealer.examineHandValue(player.getHand());

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", returnValue, expectedValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_AceIsStaticPostDynamic(){
        //Given
        blackJackDealer.initializeHands(player);
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("JACK", "Hearts");
        Card card3 = new Card("TWO", "Spades");
        Card card4 = new Card("NINE", "Spades");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card1);
        currentHand.receiveCard(card2);
        currentHand.receiveCard(card3);
        currentHand.receiveCard(card4);
        int expectedValue = 22;

        //When
        int returnValue = blackJackDealer.examineHandValue(player.getHand());

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", returnValue, expectedValue);
    }

    @Test
    public void testAssertBlackJack_PlayerHasBlackJack_PlayerIsPaidProperly() {
        //Given
        blackJackDealer.initializeHands(player);
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("TEN", "Spades");
        Hand currentHand = player.getHand();
        currentHand.receiveCard(card1);
        currentHand.receiveCard(card2);
        blackJackDealer.setPlayerHandValue(blackJackDealer.examineHandValue(player.getHand()));
        player.setWallet(300);
        player.makeBet(300);
        double expected = 900;

        //When
        blackJackDealer.assertBlackJack(player);

        //Then
        Assert.assertEquals("Checking to see if player receives proper amount", expected, player.getWallet(), 0);
    }

}
