package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by aaronlong on 5/13/17.
 */
public class TestBlackJackHand {
    private Player player;
    private BlackJackDealer blackJackDealer;
    private BlackJackHand hand;

    @Before
    public void setUp() {
        player = new Player();
        blackJackDealer = new BlackJackDealer();
        hand = new BlackJackHand();
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_ValueIsReturned() {
        //Given
        player.setHand(hand);
        blackJackDealer.initializeHands(player);
        blackJackDealer.dealHandTo(player);

        //When
        hand = (BlackJackHand) player.getHand();
        int returnValue = hand.examineHandValue();

        //Then
        Assert.assertTrue("Checking if player received a value for their hand", returnValue > 0);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_CorrectValueIsReturned(){
        //Given
        Card card = new Card("TWO", "Spades");
        hand.receiveCard(card);
        int expectedValue = 2;

        //When
        int returnValue = hand.examineHandValue();

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", expectedValue, returnValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_CorrectValueIsReturnedForAce(){
        //Given
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("ACE", "Hearts");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 12;

        //When
        int returnValue = hand.examineHandValue();

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", expectedValue, returnValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_AceIsDynamic(){
        //Given
        Card[] cards = {
                new Card("ACE", "Spades"),
                new Card("NINE", "Hearts"),
                new Card("TEN", "Spades")
        };
        for (Card card : cards) hand.receiveCard(card);
        int expectedValue = 20;

        //When
        int returnValue = hand.examineHandValue();

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", expectedValue, returnValue);
    }

    @Test
    public void testExamineHandValue_PlayerHasHand_AceIsStaticPostDynamic(){
        //Given
        Card[] cards = {
                new Card("ACE", "Spades"),
                new Card("JACK", "Hearts"),
                new Card("TWO", "Spades"),
                new Card("NINE", "Spades")
        };
        for (Card card : cards) hand.receiveCard(card);
        int expectedValue = 22;

        //When
        int returnValue = hand.examineHandValue();

        //Then
        Assert.assertEquals("Checking if player received a correct value for their hand", expectedValue, returnValue);
    }

    @Test
    public void testCheckIfBusted_Over21Hand_ReturnTrue(){
        //Given
        Card[] cards = {
                new Card("ACE", "Spades"),
                new Card("JACK", "Hearts"),
                new Card("TWO", "Spades"),
                new Card("NINE", "Spades")
        };
        for (Card card : cards) hand.receiveCard(card);

        //When

        //Then
        Assert.assertTrue("Checking if something goes over 21", hand.checkBust());
    }

    @Test
    public void testAssertBlackJack_21_ReturnTrue(){
        //Given
        Card[] cards = {
                new Card("ACE", "Spades"),
                new Card("JACK", "Hearts"),
        };
        for (Card card : cards) hand.receiveCard(card);

        //When

        //Then
        Assert.assertTrue("Checking if valid BlackJack response", hand.checkBlackJack());
        Assert.assertEquals("Checking if valid BlackJack at 21", 21, hand.getPlayerHandValue());
    }
}
