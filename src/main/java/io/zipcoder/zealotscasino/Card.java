package io.zipcoder.zealotscasino;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class Card implements Comparable<Card>{


    private String suit;
    private int value;
    private String faceValue;

    public enum Suit { S, D, H, C }
    public enum CardValue { J, Q, K, A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN }

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
        if (faceValue == null) {
            return String.format("%d%s", value, suit.toUpperCase().charAt(0));
        }
        return String.format("%s%s", faceValue, suit.toUpperCase().charAt(0));
    }

}
