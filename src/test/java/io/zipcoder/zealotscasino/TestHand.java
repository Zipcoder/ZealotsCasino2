package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by aaronlong on 5/9/17.
 */
public class TestHand {

    private ArrayList<Card> expected;
    Hand hand;

    @Before
    public void setUp(){
        expected = new ArrayList<>();
        Card[] cards = { new Card("NINE", Card.Suit.SPADES.name()), new Card("TEN", Card.Suit.CLUBS.name()) };
        hand = new Hand();
        for (Card card : cards) { expected.add(card); }
    }

    @Test
    public void getCards_ConfirmBulkAddition_ReturnArrayListCard() {
        //Given
        hand.receiveCards(expected);

        //When
        ArrayList<Card> actual = hand.getCards();

        //Then
        assertEquals("Confirming values added", expected, actual);
    }

    @Test
    public void remove_ConfirmSingleRemoval_TenOfClubsRemoved() {
        // Given
        hand.receiveCards(expected);
        expected.remove(1);

        // When
        hand.remove(1);
        ArrayList<Card> actual = hand.getCards();

        //Then
        assertEquals("Confirming that the 10 of clubs has been removed", expected, actual);
    }
}
