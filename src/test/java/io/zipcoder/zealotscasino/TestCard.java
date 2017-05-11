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


    @Before
    public void start() {
        card1 = new Card(Card.CardValue.TWO.toString(), "SPADES");
        card2 = new Card(Card.CardValue.THREE.toString(), "SPADES");
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
        String expected = "Two of Spades";

        //When
        String actual = card1.toString();

        //Then
        assertEquals("toString is not yielding the correct string", expected, actual);
    }

    @Test
    public void toString_FormatsCardCorrectlyWithFaceValue_ReturnsString() {
        //Given
        String expected = "Jack of Spades";
        card1 = new Card(Card.CardValue.JACK.name(), "SPADES");


        //When
        String actual = card1.toString();

        //Then
        assertEquals("toString is not yielding the correct string", expected, actual);
    }




}
