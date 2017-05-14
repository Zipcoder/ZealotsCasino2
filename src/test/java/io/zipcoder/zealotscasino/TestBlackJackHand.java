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
    public void testExamineHandValue_PlayerHasHand_CorrectValueIsReturned(){
        //Given
        Card card1 = new Card("TWO", "Spades");
        Card card2 = new Card("THREE", "Spades");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 5;

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

    /* @Test
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
    } */
}
