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

    

    //evaluateCard
    //evaluateAce
    //extractCardOrdinal
    //checkIfBust
    //checkIfBlackJack

}

