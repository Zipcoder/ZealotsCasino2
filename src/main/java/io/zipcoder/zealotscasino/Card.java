package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class Card implements Comparable<Card>{


    private String suit;
    private String faceValue;

    public enum Suit {SPADES, DIAMONDS, HEARTS, CLUBS}
    public enum CardValue {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    public Card(){}

    public Card(String faceValue, String suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return Card.CardValue.valueOf(getFaceValue()).ordinal() + 2;
    }

    public String getFaceValue() {
        if (faceValue == null) return "";
        return faceValue;
    }

    public String getSuitSymbol()
    {
        if(suit.equals(Suit.SPADES))
            return ((char)'\u2660' + " ");
        else if(suit.equals(Suit.CLUBS))
            return ((char)'\u2663' + " ");
        else if(suit.equals(Suit.DIAMONDS))
            return ((char)'\u2666' + " ");
        else
            return ((char)'\u2764' + " ");

    }

    public void setFaceValue(String theFaceValue) {
        faceValue = theFaceValue;
    }

    public int compareTo(Card c){
        return Card.CardValue.valueOf(getFaceValue()).ordinal() - Card.CardValue.valueOf(c.getFaceValue()).ordinal();
    }

    public String toString(){
        return ("| " + getFaceValue().substring(0, 1) + " " + getSuitSymbol() + " |");
    }



}
