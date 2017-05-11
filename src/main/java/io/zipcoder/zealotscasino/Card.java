package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class Card implements Comparable<Card>{


    private String suit;
    private int value;
    private String faceValue;

    public enum Suit {SPADES, DIAMONDS, HEARTS, CLUBS}
    public enum CardValue {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    public Card(){}

    public Card(int value, String suit){
        this.suit = suit;
        this.value = value;
    }

    public Card(String faceValue, String suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }


    public String getSuit() {
        return suit;
    }


    public int getValue() {
        return value;
    }

    public String getFaceValue() {
        if (faceValue == null) return "";
        return faceValue;
    }

    public void setFaceValue(String theFaceValue) {
        faceValue = theFaceValue;
    }


    public int compareTo(Card c){
        return value - c.getValue();
    }

    public String toString(){
        
        return String.format("%s of %s", faceValue.charAt(0) + faceValue.substring(1).toLowerCase(), suit.charAt(0) + suit.substring(1).toLowerCase());
    }

}
