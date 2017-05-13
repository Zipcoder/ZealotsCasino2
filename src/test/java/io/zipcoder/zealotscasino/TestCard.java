package io.zipcoder.zealotscasino;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class TestCard {

    private Card card1;
    private Card card2;
    private Card card3;


    @Before
    public void start() {
        card1 = new Card(Card.CardValue.TWO.toString(), "SPADES");
        card2 = new Card(Card.CardValue.THREE.toString(), "SPADES");
        card3 = new Card(Card.CardValue.ACE.toString(), "HEARTS");
    }

    //nameOfMethod_Scenario_Result

    @Test
    public void compareTo_CardTwoIsGreater_ReturnsNegativeInt() {
        //When
        int actual = card1.compareTo(card2);

        //Then
        assertTrue("compareTo is not evaluating card values correctly", actual < 0);
    }


    @Test
    public void toString_FormatsCardCorrectly_ReturnsString() {
        //Given
        String expected = "| 2  ♠ |";

        //When
        String actual = card1.toString();

        //Then
        assertEquals("toString is not yielding the correct string", expected, actual);
    }

    @Test
    public void toString_FormatsCardCorrectlyWithFaceValue_ReturnsString() {
        //Given
        String expected = "| J  ♠ |";
        card1 = new Card(Card.CardValue.JACK.name(), "SPADES");


        //When
        String actual = card1.toString();

        //Then
        assertEquals("toString is not yielding the correct string", expected, actual);
    }

    @Test
    public void getValue_JackOfSpades_ValueIsEleven() {
        //Given
        int expected = 11;
        card1 = new Card(Card.CardValue.JACK.name(), "SPADES");

        //When
        int actual = card1.getValue();

        //Then
        assertEquals("The value of Jack is 11", expected, actual);
    }

    @Test
    public void testGetFaceToPrint_LowerThanEleven()
    {
        //Given

        String expected = "2";

        //When
        String actual = card1.getFaceToPrint();

        //Then
        assertEquals("Card with value Two should return \'2\'", expected, actual);
    }

    @Test
    public void testGetFaceToPrint_ACE()
    {
        //Given
        String expected = "A";

        //When
        String actual = card3.getFaceToPrint();

        //Then
        assertEquals("Card with value ACE should return \'A\'", expected, actual);
    }

    @Test
    public void testGetFaceToPrint_KING()
    {
        //Given
        Card king = new Card("KING", "CLUBS");
        String expected = "K";

        //When
        String actual = king.getFaceToPrint();

        //Then
        assertEquals("Card with value KING should return \'K\'", expected, actual);
    }

    @Test
    public void testGetFaceToPrint_QUEEN()
    {
        //Given
        Card queen = new Card("QUEEN", "CLUBS");
        String expected = "Q";

        //When
        String actual = queen.getFaceToPrint();

        //Then
        assertEquals("Card with value QUEEN should return \'Q\'", expected, actual);
    }

    @Test
    public void testGetFaceToPrint_JACK()
    {
        //Given
        Card king = new Card("JACK", "CLUBS");
        String expected = "J";

        //When
        String actual = king.getFaceToPrint();

        //Then
        assertEquals("Card with value JACK should return \'J\'", expected, actual);
    }

    @Test
    public void testGetSuitSymbol_Diamonds()
    {
        //Given
        Card diamond = new Card("JACK", "DIAMONDS");
        String expected = "♦ ";

        //When
        String actual = diamond.getSuitSymbol();

        assertEquals("Diamond should print ♦", expected, actual);
    }

    @Test
    public void testGetSuitSymbol_Spades()
    {
        //Given
        Card spades = new Card("TEN", "SPADES");
        String expected = "♠ ";

        //When
        String actual = spades.getSuitSymbol();

        assertEquals("Diamond should print ♠", expected, actual);
    }

    @Test
    public void testGetSuitSymbol_Hearts()
    {
        //Given
        Card symbol = new Card("TEN", "HEARTS");
        String expected = "❤ ";

        //When
        String actual = symbol.getSuitSymbol();

        assertEquals("Diamond should print ❤", expected, actual);
    }

    @Test
    public void testGetSuitSymbol_Clubs()
    {
        //Given
        Card symbol = new Card("TEN", "CLUBS");
        String expected = "♣ ";

        //When
        String actual = symbol.getSuitSymbol();

        assertEquals("Diamond should print ♣", expected, actual);
    }





}
