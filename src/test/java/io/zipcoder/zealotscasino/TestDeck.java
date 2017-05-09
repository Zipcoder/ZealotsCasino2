package io.zipcoder.zealotscasino;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andrewwong on 5/9/17.
 */
public class TestDeck {
    Deck deck;
    @Before
    public void instantiateDeck(){
        deck = new Deck();
    }
    @Test
    public void buildDeck_DeckIsEmpty_DeckOf52Returned(){
        //Given
        int expectedDeckSize = 52;

        //When
        deck.buildDeck();

        //Then
        assertEquals("Deck is not the correct size", expectedDeckSize, deck.getDeckQue().size());
    }
    @Test
    public void getCard_DeckHasCards_CardIsReturned(){
        //Given
        deck.buildDeck();
        Class cardType = Card.class;
        Card card;
        //When
        card = deck.surrenderCard();
        //Then
        assertEquals("Card not returned", card.getClass(), cardType);
    }

}
