package io.zipcoder.zealotscasino;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by aaronlong on 5/9/17.
 */
public class TestHand {
    @Test
    public void getCards_ConfirmBulkAddition_ReturnArrayListCard() {
        // Given
        ArrayList<Card> expected = new ArrayList<Card>();
        Card[] cards = { new Card(9, Suit.CLUBS.getVal()), new Card(10, Suit.CLUBS.getVal()) };
        for (Card card : cards) { expected.add(card); }
        Hand hand = new Hand();
        hand.receiveCards(expected);

        //When
        ArrayList<Card> actual = hand.getCards();

        //Then
        assertEquals("Confirming values added", expected, actual);
    }
}
