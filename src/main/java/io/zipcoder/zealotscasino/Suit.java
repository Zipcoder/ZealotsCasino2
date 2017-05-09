package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public enum Suit {
    SPADES("S"), HEARTS("H"), DIAMOND("D"), CLUBS("C");

    private String suitVal;

    Suit(String theVal) {
        suitVal = theVal;
    }
}
