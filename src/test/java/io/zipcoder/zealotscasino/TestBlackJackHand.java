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

    //extractCardOrdinal
    @Test
    public void testExtractCardOrdinal_CardHasNumericValue_CorrectOrdinalIsReturned(){
        //Given
        Card card1 = new Card("TWO", "Spades");
        hand.receiveCard(card1);
        int expectedOrdinal = 0;

        //When
        int actualOrdinal = hand.extractCardOrdinal(card1);

        //Then
        Assert.assertEquals("Checking to see if the correct ordinal of the enum is returned", expectedOrdinal, actualOrdinal);
    }

    @Test
    public void testExtractCardOrdinal_CardHasFaceValue_CorrectOrdinalIsReturned(){
        //Given
        Card card1 = new Card("KING", "Spades");
        hand.receiveCard(card1);
        int expectedOrdinal = 11;

        //When
        int actualOrdinal = hand.extractCardOrdinal(card1);

        //Then
        Assert.assertEquals("Checking to see if the correct ordinal of the enum is returned", expectedOrdinal, actualOrdinal);
    }

    @Test
    public void testExtractCardOrdinal_CardHasAceValue_CorrectOrdinalIsReturned(){
        //Given
        Card card1 = new Card("ACE", "Spades");
        hand.receiveCard(card1);
        int expectedOrdinal = 12;

        //When
        int actualOrdinal = hand.extractCardOrdinal(card1);

        //Then
        Assert.assertEquals("Checking to see if the correct ordinal of the enum is returned", expectedOrdinal, actualOrdinal);
    }

    //evaluateCard
    @Test
    public void testEvaluateCard_CardHasNumericValue_CorrectValueIsReturned(){
        //Given
        Card card = new Card("SIX", "Hearts");
        int expectedValue = 6;
        //When
        int methodReturnValue = hand.evaluateCard(card);
        //Then
        Assert.assertEquals("Checking to see if method properly evaluates the given card", expectedValue, methodReturnValue);
    }

    @Test
    public void testEvaluateCard_CardHasFaceValue_CorrectValueIsReturned(){
        //Given
        Card card = new Card("KING", "Hearts");
        int expectedValue = 10;
        //When
        int methodReturnValue = hand.evaluateCard(card);
        //Then
        Assert.assertEquals("Checking to see if method properly evaluates the given card", expectedValue, methodReturnValue);
    }

    @Test
    public void testEvaluateAce_CardHasHighAceValue_CorrectValueIsReturned(){
        //Given
        Card card = new Card("ACE", "Hearts");
        hand.receiveCard(card);
        int expectedValue = 11;
        //When
        int methodReturnValue = hand.evaluateAce();
        //Then
        Assert.assertEquals("Checking to see if method properly evaluates the given card", expectedValue, methodReturnValue);
    }

    @Test
    public void testEvaluateAce_CardHasLowAceValue_CorrectValueIsReturned(){
        //Given
        hand.setHandValue(19);
        Card card = new Card("ACE", "Spades");
        int expectedValue = 1;
        hand.receiveCard(card);
        //When
        int methodReturnValue = hand.evaluateAce();
        //Then
        Assert.assertEquals("Checking to see if method properly evaluates the given card", expectedValue, methodReturnValue);
    }

    //updateHandValue
    @Test
    public void testUpdateHandValue_PlayerDrawsANumericCard_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("SEVEN", "Spades");
        Card card2 = new Card("TEN", "Spades");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 17;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    @Test
    public void testUpdateHandValue_PlayerDrawsTwoFaceCards_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("KING", "Spades");
        Card card2 = new Card("JACK", "Spades");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 20;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    @Test
    public void testUpdateHandValue_PlayerDrawsAnAceHigh_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("EIGHT", "Spades");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 19;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    @Test
    public void testUpdateHandValue_PlayerDrawsTwoAces_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("ACE", "Hearts");
        Card card2 = new Card("ACE", "Spades");
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int expectedValue = 12;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    @Test
    public void testUpdateHandValue_PlayerBustsButAceAdjusts_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("NINE", "Spades");
        Card card3 = new Card("FOUR", "Spades");

        hand.receiveCard(card1);
        hand.receiveCard(card2);
        hand.receiveCard(card3);
        int expectedValue = 14;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    @Test
    public void testUpdateHandValue_AceAdjustsOnlyOnce_HandValueIsUpdated(){
        //Given
        Card card1 = new Card("ACE", "Spades");
        Card card2 = new Card("NINE", "Spades");
        Card card3 = new Card("FOUR", "Spades");
        Card card4 = new Card("TEN", "Spades");

        hand.receiveCard(card1);
        hand.receiveCard(card2);
        hand.receiveCard(card3);
        hand.receiveCard(card4);
        int expectedValue = 24;
        //When
        hand.updateHandValue();
        //Then
        Assert.assertEquals("Checking to see if the hand receives the right value", expectedValue, hand.getHandValue() );
    }

    //checkIfBust
    @Test
    public void testCheckIfBust_HandBusts_CorrectValueReturned(){
        //Given
        hand.setHandValue(23);
        boolean expectedValue = true;

        //When
        boolean returnValue = hand.checkIfBust();

        //Then
        Assert.assertEquals("Checking to see if method returns the correct boolean value", expectedValue, returnValue);

    }

    @Test
    public void testCheckIfBust_HandDoesntBust_CorrectValueReturned(){
        //Given
        hand.setHandValue(20);
        boolean expectedValue = false;

        //When
        boolean returnValue = hand.checkIfBust();

        //Then
        Assert.assertEquals("Checking to see if method returns the correct boolean value", expectedValue, returnValue);

    }

    //checkIfBlackJack
    @Test
    public void testCheckIfBlackJack_HandBusts_CorrectValueReturned(){
        //Given
        hand.setHandValue(23);
        boolean expectedValue = false;

        //When
        boolean returnValue = hand.checkIfBlackJack();

        //Then
        Assert.assertEquals("Checking to see if method returns the correct boolean value", expectedValue, returnValue);

    }

    @Test
    public void testCheckIfBlackJack_HandFallsShort_CorrectValueReturned(){
        //Given
        hand.setHandValue(20);
        boolean expectedValue = false;

        //When
        boolean returnValue = hand.checkIfBlackJack();

        //Then
        Assert.assertEquals("Checking to see if method returns the correct boolean value", expectedValue, returnValue);

    }

    @Test
    public void testCheckIfBlackJack_HandIsBlackJack_CorrectValueReturned(){
        //Given
        hand.setHandValue(21);
        boolean expectedValue = true;

        //When
        boolean returnValue = hand.checkIfBlackJack();

        //Then
        Assert.assertEquals("Checking to see if method returns the correct boolean value", expectedValue, returnValue);

    }

}

